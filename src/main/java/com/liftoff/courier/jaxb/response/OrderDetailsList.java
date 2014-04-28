package com.liftoff.courier.jaxb.response;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.liftoff.courier.jaxb.OrderDetails;

/**
 * The Class OrderDetailsList.
 * 
 * @author manoj.n
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "delivery")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OrderDetailsList extends StatusResponseBean {

	/** The type. */
	@XmlElement(name = "type")
	protected String type;
	
	/** The period. */
	@XmlElement(name = "period")
	protected String period;
	
	/** The order details. */
	@XmlElement(name = "orders_list")
	protected ArrayList<OrderDetails> orderDetails;

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the period.
	 *
	 * @return the period
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * Sets the period.
	 *
	 * @param period the new period
	 */
	public void setPeriod(String period) {
		this.period = period;
	}

	/**
	 * Gets the order details.
	 *
	 * @return the order details
	 */
	public ArrayList<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	/**
	 * Sets the order details.
	 *
	 * @param orderDetails the new order details
	 */
	public void setOrderDetails(ArrayList<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
