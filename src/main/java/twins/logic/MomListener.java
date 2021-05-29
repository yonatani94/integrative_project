package twins.logic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import twins.operationsAPI.OperationBoundary;
import org.springframework.transaction.annotation.Transactional;


@Component
public class MomListener {
	private ObjectMapper jackson;
	private OperationsService service;
	
	public MomListener(OperationsService service) {
		this.jackson=new ObjectMapper();
		this.service=service;
	}

	
	@JmsListener(destination="MyOperations")
	@Transactional
	public void handleMessagesFromMom(String json) {
		try {
			OperationBoundary boundary = this.jackson.readValue(json, OperationBoundary.class);
			service.invokeAsynchronousOperation(boundary);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
