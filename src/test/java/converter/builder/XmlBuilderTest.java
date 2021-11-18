package converter.builder;

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

@DisplayName("XmlBuilder Test")
class XmlBuilderTest {

	private final static String ELEMENT_NAME = "element_name";
	private final static String ELEMENT_CONTENT = "element content";
	private final static String ATTRIBUTE_KEY = "jdk";
	private final static String ATTRIBUTE_VALUE = "1.8.9";

	private Builder builderUnderTest;

	@BeforeEach
	void init() {
		builderUnderTest = new XmlBuilder();
	}

	@Test
	@DisplayName("Builds XML when element has content")
	void shouldBuildXmlWithContent() {
		// GIVEN
		String expected = String.format("<%s>%s</%s>", ELEMENT_NAME, ELEMENT_CONTENT, ELEMENT_NAME);
		Element element = new BlockElement(ELEMENT_NAME, ELEMENT_CONTENT, null);
		// WHEN
		String result = builderUnderTest.build(element);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Builds XML when element content is null")
	void shouldBuildXmlWithoutContent() {
		// GIVEN
		String expected = String.format("<%s/>", ELEMENT_NAME);
		Element element = new InlineElement(ELEMENT_NAME, null);
		// WHEN
		String result = builderUnderTest.build(element);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Builds XML when element has content and with attributes")
	void shouldBuildXmlWithContentAndWithAttributes() {
		// GIVEN
		String attribute = " " + ATTRIBUTE_KEY + "=\"" + ATTRIBUTE_VALUE + "\"";
		String expected = String.format("<%s%s>%s</%s>", ELEMENT_NAME, attribute, ELEMENT_CONTENT, ELEMENT_NAME);
		Element element = new BlockElement(ELEMENT_NAME, ELEMENT_CONTENT, Map.of(ATTRIBUTE_KEY, ATTRIBUTE_VALUE));
		// WHEN
		String result = builderUnderTest.build(element);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Builds XML when element content is null and with attributes")
	void shouldBuildXmlWithoutcontentAndWithAttributes() {
		// GIVEN
		String attribute = " " + ATTRIBUTE_KEY + "=\"" + ATTRIBUTE_VALUE + "\"";
		String expected = String.format("<%s%s/>", ELEMENT_NAME, attribute);
		Element element = new InlineElement(ELEMENT_NAME, Map.of(ATTRIBUTE_KEY, ATTRIBUTE_VALUE));
		// WHEN
		String result = builderUnderTest.build(element);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Throws NoSuchElementException if element is null")
	void shouldThrowNoSuchElementExceptionIfElementIsNull() {
		assertThrows(NoSuchElementException.class, () -> builderUnderTest.build(null));
	}
}
