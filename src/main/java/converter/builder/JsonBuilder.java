package converter.builder;

import converter.element.Element;

public class JsonBuilder implements Builder {

	@Override
	public String build(Element element) {
		String value = element.getValue() == null ? "null" : "\"" + element.getValue() + "\"";
		return String.format("{\"%s\":%s}", element.getName(), value);
	}

}
