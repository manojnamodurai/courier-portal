package com.liftoff.courier.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liftoff.courier.dao.impl.UserDAOImpl;
import com.liftoff.courier.jaxb.request.User;
import com.liftoff.courier.jaxb.response.StatusResponseBean;
import com.liftoff.courier.request.validation.UserRequestValidation;
import com.liftoff.courier.util.ResponseUtils;

/**
 * The Class UserService.
 * 
 * @author manoj.n
 * @version 1.0
 */
@Path("/user")
public class UserService {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	/** The request validation. */
	UserRequestValidation requestValidation = null;
	
	/**
	 * User register.
	 *
	 * @param user the user
	 * @return the status response bean
	 */
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public StatusResponseBean userRegister(User user) {
		logger.debug("----- UserService.userRegister() method begins -----");
		StatusResponseBean responseBean = null;
		UserDAOImpl userDAOImpl = null;
		try {
			requestValidation = new UserRequestValidation();
			if(requestValidation.validateRegistrationRequest(user)){
				userDAOImpl = new  UserDAOImpl();
				if(userDAOImpl.validateUsername(user.getUsername())){
					boolean isRegistered = userDAOImpl.registerUser(user);
					if(isRegistered){
						responseBean = new StatusResponseBean();
						responseBean.setStatusCode(ResponseUtils.SUCCESS_CODE);
						responseBean.setStatusDescription(ResponseUtils.REGISTRATION_SUCCESS_RESPONSE);
					} else {
						responseBean = new StatusResponseBean();
						responseBean.setStatusCode(ResponseUtils.ERROR_CODE);
						responseBean.setStatusDescription(ResponseUtils.REGISTRATION_ERROR_RESPONSE);
					}
				} else {
					responseBean = new StatusResponseBean();
					responseBean.setStatusCode(ResponseUtils.ERROR_CODE);
					responseBean.setStatusDescription(ResponseUtils.USERNAME_DUPLICATE_RESPONSE);
				}
			} else {
				responseBean = new StatusResponseBean();
				responseBean.setStatusCode(ResponseUtils.ERROR_CODE);
				responseBean.setStatusDescription(ResponseUtils.INVALID_PARAMETERS);
			}
		} catch(Exception exception) {
			logger.error("Error occured in UserService.userRegister() " + exception);
			responseBean = new StatusResponseBean();
			responseBean.setStatusCode(ResponseUtils.ERROR_CODE);
			responseBean.setStatusDescription(ResponseUtils.REGISTRATION_ERROR_RESPONSE);
		}
		logger.debug("----- UserService.userRegister() method ends -----");
		return responseBean;
	}
	
	/**
	 * User login.
	 *
	 * @param user the user
	 * @return the status response bean
	 */
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public StatusResponseBean userLogin(User user) {
		logger.debug("----- UserService.userLogin() method begins -----");
		StatusResponseBean responseBean = null;
		UserDAOImpl userDAOImpl = null;
		try {
			requestValidation = new UserRequestValidation();
			if(requestValidation.validateLoginRequest(user)){
				userDAOImpl = new UserDAOImpl();
				boolean isValidUser = userDAOImpl.authenticateUser(user);
				if(isValidUser){
					responseBean = new StatusResponseBean();
					responseBean.setStatusCode(ResponseUtils.SUCCESS_CODE);
					responseBean.setStatusDescription(ResponseUtils.LOGIN_SUCCESS_RESPONSE);
				} else {
					responseBean = new StatusResponseBean();
					responseBean.setStatusCode(ResponseUtils.ERROR_CODE);
					responseBean.setStatusDescription(ResponseUtils.LOGIN_FAILURE_RESPONSE);
				}
			} else {
				responseBean = new StatusResponseBean();
				responseBean.setStatusCode(ResponseUtils.ERROR_CODE);
				responseBean.setStatusDescription(ResponseUtils.INVALID_PARAMETERS);
			}
		} catch(Exception exception) {
			logger.error("Error occured in UserService.userLogin() " + exception);
			responseBean = new StatusResponseBean();
			responseBean.setStatusCode(ResponseUtils.ERROR_CODE);
			responseBean.setStatusDescription(ResponseUtils.LOGIN_ERROR_RESPONSE);
		}
		logger.debug("----- UserService.userLogin() method ends -----");
		return responseBean;
	}
}
