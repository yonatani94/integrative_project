package twins.adminAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import twins.logic.UsersService;
import twins.userAPI.UserBoundary;
import twins.userAPI.UserID;

@RestController
public class AdminController {
	private UsersService userService;
	
	
	  @Autowired 
	  public void setUserService(UsersService userService) {
	  this.userService = userService; 
	  }
	 
	 

	@RequestMapping(path = "/twins/admin/users/{userSpace}/{userEmail}", method = RequestMethod.DELETE)
	public void DeleteAllUser(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {

		return;
	}

	@RequestMapping(path = "/twins/admin/items/{userSpace}/{userEmail}", method = RequestMethod.DELETE)
	public void DeleteAllItem(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {
		return;
	}

	@RequestMapping(path = "/twins/admin/operations/{userSpace}/{userEmail}", method = RequestMethod.DELETE)
	public void DeleteAllOperation(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {
		return;
	}

	@RequestMapping(path = "/twins/admin/users/{userSpace}/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary[] getUsers(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {

		List<UserBoundary> rv = this.userService.getAllUsers(userSpace, userEmail);
		return rv.toArray(new UserBoundary[0]);

		/*
		 * UserBoundary[] rv = new UserBoundary[] { new UserBoundary(new
		 * UserID(userSpace, userEmail), "QA", "username", "Cat") };
		 * System.out.println(rv); return rv;
		 */
	}

}
