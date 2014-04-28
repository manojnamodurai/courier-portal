package com.liftoff.courier.jaxb;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.liftoff.courier.jaxb.request.User;
import com.liftoff.courier.jaxb.response.StatusResponseBean;

/**
 * The Class OrderDetails.
 * 
 * @author manoj.n
 * @version 1.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "order")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OrderDetails extends StatusResponseBean {

	/** The order id. */
	@XmlElement(name = "order_id")
	protected long orderId;
	
	/** The user. */
	@XmlElement(name = "sender")
	protected User user;
	
	/** The receiver details. */
	@XmlElement(name = "receiver")
	protected User receiverDetails;
	
	/** The goods details. */
	@XmlElement(name = "goods")
	protected GoodsDetails goodsDetails;
	
	/** The status. */
	@XmlElement(name = "delivery_status")
	protected String status;
	
	/** The created date. */
	@XmlElement(name = "order_date")
	protected String createdDate;
	
	/**
	 * Gets the order id.
	 *
	 * @return the order id
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * Sets the order id.
	 *
	 * @param orderId the new order id
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the receiver details.
	 *
	 * @return the receiver details
	 */
	public User getReceiverDetails() {
		return receiverDetails;
	}

	/**
	 * Sets the receiver details.
	 *
	 * @param receiverDetails the new receiver details
	 */
	public void setReceiverDetails(User receiverDetails) {
		this.receiverDetails = receiverDetails;
	}

	/**
	 * Gets the goods details.
	 *
	 * @return the goods details
	 */
	public GoodsDetails getGoodsDetails() {
		return goodsDetails;
	}

	/**
	 * Sets the goods details.
	 *
	 * @param goodsDetails the new goods details
	 */
	public void setGoodsDetails(GoodsDetails goodsDetails) {
		this.goodsDetails = goodsDetails;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate the new created date
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
