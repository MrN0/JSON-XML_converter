package converter.parser;

import converter.element.Element;

public interface Parser {

	Element parse(String document);

}
