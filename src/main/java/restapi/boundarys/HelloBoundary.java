package restapi.boundarys;

import java.util.Date;

// JSON SAMPLE: {"message":"hello", "counter":55, "messageTimestamp":"2021-03-03T16:15:12'000"}
public class HelloBoundary {
	private String message;
	private int counter;
	private Date messageTimestamp;

	public HelloBoundary() {
	}

	public HelloBoundary(String message, int counter) {
		super();
		this.message = message;
		this.counter = counter;
		this.messageTimestamp = new Date();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public Date getMessageTimestamp() {
		return messageTimestamp;
	}
	
	public void setMessageTimestamp(Date messageTimestamp) {
		this.messageTimestamp = messageTimestamp;
	}

	@Override
	public String toString() {
		return "HelloBoundary [message=" + message + ", counter=" + counter + ", messageTimestamp=" + messageTimestamp
				+ "]";
	}
	
	
}



