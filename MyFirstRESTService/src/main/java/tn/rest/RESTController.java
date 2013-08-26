package tn.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import tn.rest.userservice.UserService;
import tn.rest.userservice.domain.User;
import tn.rest.userservice.domain.UserList;

@Controller
public class RESTController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathVariable Long id,
			@RequestHeader("Accept") String anAcceptHeaderValue) {
		traceAcceptHeader("UserService", anAcceptHeaderValue);
		return userService.getUser(id);
	}

	// <?xml version="1.0" encoding="UTF-8" standalone="yes"?><user><id>2</id><name>John Doe</name></user>
	//NEED TO SET THE ContentType HEADER !!!
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public User add(@RequestBody User user,
			@RequestHeader("Accept") String anAcceptHeaderValue) {
		traceAcceptHeader("UserService", anAcceptHeaderValue);
		logger.trace("Creating new user {}", user);
		return userService.addUser(user);
	}

	// <?xml version="1.0" encoding="UTF-8" standalone="yes"?><user><id>2</id><name>NEW John Doe</name></user>
	//NEED TO SET THE ContentType HEADER !!!
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@RequestBody User user,
			@RequestHeader("Accept") String anAcceptHeaderValue) {
		traceAcceptHeader("UserService", anAcceptHeaderValue);
		logger.trace("Updating user {}", user);
		User updatedUser = userService.updateUser(user);
		if (updatedUser == null) {
			traceUnknownUser(user.getId());
		}
	}	
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "id") long id,
			@RequestHeader("Accept") String anAcceptHeaderValue) {
		traceAcceptHeader("UserService", anAcceptHeaderValue);
		logger.trace("Deleting user with id {}", id);
		User deletedUser = userService.deleteUser(id);
		if (deletedUser == null) {
			traceUnknownUser(id);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public UserList getUsers(@RequestHeader("Accept") String anAcceptHeaderValue) {
		traceAcceptHeader("UserService", anAcceptHeaderValue);
		return userService.getUsers();
	}

	private void traceAcceptHeader(String aServicename,
			String anAcceptHeaderValue) {
		logger.trace("{} serving resource for Accept header: {}", aServicename,
				anAcceptHeaderValue);
	}

	private void traceUnknownUser(Long aUserId) {
		logger.trace("User with id {} does not exist", aUserId);
	}
}
