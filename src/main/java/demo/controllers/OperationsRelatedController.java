package demo.controllers;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import demo.boundarys.OperationBoundary;
import demo.models.OperationId;

@RestController
public class OperationsRelatedController {

	@RequestMapping(
			path = "/twins/operations",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public OperationBoundary invokeOperationOnItem(@RequestBody OperationBoundary operationBoundary) {
		operationBoundary.setOperationId(new OperationId("asd", "123"));
		return operationBoundary;
	}
}
