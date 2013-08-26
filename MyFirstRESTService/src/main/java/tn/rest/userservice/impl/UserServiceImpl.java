package tn.rest.userservice.impl;

import tn.rest.userservice.UserService;
import tn.rest.userservice.domain.User;
import tn.rest.userservice.domain.UserList;

public class UserServiceImpl implements UserService {

	private UserList users = new UserList();
	
	public User getUser(Long id) {
		return users.getUser(id);
	}
	
	public User deleteUser(Long id) {
		return users.delete(id);
	}
	
	public User addUser(User aUser) {
		return users.add(aUser);
	}
	
	public User updateUser(User aUser) {
		return users.update(aUser);
	}	
	
	public UserList getUsers() {
		return users;
	}
}
