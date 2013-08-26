
package tn.rest.userservice.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import tn.rest.RESTController;
import tn.rest.userservice.domain.User;
import tn.rest.userservice.domain.UserList;

/**
 * Integration test for {@link RESTController}.
 * 
 * 
 */
public class RESTControllerTest {
	Logger logger = LoggerFactory.getLogger(getClass());
	String baseUrl;

	@Before
	public void setUp() throws Exception {
		baseUrl = "http://localhost:8080/userservice/users";
	}

	@Test
	public void getUserWithJSON() {
		RestTemplate restTemplate = createJSONRestTemplate();
		User user = restTemplate.getForObject(baseUrl + "/1", User.class, (Object) null);

		assertNotNull(user);
		assertEquals(Long.valueOf(1), user.getId());
	}

	@Test
	public void getUserWithXML() throws Exception {
		RestTemplate restTemplate = createXMLRestTemplate();
		User user = restTemplate.getForObject(new URI(baseUrl + "/1"), User.class);

		assertNotNull(user);
		assertEquals(Long.valueOf(1), user.getId());
	}

	@Test
	public void getUsersWithJSON() throws Exception {
		RestTemplate restTemplate = createJSONRestTemplate();
		UserList users = restTemplate.getForObject(new URI(baseUrl), UserList.class);

		assertNotNull(users);
		assertEquals(2, users.getUsers().size());		
	}

	@Test
	public void getUsersWithXML() throws Exception {
		RestTemplate restTemplate = createXMLRestTemplate();
		UserList users = restTemplate.getForObject(new URI(baseUrl), UserList.class);

		assertNotNull(users);
		assertEquals(2, users.getUsers().size());
	}	
	
	@Test
	public void getUserWithUnsupportedAccept() throws Exception {
		RestTemplate restTemplate = createStringRestTemplate();

		String user = restTemplate.getForObject(new URI(baseUrl + "/1"), String.class);

		logger.debug("Received data as: {}", user);
	}

	private RestTemplate createJSONRestTemplate() {
		ArrayList<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new MappingJacksonHttpMessageConverter());
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(messageConverters);
		return restTemplate;
	}

	private RestTemplate createXMLRestTemplate() throws Exception {
		ArrayList<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new Jaxb2RootElementHttpMessageConverter());
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(messageConverters);
		return restTemplate;
	}

	private RestTemplate createStringRestTemplate() throws Exception {
		ArrayList<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new StringHttpMessageConverter());
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(messageConverters);
		return restTemplate;
	}
}
