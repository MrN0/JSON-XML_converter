package converter.builder;

import java.util.Map;
import java.util.NoSuchElementException;

import converter.element.Element;

public class JsonBuilder implements Builder {

	private static final String ERROR_MSG_NO_ELEMENT = "There is no element to parse!";
	
	@Override
	public String build(Element element) {
		if (element == null) {
			throw new NoSuchElementException(ERROR_MSG_NO_ELEMENT);
		}
		
		String name = element.getName();
		String content = buildContent(element.getContent());
		String attributes = buildAttributes(element.getAttributes());

		StringBuilder sb = new StringBuilder();
		sb.append("{\"").append(name).append("\":");

		if (attributes.isEmpty()) {
			sb.append(content);
		} else {
			sb.append("{").append(attributes);
			sb.append("\"#").append(name).append("\":").append(content).append("}");
		}
		
		sb.append("}");
		
		return sb.toString();
	}
	
	private String buildContent(String value) {
		if (value == null) {
			return "null";
		}
		return "\"" + value + "\"";
	}
	
	private String buildAttributes(Map<String, String> attributes) {
		if (attributes.isEmpty()) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> attribute : attributes.entrySet()) {
			sb.append("\"@").append(attribute.getKey()).append("\":\"").append(attribute.getValue()).append("\",");
		}
		return sb.toString();
	}

}
