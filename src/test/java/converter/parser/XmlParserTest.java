package converter.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import converter.element.Element;
import converter.element.impl.BlockElement;
import converter.element.impl.InlineElement;
import converter.exception.InvalidElementException;

@DisplayName("XmlParser Test")
class XmlParserTest {

	private final static String ELEMENT_NAME = "element_name";
	private final static String ELEMENT_CONTENT = "element content";
	private final static String ATTRIBUTE_KEY = "jdk";
	private final static String ATTRIBUTE_VALUE = "1.8.9";

	private Parser parserUnderTest;

	@BeforeEach
	void init() {
		parserUnderTest = new XmlParser();
	}

	@Test
	@DisplayName("Extracts XML block element without content")
	void shouldExtractXmlBlockElementWithoutContent() {
		// GIVEN
		String element = String.format("<%s></%s>", ELEMENT_NAME, ELEMENT_NAME);
		Element expected = new InlineElement(ELEMENT_NAME, null);
		// WHEN
		Element result = parserUnderTest.parse(element);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Extracts XML inline element without attributes")
	void shouldExtractXmlInlineElementWithoutAttributes() {
		// GIVEN
		String element = String.format("<%s/>", ELEMENT_NAME);
		Element expected = new InlineElement(ELEMENT_NAME, null);
		// WHEN
		Element result = parserUnderTest.parse(element);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Extracts XML block element without attributes")
	void shouldExtractXmlBlockElementWithoutAttributes() {
		// GIVEN
		String element = String.format("<%s>%s</%s>", ELEMENT_NAME, ELEMENT_CONTENT, ELEMENT_NAME);
		Element expected = new BlockElement(ELEMENT_NAME, ELEMENT_CONTENT, null);
		// WHEN
		Element result = parserUnderTest.parse(element);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Extracts XML block element with attributes")
	void shouldExtractXmlBlockElementWithAttributes() {
		// GIVEN
		String attribute1 = String.format("%s=\"%s\"", ATTRIBUTE_KEY + 1, ATTRIBUTE_VALUE);
		String attribute2 = String.format("%s=\"%s\"", ATTRIBUTE_KEY + 2, ATTRIBUTE_VALUE);
		String element = String.format("<%s %s %s>%s</%s>", 
				ELEMENT_NAME, attribute1, attribute2, ELEMENT_CONTENT, ELEMENT_NAME);
		Element expected = new BlockElement(
				ELEMENT_NAME, 
				ELEMENT_CONTENT, 
				Map.of(ATTRIBUTE_KEY + 1, ATTRIBUTE_VALUE, ATTRIBUTE_KEY + 2, ATTRIBUTE_VALUE));
		// WHEN
		Element result = parserUnderTest.parse(element);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Extracts XML inline element with attributes")
	void shouldExtractXmlInlineElementWithAttributes() {
		// GIVEN
		String attribute1 = String.format("%s=\"%s\"", ATTRIBUTE_KEY + 1, ATTRIBUTE_VALUE);
		String attribute2 = String.format("%s=\"%s\"", ATTRIBUTE_KEY + 2, ATTRIBUTE_VALUE);
		String element = String.format("<%s %s %s/>", ELEMENT_NAME, attribute1, attribute2);
		Element expected = new InlineElement(ELEMENT_NAME, 
				Map.of(ATTRIBUTE_KEY + 1, ATTRIBUTE_VALUE, ATTRIBUTE_KEY + 2, ATTRIBUTE_VALUE));
		// WHEN
		Element result = parserUnderTest.parse(element);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Throws NoSuchElementException if XML element does not exist")
	void shouldThrowNoSuchElementExceptionIfElementDoesNotExist() {
		assertThrows(NoSuchElementException.class, () -> parserUnderTest.parse(null));
	}

	@Test
	@DisplayName("Throws InvalidElementException if XML element's name is invalid")
	void shouldThrowInvalidElementExceptionIfXmlElementNameIsInvalid() {
		String element = "<!name/>";
		assertThrows(InvalidElementException.class, () -> parserUnderTest.parse(element));
	}

}
