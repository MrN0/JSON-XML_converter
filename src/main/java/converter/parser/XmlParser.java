package converter.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import converter.exception.InvalidElementException;

public class XmlParser extends AbstractParser {
	
	private static final Pattern ELEMENT_NAME_PATTERN;
	private static final Pattern ELEMENT_CONTENT_PATTERN;
	private static final Pattern ELEMENT_ATTRIBUTE_PATTERN;

	static {
		ELEMENT_NAME_PATTERN = Pattern.compile(String.format("^<(%s)", ELEMENT_NAME));
		ELEMENT_CONTENT_PATTERN = Pattern.compile(">(.+)<");
		ELEMENT_ATTRIBUTE_PATTERN = Pattern.compile(
				String.format("(%s)\\s*=\\s*\"(%s)\"", ATTRIBUTE_KEY, ATTRIBUTE_VALUE));
	}

	@Override
	protected String getElementName(String document) {
		var matcher = ELEMENT_NAME_PATTERN.matcher(document);
		if (matcher.find()) {
			return matcher.group(1);
		}
		throw new InvalidElementException(ERROR_MSG_ELEMENT_NAME_NOT_VALID);
	}

	@Override
	protected String getElementContent(String document) {
		var matcher = ELEMENT_CONTENT_PATTERN.matcher(document);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}

	@Override
	protected Map<String, String> getElementAttributes(String document) {
		Map<String, String> attributes = new HashMap<>();
		var matcher= ELEMENT_ATTRIBUTE_PATTERN.matcher(document);
		while (matcher.find()) {
			attributes.put(matcher.group(1), matcher.group(2));
		}
		return attributes;
	}

}
