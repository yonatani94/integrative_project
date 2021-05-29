package twins.logic;

import java.util.List;

import twins.operationsAPI.OperationBoundary;

public interface AdvancedOperationsService extends OperationsService{
	
	
	public List<OperationBoundary> getAllOperations(String userSpace, String userEmail,int size, int page);
	public OperationBoundary sendAndForget(OperationBoundary operation);


}
