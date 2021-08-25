package converter.parser;

import java.util.regex.Pattern;

import converter.element.Element;
import converter.exception.InvalidElementException;

public class XmlParser implements Parser {

	private static final String INLINE = "<(\\w+)\\/>";
	private static final String BLOCK = "<(\\w+)\\/?>(.*)<\\/(\\w+)>";
	private final Pattern inlinePattern;
	private final Pattern blockPattern;

	public XmlParser() {
		this.inlinePattern = Pattern.compile(INLINE);
		this.blockPattern = Pattern.compile(BLOCK);
	}

	@Override
	public Element parse(String document) {
		var matcher = inlinePattern.matcher(document);
		if (matcher.find()) {
			return new Element(matcher.group(1), null);
		}

		matcher = blockPattern.matcher(document);
		if (matcher.find()) {
			return new Element(matcher.group(1), matcher.group(2));
		}

		throw new InvalidElementException("ERROR: Invalid XML document!");
	}

}
