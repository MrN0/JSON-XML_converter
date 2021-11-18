package converter;

import converter.builder.Builder;
import converter.element.Element;
import converter.parser.Parser;

public class Converter {

	private final Parser parser;
	private final Builder builder;

	public Converter(Parser parser, Builder builder) {
		this.parser = parser;
		this.builder = builder;
	}

	public String convert(String document) {
		Element element = parser.parse(document);
		return builder.build(element);
	}

}
