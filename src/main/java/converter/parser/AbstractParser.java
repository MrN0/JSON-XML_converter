package converter.parser;

import java.util.Map;
import java.util.NoSuchElementException;

import converter.element.Element;
import converter.element.impl.BlockElement;
import converter.element.impl.InlineElement;

abstract class AbstractParser implements Parser {

	protected static final String ERROR_MSG_NO_ELEMENT = "There is no element to parse!";
	protected static final String ERROR_MSG_ELEMENT_NOT_VALID = "Element is not valid!";
	protected static final String ERROR_MSG_ELEMENT_NAME_NOT_VALID = "Element name is not valid!";
	protected static final String ERROR_MSG_ELEMENT_CONTENT_NOT_VALID = "Element content is not valid!";

	protected static final String ELEMENT_NAME = "[a-zA-Z_]\\w*";
	protected static final String ELEMENT_CONTENT = "null|\\w[\\w\\s]*";
	protected static final String ATTRIBUTE_KEY = "[a-zA-Z_]\\w*";
	protected static final String ATTRIBUTE_VALUE = "[\\w\\-\\.]+";

	protected AbstractParser() {}

	@Override
	public Element parse(String document) {
		if (document == null) {
			throw new NoSuchElementException(ERROR_MSG_NO_ELEMENT);
		}
		
		String name = getElementName(document);
		String content = getElementContent(document);
		Map<String, String> attributes = getElementAttributes(document);
		
		if (content == null) {
			return new InlineElement(name, attributes);
		} else {
			return new BlockElement(name, content, attributes);
		}
	}

	protected abstract String getElementName(String document);
	protected abstract String getElementContent(String document);
	protected abstract  Map<String, String> getElementAttributes(String socument);

}
