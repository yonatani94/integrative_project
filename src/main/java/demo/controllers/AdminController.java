package demo.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.boundarys.ItemBoundary;
import demo.boundarys.OperationBoundary;
import demo.boundarys.UserBoundary;
import demo.models.CreatedBy;
import demo.models.ItemID;
import demo.models.Location;
import demo.models.OperationId;
import demo.models.UserID;

@RestController
public class AdminController {
	/////
	@RequestMapping(path = "/twins/admin/users/{userSpace}/{userEmail}",
			method = RequestMethod.DELETE)
	public void DeleteAllUser(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {
		
		return;
	}

	@RequestMapping(path = "/twins/admin/items/{userSpace}/{userEmail}", 
			method = RequestMethod.DELETE)
	public void DeleteAllItem(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {
		return;
	}

	@RequestMapping(path = "/twins/admin/operations/{userSpace}/{userEmail}", 
			method = RequestMethod.DELETE)
	public void DeleteAllOperation(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {
		return;
	}

	@RequestMapping(path = "/twins/admin/users/{userSpace}/{userEmail}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary[] getUsers(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {

		UserBoundary[] rv = new UserBoundary[] {
				new UserBoundary(new UserID(userSpace, userEmail), "QA", "username", "Cat") };
		System.out.println(rv);
		return rv;
	}

}
