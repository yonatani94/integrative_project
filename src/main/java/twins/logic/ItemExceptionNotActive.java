package twins.logic;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)

public class ItemExceptionNotActive extends RuntimeException {

	private static final long serialVersionUID = 6957302919844563827L;

	public ItemExceptionNotActive() {
		super("Item is not Active");
	}

	public ItemExceptionNotActive(String message) {
		super(message);
	}

	public ItemExceptionNotActive(Throwable cause) {
		super(cause);
	}

	public ItemExceptionNotActive(String message, Throwable cause) {
		super(message, cause);
		
	}
}
