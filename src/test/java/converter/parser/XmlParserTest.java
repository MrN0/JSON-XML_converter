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

class XmlParserTest {

	private Parser parserUnderTest;

	@BeforeEach
	void initParser() {
		parserUnderTest = new XmlParser();
	}

	@AfterEach
	void undefParser() {
		parserUnderTest = null;
	}

	@Test
	void testParseXMLwithValue() {
		String document = "<host>127.0.0.1</host>";
		Element element = parserUnderTest.parse(document);
		assertEquals(new Element("host", "127.0.0.1"), element);
	}

	@Test
	void testParseXMLwithoutValue() {
		String document = "<success/>";
		Element element = parserUnderTest.parse(document);
		assertEquals(new Element("success", null), element);
	}

	@ParameterizedTest(name = "{0} - should throw the InvalidElementException")
	@ValueSource(strings = { "<></>" })
	void testInvalidArgument(String document) {
		assertThrows(InvalidElementException.class, 
				() -> parserUnderTest.parse(document));
	}

}
