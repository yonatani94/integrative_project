package demo.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import demo.boundarys.UserBoundary;
import demo.models.NewUserDetails;
import demo.models.UserID;

@RestController
public class UserRelatedController {
	
	
	public String SPACE = "2021.project";
	
	@RequestMapping(
			path = "/twins/users",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
		public UserBoundary createUser (@RequestBody NewUserDetails user) {
			// STUB IMPLEMENTATION 
		    UserBoundary rv = new UserBoundary(new UserID(SPACE, user.getEmail()), user.getRole(), user.getUsername(), user.getAvatar());
		    System.out.println(rv);
		
			return rv;
		}

	@RequestMapping(
			path = "/twins/users/login/{userSpace}/{userEmail}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
		public UserBoundary getUserSpace(@PathVariable("userSpace") String userSpace, @PathVariable("userEmail") String userEmail) {
		    UserBoundary rv = new UserBoundary(new UserID(userSpace, userEmail), "DevOps", "YardenDev", "Owl");
		    System.out.println(rv);
			return rv;
		}
	

	@RequestMapping(
			path = "/twins/users/{userSpace}/{userEmail}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE)
		public void updateUser (@PathVariable("userSpace") String userSpace, @PathVariable("userEmail") String userEmail
				,@RequestBody UserBoundary user_boundry) {
			// STUB IMPLEMENTATION 
		}
}
