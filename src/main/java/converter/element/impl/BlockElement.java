package converter.element.impl;

import java.util.Map;
import java.util.Objects;

public class BlockElement extends AbstractElement {

	private String content;

	public BlockElement(String name, String content, Map<String, String> attributes) {
		super(name, attributes);
		this.content = content;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(content);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof BlockElement))
			return false;
		BlockElement other = (BlockElement) obj;
		return Objects.equals(content, other.content);
	}

	@Override
	public String toString() {
		return "BlockElement [name=" + getName() + 
				", content=" + content + 
				", attributes=" + getAttributes() + "]";
	}

}
