package com.liftoff.courier.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * The Class Address.
 *
 * @author manoj.n
 * @version 1.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "address")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Address{
	
	/** The id. */
	@XmlTransient
	protected long id;
	
	/** The name. */
	@XmlElement(name = "name")
	protected String name;
	
	/** The street1. */
	@XmlElement(name = "street1")
	protected String street1;
	
	/** The street2. */
	@XmlElement(name = "street2")
	protected String street2;
	
	/** The city. */
	@XmlElement(name = "city")
	protected String city;
	
	/** The state. */
	@XmlElement(name = "state")
	protected String state;
	
	/** The country. */
	@XmlElement(name = "country")
	protected String country;
	
	/** The zipcode. */
	@XmlElement(name = "zipcode")
	protected String zipcode;

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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the street1.
	 *
	 * @return the street1
	 */
	public String getStreet1() {
		return street1;
	}

	/**
	 * Sets the street1.
	 *
	 * @param street1 the new street1
	 */
	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	/**
	 * Gets the street2.
	 *
	 * @return the street2
	 */
	public String getStreet2() {
		return street2;
	}

	/**
	 * Sets the street2.
	 *
	 * @param street2 the new street2
	 */
	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the zipcode.
	 *
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * Sets the zipcode.
	 *
	 * @param zipcode the new zipcode
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
}