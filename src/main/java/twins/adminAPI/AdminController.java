package twins.adminAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import twins.logic.AdvancedItemsService;
import twins.logic.AdvancedUsersService;
import twins.logic.ItemsService;
import twins.logic.OperationsService;
import twins.logic.UsersService;
import twins.userAPI.UserBoundary;

@RestController
public class AdminController {
	private UsersService userService;
	private OperationsService operationsService;
	private ItemsService itemsService;
	private AdvancedUsersService advanceUsers;

	@Autowired
	public void setUserService(UsersService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setOperationsService(OperationsService operationsService) {
		this.operationsService = operationsService;
	}

	@Autowired
	public void setItemsService(ItemsService itemsService) {
		this.itemsService = itemsService;
	}
	@Autowired
	public void setAdvanceItem(AdvancedUsersService advanceUsers) {
		this.advanceUsers = advanceUsers;
	}
	@RequestMapping(path = "/twins/admin/users/{userSpace}/{userEmail}", method = RequestMethod.DELETE)
	public void DeleteAllUser(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {
		this.userService.deleteAllUsers(userSpace, userEmail);
	}

	@RequestMapping(path = "/twins/admin/items/{userSpace}/{userEmail}", method = RequestMethod.DELETE)
	public void DeleteAllItem(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {
		this.itemsService.deleteAllItems(userSpace, userEmail);
	}

	@RequestMapping(path = "/twins/admin/operations/{userSpace}/{userEmail}", method = RequestMethod.DELETE)
	public void DeleteAllOperation(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {
		this.operationsService.deleteAllOperations(userSpace, userEmail);
	}

	@RequestMapping(path = "/twins/admin/users/{userSpace}/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserBoundary> getUsers(
			@RequestParam(name= "size", required = false , defaultValue = "10" ) int size,
			@RequestParam(name= "page", required = false , defaultValue = "0" ) int page,
			@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {
		return  this.advanceUsers.getAllUsers(userSpace,userSpace,size, page);

	}

	

}
