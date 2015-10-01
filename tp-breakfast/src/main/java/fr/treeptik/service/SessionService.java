package fr.treeptik.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import fr.treeptik.entity.User;
import fr.treeptik.exception.ServiceException;

@Component
@Scope("session")
public class SessionService {

	@Autowired
	private UserService userService;
	
	private User user;

	public User getUser() throws ServiceException {
		if (user == null) {
			org.springframework.security.core.userdetails.User userDetail = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			user = userService.findByLogin(userDetail.getUsername());
			System.out.println("user connected = " + user.getLogin());
		}
		return user;
	}


	
	
}
