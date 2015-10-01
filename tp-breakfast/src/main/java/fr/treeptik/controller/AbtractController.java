package fr.treeptik.controller;

import org.springframework.beans.factory.annotation.Autowired;

import fr.treeptik.entity.User;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.SessionService;

public class AbtractController {

	@Autowired
	private SessionService sessionService;

	private User user;

	public User getUser() throws ServiceException {
		user = sessionService.getUser();
		return user;
	}

	
	
	
	
	
}
