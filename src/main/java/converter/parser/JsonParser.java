package converter.parser;

import java.util.regex.Pattern;

import converter.element.Element;
import converter.exception.InvalidElementException;

public class JsonParser implements Parser {

	private static final String JSON_ELEMENT = "\\{\"(\\w+)\"\\s*:\\s*(null|\"[^\\\"]+\")}";
	private final Pattern elementPattern;

	public JsonParser() {
		this.elementPattern = Pattern.compile(JSON_ELEMENT);
	}

	@Override
	public Element parse(String document) {
		var matcher = elementPattern.matcher(document);
		if (matcher.find()) {
			String value = "null".equals(matcher.group(2)) ? null : matcher.group(2).replace("\"", "");
			return new Element(matcher.group(1), value);
		}

		throw new InvalidElementException("ERROR: Invalid JSON document!");
	}

}
