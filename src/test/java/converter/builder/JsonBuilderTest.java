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

@DisplayName("JsonBuilder Test")
class JsonBuilderTest {

	private final static String ELEMENT_NAME = "element_name";
	private final static String ELEMENT_CONTENT = "element content";
	private final static String ATTRIBUTE_KEY = "jdk";
	private final static String ATTRIBUTE_VALUE = "1.8.9";

	private Builder builderUnderTest;

	@BeforeEach
	void init() {
		builderUnderTest = new JsonBuilder();
	}

	@Test
	@DisplayName("Builds JSON when element has content and without attributes")
	void shouldBuildJsonWithContentWithoutAttributes() {
		// GIVEN
		String expected = String.format("{\"%s\":\"%s\"}", ELEMENT_NAME, ELEMENT_CONTENT);
		Element element = new BlockElement(ELEMENT_NAME, ELEMENT_CONTENT, null);
		// WHEN
		String result = builderUnderTest.build(element);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Builds Json when element content is null and without attributes")
	void shouldBuildJsonWithoutContentWithoutAttributes() {
		// GIVEN
		String expected = String.format("{\"%s\":null}", ELEMENT_NAME);
		Element element = new InlineElement(ELEMENT_NAME, null);
		// WHEN
		String result = builderUnderTest.build(element);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Builds JSON when element has content and with attributes")
	void shouldBuildJsonWithContentWithAttributes() {
		// GIVEN
		String expected = String.format("{\"%s\":{\"@%s\":\"%s\",\"#%s\":\"%s\"}}", ELEMENT_NAME, ATTRIBUTE_KEY,
				ATTRIBUTE_VALUE, ELEMENT_NAME, ELEMENT_CONTENT);
		Element element = new BlockElement(ELEMENT_NAME, ELEMENT_CONTENT, Map.of(ATTRIBUTE_KEY, ATTRIBUTE_VALUE));
		// WHEN
		String result = builderUnderTest.build(element);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Builds JSON when element content is null and with attributes")
	void shouldBuildJsonWithoutContentWithAttributes() {
		// GIVEN
		String expected = String.format("{\"%s\":{\"@%s\":\"%s\",\"#%s\":null}}", ELEMENT_NAME, ATTRIBUTE_KEY,
				ATTRIBUTE_VALUE, ELEMENT_NAME);
		Element element = new BlockElement(ELEMENT_NAME, null, Map.of(ATTRIBUTE_KEY, ATTRIBUTE_VALUE));
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
