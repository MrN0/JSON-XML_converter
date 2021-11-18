package converter.element.impl;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import converter.element.Element;

public abstract class AbstractElement implements Element {

	private final String name;
	private final Map<String, String> attributes;

	protected AbstractElement(String name, Map<String, String> attributes) {
		super();
		this.name = name;
		this.attributes = attributes == null ? Map.of() : Collections.unmodifiableMap(attributes);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Map<String, String> getAttributes() {
		return attributes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(attributes, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof AbstractElement))
			return false;
		AbstractElement other = (AbstractElement) obj;
		return Objects.equals(attributes, other.attributes) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "AbstractElement [name=" + name + ", attributes=" + attributes + "]";
	}

}
