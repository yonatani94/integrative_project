package twins.logic;

import java.util.List;

import twins.operationsAPI.OperationBoundary;

public interface AdvancedOpretionsService extends OperationsService{
	
	
	public List<OperationBoundary> getAllOperations(String userSpace, String userEmail,int size, int page);

}
