package converter.element.impl;

import java.util.Map;

public class InlineElement extends AbstractElement {

	public InlineElement(String name, Map<String, String> attributes) {
		super(name, attributes);
	}

	@Override
	public String getContent() {
		return null;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof InlineElement))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InlineElement [name=" + super.getName() + ", attributes=" + super.getAttributes() + "]";
	}

}
