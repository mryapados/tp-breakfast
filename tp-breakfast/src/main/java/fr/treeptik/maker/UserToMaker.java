package fr.treeptik.maker;

import fr.treeptik.entity.User;

public class UserToMaker {

	public static UserMaker to(User user){
		UserMaker userMaker = new UserMaker();
		
		userMaker.setId(user.getId());
		userMaker.setLogin(user.getLogin());
		
		userMaker.setEnabled(user.getEnabled());
		userMaker.setRole(user.getRole());
		
		return userMaker;
	}
	
	
	
}
