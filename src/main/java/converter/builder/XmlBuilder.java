package converter.builder;

import converter.element.Element;

public class XmlBuilder implements Builder {

	@Override
	public String build(Element element) {
		return element.getValue() == null ? getInlineElement(element.getName())
				: getBlockElement(element.getName(), element.getValue());
	}

	private String getInlineElement(String name) {
		return "<" + name + "/>";
	}

	private String getBlockElement(String name, String value) {
		return String.format("<%s>%s</%s>", name, value, name);
	}
}
