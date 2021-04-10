package twins.operationsAPI;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import twins.digitalItemAPI.Item;
import twins.digitalItemAPI.ItemID;
import twins.userAPI.UserID;

@RestController
public class OperationsRelatedController {

	@RequestMapping(path = "/twins/admin/operations/{userSpace}/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public OperationBoundary[] getOperations(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {

		// Maps for testing
		Map<String, Object> nestedOpAttributes = new HashMap<>();
		nestedOpAttributes.put("Key2Subkey", "can be nested json");
		nestedOpAttributes.put("Subkey2", "SubValue2");
		Map<String, Object> opAttributes = new HashMap<>();
		opAttributes.put("key1", "can be set to any value you wish");
		opAttributes.put("Key2", nestedOpAttributes);

		OperationBoundary[] rv = new OperationBoundary[] { new OperationBoundary(new OperationId(userSpace, userEmail),
				"operationType", new Item(new ItemID(userSpace, userEmail)), new Date(),
				new InvokedBy(new UserID(userSpace, userEmail)), opAttributes) };
		System.out.println(rv);
		return rv;
	}

	@RequestMapping(path = "/twins/operations", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public OperationBoundary invokeOperationOnItem(@RequestBody OperationBoundary operationBoundary) {
		operationBoundary.setOperationId(new OperationId("asd", "123"));
		return operationBoundary;
	}
}
