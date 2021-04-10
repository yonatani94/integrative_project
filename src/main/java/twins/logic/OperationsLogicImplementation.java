package twins.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import twins.operationsAPI.OperationBoundary;
@Service
public class OperationsLogicImplementation implements OperationsService{
	private ItemDao itemDao;
	private ObjectMapper jackson;
	
	@Autowired
	public OperationsLogicImplementation(ItemDao itemDao, ObjectMapper jackson) {
		super();
		this.itemDao = itemDao;
		this.jackson =new ObjectMapper();
		}

	@Override
	public Object invokeOperation(OperationBoundary operation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationBoundary invokeAsynchronousOperation(OperationBoundary operation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OperationBoundary> getAllOperations(String adminSpace, String adminEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllOperations(String adminSpace, String adminEmail) {
		// TODO Auto-generated method stub
		
	}

}
