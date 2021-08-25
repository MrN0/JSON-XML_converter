package converter.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import converter.element.Element;

class XmlBuilderTest {

	private Builder builderUnderTest;

	@BeforeEach
	void initParser() {
		builderUnderTest = new XmlBuilder();
	}

	@AfterEach
	void undefParser() {
		builderUnderTest = null;
	}

	@Test
	void testConstructXMLwithValue() {
		Element element = new Element("host", "127.0.0.1");
		String document = builderUnderTest.build(element);
		assertEquals("<host>127.0.0.1</host>", document);
	}

	@Test
	void testConstructXMLwithoutValue() {
		Element element = new Element("success", null);
		String document = builderUnderTest.build(element);
		assertEquals("<success/>", document);
	}

}
