package demo.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.boundarys.UserBoundary;
import demo.models.UserID;

@RestController
public class UsersController {
	@RequestMapping(
			path = "/twins/admin/users/{userSpace}/{userEmail}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary[] getUsers(@PathVariable("userSpace") String userSpace, @PathVariable("userEmail") String userEmail) {
		
		UserBoundary[] rv = new UserBoundary[] { new UserBoundary(new UserID(userSpace, userEmail), "QA", "username", "Cat")};
		System.out.println(rv);
		return rv;
	}
}
