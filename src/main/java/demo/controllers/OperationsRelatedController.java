package demo.controllers;

import java.util.Date;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import demo.boundarys.OperationBoundary;

@RestController
public class OperationsRelatedController {

	@RequestMapping(
			path = "/twins/operations",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public OperationBoundary invokeOperationOnItem(@RequestBody OperationBoundary operationBoundary) {
		OperationBoundary boundary = new OperationBoundary(null, null, null, null, null, null);
		boundary.toString();
		return operationBoundary;
	}
}
