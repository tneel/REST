package tn.rest.userservice.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "users")
public class UserList implements Serializable {

	static HashMap<Long, User> users = new HashMap<Long, User>();

	static {
		users.put(1L, new User(1L, "Stephen Smith"));
		users.put(2L, new User(2L, "John Doe"));
	}

	public UserList() {
	}

	public User getUser(Long userId) {
		return users.get(userId);
	}

	@XmlElement(name = "user")
	public List<User> getUsers() {
		return (List<User>) new ArrayList<User>(users.values());
	}

	public User delete(long userId) {
        return users.remove(userId);
    }
	
    public User update(User user) {
//    	Will return the original user or null if it does not exist
        return users.put(user.getId(), user);
    }
 
    
	public User add(User aUser) {
		users.put(aUser.getId(), aUser);
		return aUser;
	}
}
