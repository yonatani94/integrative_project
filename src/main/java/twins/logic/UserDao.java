package twins.logic;

import org.springframework.data.repository.CrudRepository;

import restapi.UserEntity;

public interface UserDao extends CrudRepository<UserEntity, String>{

}
