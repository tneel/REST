
package tn.rest.userservice.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The {@link XmlRootElement} annotation is needed for the XML representation.
 * 
 */
@XmlRootElement
public class User {

	private Long id;
	private String name;

	public User() {
	}

	public User(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
