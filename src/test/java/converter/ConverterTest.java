package converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import converter.builder.Builder;
import converter.parser.Parser;

@ExtendWith(MockitoExtension.class)
class ConverterTest {

	private static final String XML_ELEMENT = "<test>Some value</test>";
	private final static String JSON_ELEMENT = "{\"test\":\"Some Document\"}";

	@Mock
	Parser parser;

	@Mock
	Builder builder;

	@Test
	@DisplayName("Converts XML element into JSON element")
	void shouldConvertXmlElementIntoJsonElement() {
		// GIVEN
		Converter converter = new Converter(parser, builder);
		when(converter.convert(XML_ELEMENT)).thenReturn(JSON_ELEMENT);
		// WHEN
		String result = converter.convert(XML_ELEMENT);
		// THEN
		assertEquals(JSON_ELEMENT, result);
	}

	@Test
	@DisplayName("Converts JSON element into XML element")
	void shouldConvertJsonElementIntoXmlnElement() {
		// GIVEN
		Converter converter = new Converter(parser, builder);
		when(converter.convert(JSON_ELEMENT)).thenReturn(XML_ELEMENT);
		// WHEN
		String result = converter.convert(JSON_ELEMENT);
		// THEN
		assertEquals(XML_ELEMENT, result);
	}

}
