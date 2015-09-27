package fr.treeptik.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

import fr.treeptik.entity.User;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.UserService;


@Component
public class InitialisationBase {
	public InitialisationBase() {

	}

	@Autowired
	private UserService userService;
	
	
	public void run() throws ServiceException {
		System.out.println("init");
		initUser();
	}

	public void initUser() throws ServiceException{
		System.out.println("init user");
		User user = new User();
		user.setLogin("admin");
		
		ShaPasswordEncoder sha = new ShaPasswordEncoder();
		user.setEncryptPassword(sha.encodePassword("852963", null));
		
		user.setEnabled(true);
		
		user.setRole("ROLE_ADMIN");
	
		userService.save(user);

	}
	
	
	
	
	

}
