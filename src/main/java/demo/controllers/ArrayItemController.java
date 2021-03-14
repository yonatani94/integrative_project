package demo.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.boundarys.ItemBoundary;
import demo.models.CreatedBy;
import demo.models.ItemAttributes;
import demo.models.ItemID;
import demo.models.Location;
import demo.models.OperationId;
import demo.models.UserID;

@RestController
public class ArrayItemController {
	
	@RequestMapping(path = "/twins/items/{userSpace}/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ItemBoundary[] getOperations(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {

		
		ItemBoundary[] rv = new ItemBoundary[] {new ItemBoundary(new ItemID(userSpace,"99"), "demoType", "demo Item", true, "2021-03-07T09:55:05.248+0000", new CreatedBy(new UserID(userSpace,userEmail)), new Location(32.115139,34.817804), new ItemAttributes("can be set to any value you wish",
				"you can also name the attributes any name you like",58,false))};
		
		
		System.out.println(rv);
		return rv;
	}

}
