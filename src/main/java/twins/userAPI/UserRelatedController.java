package twins.userAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import twins.logic.UsersService;

@RestController
public class UserRelatedController {

	//public String SPACE = "2021.project";
	private UsersService userService;
	
	
	  @Autowired 
	  public void setUserService(UsersService userService) {
	  this.userService = userService; 
	  }
	  
	  
	@RequestMapping(path = "/twins/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary createUser(@RequestBody UserBoundary user) {
		return this.userService.createUser(user);
	}

	@RequestMapping(path = "/twins/users/login/{userSpace}/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary getUserSpace(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {
		return this.userService.login(userSpace, userEmail);
	}

	@RequestMapping(path = "/twins/users/{userSpace}/{userEmail}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUser(@PathVariable("userSpace") String userSpace, @PathVariable("userEmail") String userEmail,
			@RequestBody UserBoundary user_boundry) {
		this.userService.updateUser(userSpace, userEmail, user_boundry);
	}
}
