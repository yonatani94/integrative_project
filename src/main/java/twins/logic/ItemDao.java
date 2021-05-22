package twins.logic;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import twins.data.ItemEntity;

public interface ItemDao extends PagingAndSortingRepository<ItemEntity, String> {
	
	
	public List<ItemEntity> findAllByActive(@Param("active") boolean active,Pageable pageable);

}
