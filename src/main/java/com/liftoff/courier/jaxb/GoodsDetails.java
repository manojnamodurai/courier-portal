package com.liftoff.courier.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The Class GoodsDetails.
 * 
 * @author manoj.n
 * @version 1.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "goods")
public class GoodsDetails {

	/** The id. */
	@XmlTransient
	protected long id;
	
	/** The weight. */
	@XmlElement(name = "weight")
	protected String weight;
	
	/** The descripton. */
	@XmlElement(name = "description")
	protected String descripton;

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
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * Sets the weight.
	 *
	 * @param weight the new weight
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * Gets the descripton.
	 *
	 * @return the descripton
	 */
	public String getDescripton() {
		return descripton;
	}

	/**
	 * Sets the descripton.
	 *
	 * @param descripton the new descripton
	 */
	public void setDescripton(String descripton) {
		this.descripton = descripton;
	}
}
