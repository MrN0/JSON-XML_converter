package converter.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import converter.element.Element;

class JsonBuilderTest {

	private Builder builderUnderTest;

	@BeforeEach
	void initParser() {
		builderUnderTest = new JsonBuilder();
	}

	@AfterEach
	void undefParser() {
		builderUnderTest = null;
	}

	@Test
	void testConstructJSONwithValue() {
		Element element = new Element("jdk", "1.8.9");
		String document = builderUnderTest.build(element);
		assertEquals("{\"jdk\":\"1.8.9\"}", document);
	}

	@Test
	void testConstructJSONwithoutValue() {
		Element element = new Element("storage", null);
		String document = builderUnderTest.build(element);
		assertEquals("{\"storage\":null}", document);
	}

}
