package twins.logic;

import org.springframework.data.repository.CrudRepository;

import restapi.ItemEntity;

public interface ItemDao  extends CrudRepository<ItemEntity, String>{

}
