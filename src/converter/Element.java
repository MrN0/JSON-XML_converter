package converter;

public class Element<T> {

	private String key;
	private T value;

	public Element(String key, T value) {
		this.key = key;
		this.value = "null".equals(value) ? null : value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public String getAsXml() {
		var openTag = String.format("<%s>", key);
		var closeTag = String.format("</%s>", key);

		var sb = new StringBuilder();
		if (value == null) {
			sb.append("<").append(key).append("/>");
		} else {
			sb.append(openTag).append(value).append(closeTag);
		}
		return sb.toString();
	}

	public String getAsJson() {
		if (value instanceof String) {
			return String.format("{\"%s\" : \"%s\"}", key, value);
		} else {
			return String.format("{\"%s\" : %s}", key, value);
		}
	}

}
