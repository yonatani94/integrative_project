package twins;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import twins.digitalItemAPI.ItemBoundary;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTests {
	private int port;
	private RestTemplate restTemplate;
	
	

	@LocalServerPort
	public void setPort(int port) {
		this.port = port;
	}
	
	@PostConstruct
	public void init()
	{
		this.restTemplate = new RestTemplate();
	}
	
	
	@AfterEach
	public void tearDown()
	{
		String url = "http://localhost:" + port + "/twins/admin/items/{userSpace}/{userEmail}";
		this.restTemplate
		.delete(url,"theSpace", "email@gmail.com");
		
	}
	
	@Test
	public void testContext() {
		
		
	}
	
	
	@Test
	public void testGetAllItemsWithEmptyDB() throws Exception
	{
		
		String url = "http://localhost:" + port + "/twins/items/{userSpace}/{userEmail}";
		
		ItemBoundary[] all = 
		this.restTemplate
		.getForObject(url, ItemBoundary[].class,
				"theSpace", "email@gmail.com");
		
		assertThat(all)
		.isNotNull()
		.isEmpty();
		
	
	}
}
