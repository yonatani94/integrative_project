package twins.logic;

import org.springframework.data.repository.PagingAndSortingRepository;

import twins.data.OperationEntity;

public interface OperationDao extends PagingAndSortingRepository<OperationEntity, String> {

}
