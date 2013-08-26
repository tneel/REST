package tn.rest.userservice;

import tn.rest.userservice.domain.User;
import tn.rest.userservice.domain.UserList;

public interface UserService {

	
	public User getUser(Long id);
	
	public User deleteUser(Long id);
	
	public User addUser(User aUser);
	
	public User updateUser(User aUser);
	
	public UserList getUsers();
}
