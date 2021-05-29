package twins.operationsAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import twins.logic.AdvancedOperationsService;
import twins.logic.OperationsService;

@RestController
public class OperationsRelatedController {

	// public String SPACE = "2021.project";
	private OperationsService operationsService;
	private AdvancedOperationsService advancedOperations;

	@Autowired
	public void setOperationsService(OperationsService operationsService) {
		this.operationsService = operationsService;
	}

	@RequestMapping(path = "/twins/admin/operations/{userSpace}/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OperationBoundary> getOperations(
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@PathVariable("userSpace") String userSpace, @PathVariable("userEmail") String userEmail) {
		return this.advancedOperations.getAllOperations(userSpace, userEmail, size, page);
	}

	@RequestMapping(path = "/twins/operations", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object invokeOperation(@RequestBody OperationBoundary operationBoundary) {
		return this.operationsService.invokeOperation(operationBoundary);
	}

	@RequestMapping(path = "/twins/operations/async", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object invokeAsyncOperation(@RequestBody OperationBoundary operationBoundary) {
		return this.advancedOperations.sendAndForget(operationBoundary);
	}

	@Autowired
	public void setAdvanceOperations(AdvancedOperationsService advanceOperations) {
		this.advancedOperations = advanceOperations;
	}

}
