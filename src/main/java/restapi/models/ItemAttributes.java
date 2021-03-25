package restapi.models;

public class ItemAttributes {
	
	private String key1;
	private String key2;
	private int key3;
	private boolean key4;
	
	public ItemAttributes() {
		super();
	}
	
	public ItemAttributes(String key1, String key2, int key3, boolean key4) {
		super();
		this.key1 = key1;
		this.key2 = key2;
		this.key3 = key3;
		this.key4 = key4;
	}

	public String getKey1() {
		return key1;
	}

	public void setKey1(String key1) {
		this.key1 = key1;
	}

	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {
		this.key2 = key2;
	}

	public int getKey3() {
		return key3;
	}

	public void setKey3(int key3) {
		this.key3 = key3;
	}

	public boolean isKey4() {
		return key4;
	}

	public void setKey4(boolean key4) {
		this.key4 = key4;
	}

	
	
	

}
