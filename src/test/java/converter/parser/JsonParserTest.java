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

@DisplayName("JsonParser Test")
class JsonParserTest {

	private final static String ELEMENT_NAME = "element_name";
	private final static String ELEMENT_CONTENT = "element content";
	private final static String ATTRIBUTE_KEY = "jdk";
	private final static String ATTRIBUTE_VALUE = "1.8.9";

	private Parser parserUnderTest;

	@BeforeEach
	void init() {
		this.parserUnderTest = new JsonParser();
	}

	@Test
	@DisplayName("Extracts JSON element with value and without attributes")
	void shouldExtractJsonElementWithValueAndWithoutAttributes() {
		// GIVEN
		String jsonElement = String.format("{\"%s\" : \"%s\"}", ELEMENT_NAME, ELEMENT_CONTENT);
		Element expected = new BlockElement(ELEMENT_NAME, ELEMENT_CONTENT, null);
		// WHEN
		Element result = parserUnderTest.parse(jsonElement);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Extracts JSON element without value and without attributes")
	void shouldExtractJsonElementWithoutValueAndWithoutAttributes() {
		// GIVEN
		String jsonElement = String.format("{\"%s\" : null}", ELEMENT_NAME);
		Element expected = new InlineElement(ELEMENT_NAME, null);
		// WHEN
		Element result = parserUnderTest.parse(jsonElement);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Extracts JSON element with value and attributes")
	void shouldExtractJsonElementWithValueAndAttributes() {
		// GIVEN
		String jsonElement = 
				"{\n"
				+ "    \"" + ELEMENT_NAME + "\" : {\n"
				+ "        \"@" + ATTRIBUTE_KEY + "\" : \"" + ATTRIBUTE_VALUE + "\",\n"
				+ "        \"#" + ELEMENT_NAME + "\" : \"" + ELEMENT_CONTENT + "\"\n"
				+ "    }\n"
				+ "}";
		Element expected = new BlockElement(
				ELEMENT_NAME,
				ELEMENT_CONTENT, 
				Map.of(ATTRIBUTE_KEY, ATTRIBUTE_VALUE));
		// WHEN
		Element result = parserUnderTest.parse(jsonElement);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Extracts JSON element without value and with attributes")
	void shouldExtractJsonElementWithoutValueAndWithAttributes() {
		// GIVEN
		String jsonElement = 
				"{\n"
				+ "    \"" + ELEMENT_NAME + "\" : {\n"
				+ "        \"@" + ATTRIBUTE_KEY + "\" : \"" + ATTRIBUTE_VALUE + "\",\n"
				+ "        \"#" + ELEMENT_NAME + "\" : null\n"
				+ "    }\n"
				+ "}";
		Element expected = new InlineElement(
				ELEMENT_NAME,
				Map.of(ATTRIBUTE_KEY, ATTRIBUTE_VALUE));
		// WHEN
		Element result = parserUnderTest.parse(jsonElement);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Throws NoSuchElementException if JSON element does not exist")
	void shouldThrowNoSuchElementExceptionIfElementDoesNotExist() {
		assertThrows(NoSuchElementException.class, () -> parserUnderTest.parse(null));
	}

	@Test
	@DisplayName("Throws an InvalidElementException if JSON element name is invalid")
	void InvalidElementExceptionIfJsonElementNameIsInvalid() {
		String element = "{\"wrong name\" : \"value\"}";
		assertThrows(InvalidElementException.class, () -> parserUnderTest.parse(element));
	}

	@Test
	@DisplayName("Throws an InvalidElementException if JSON element content is invalid")
	void InvalidElementExceptionIfJsonElementContentIsInvalid() {
		String element = "{\"name\" : }";
		assertThrows(InvalidElementException.class, () -> parserUnderTest.parse(element));
	}

}
