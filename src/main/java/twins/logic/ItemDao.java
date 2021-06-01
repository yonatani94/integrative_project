package twins.logic;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import twins.data.ItemEntity;
import twins.digitalItemAPI.ItemBoundary;

public interface ItemDao extends PagingAndSortingRepository<ItemEntity, String> {

	public List<ItemEntity> findAllByActive(@Param("active") boolean active, Pageable pageable);

	public List<ItemEntity> findAllByType(@Param("type") String type);

}
