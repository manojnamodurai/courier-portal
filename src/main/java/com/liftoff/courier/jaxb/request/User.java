package com.liftoff.courier.jaxb.request;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.liftoff.courier.jaxb.Address;
import com.liftoff.courier.jaxb.Contact;

/**
 * The Class User.
 * 
 * @author manoj.n
 * @version 1.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "user")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class User {

	/** The id. */
	@XmlTransient
	protected long id;
	
	/** The username. */
	@XmlElement(name = "username")
	protected String username;
	
	/** The password. */
	@XmlElement(name = "password")
	protected String password;
	
	/** The address. */
	@XmlElement(name = "address")
	protected Address address;
	
	/** The contact. */
	@XmlElement(name = "contact")
	protected Contact contact;
	
	/** The created_date. */
	@XmlElement(name = "created_date")
	protected Timestamp created_date;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Gets the contact.
	 *
	 * @return the contact
	 */
	public Contact getContact() {
		return contact;
	}

	/**
	 * Sets the contact.
	 *
	 * @param contact the new contact
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}

	/**
	 * Gets the created_date.
	 *
	 * @return the created_date
	 */
	public Timestamp getCreated_date() {
		return created_date;
	}

	/**
	 * Sets the created_date.
	 *
	 * @param created_date the new created_date
	 */
	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}
	
}
