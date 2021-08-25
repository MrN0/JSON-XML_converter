package converter.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import converter.element.Element;
import converter.exception.InvalidElementException;

class JsonParserTest {

	private Parser parserUnderTest;

	@BeforeEach
	void initParser() {
		this.parserUnderTest = new JsonParser();
	}

	@AfterEach
	void undefParser() {
		parserUnderTest = null;
	}

	@Test
	void testParseJSONwithValue() {
		String document = "{\"jdk\" : \"1.8.9\"}";
		Element element = parserUnderTest.parse(document);
		assertEquals(new Element("jdk", "1.8.9"), element);
	}

	@Test
	void testParseJSONwithoutValue() {
		String document = "{\"storage\" : null}";
		Element element = parserUnderTest.parse(document);
		assertEquals(new Element("storage", null), element);
	}

	@ParameterizedTest(name = "{0} - should throw the InvalidElementException")
	@ValueSource(strings = { "{name : value}" })
	void testInvalidArgument(String document) {
		assertThrows(InvalidElementException.class,
				() -> parserUnderTest.parse(document));
	}

}
