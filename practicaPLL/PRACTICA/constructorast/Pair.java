package constructorast;


public class Pair<elem1,elem2>{
	private elem1 key;
	private elem2 value;

	public Pair(elem1 key, elem2 value) {
		this.key = key;
		this.value = value;
	}

	public elem1 getKey() {
		return key;
	}

	public void setKey(elem1 key) {
		this.key = key;
	}

	public elem2 getValue() {
		return value;
	}

	public void setValue(elem2 value) {
		this.value = value;
	}
	
}
