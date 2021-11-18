package converter.builder;

import java.util.Map;
import java.util.NoSuchElementException;

import converter.element.Element;

public class XmlBuilder implements Builder {

	private static final String ERROR_MSG_NO_ELEMENT = "There is no element to build!";
	
	@Override
	public String build(Element element) {
		if (element == null) {
			throw new NoSuchElementException(ERROR_MSG_NO_ELEMENT);
		}
		
		String name = element.getName();
		String attributes = buildAttributes(element.getAttributes());
		if (element.getContent() == null) {
			return getInlineElement(name, attributes);
		} else {
			return getBlockElement(name, attributes, element.getContent());
		}
	}

	private String getInlineElement(String name, String attributes) {
		return "<" + name + attributes + "/>";
	}

	private String getBlockElement(String name, String attributes, String content) {
		return String.format("<%s%s>%s</%s>", name, attributes, content, name);
	}
	
	private String buildAttributes(Map<String, String> attributes) {
		if (attributes.isEmpty()) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> attribute : attributes.entrySet()) {
			sb.append(" ").append(attribute.getKey()).append("=\"").append(attribute.getValue()).append("\"");
		}
		return sb.toString();
	}

}
