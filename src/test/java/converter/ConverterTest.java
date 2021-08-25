package converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import converter.builder.Builder;
import converter.parser.Parser;

@ExtendWith(MockitoExtension.class)
class ConverterTest {

	@Mock
	Parser parser;
	
	@Mock
	Builder builder;
	
	@Test
	void testConverter() {
		// GIVEN
		Converter converter = new Converter(parser, builder);
		when(converter.convert("<test>Some Document</test>"))
		.thenReturn("{\"test\":\"Some Document\"}");
		
		// WHEN
		String result = converter.convert("<test>Some Document</test>");
		
		// THEN
		assertThat(result).isEqualTo("{\"test\":\"Some Document\"}");
	}

}
