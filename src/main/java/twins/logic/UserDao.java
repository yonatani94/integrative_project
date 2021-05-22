package twins.logic;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import twins.data.ItemEntity;
import twins.data.UserEntity;

public interface UserDao extends PagingAndSortingRepository<UserEntity, String> {

	
	
}
