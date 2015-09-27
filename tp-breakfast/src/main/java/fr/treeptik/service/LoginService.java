package fr.treeptik.service;

import java.util.Arrays;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.treeptik.dao.UserDao;
import fr.treeptik.entity.User;
 
@Service
public class LoginService implements UserDetailsService{
	private UserDao userDao;
    
    @Autowired
    public LoginService(UserDao userDao)
    {
        this.userDao = userDao;
    }
 
    @Override
    public UserDetails loadUserByUsername( String login ) throws UsernameNotFoundException
    {
		User user = userDao.FindByLogin(login);
	    if( user == null ) throw new UsernameNotFoundException("User " + login + " not found");
	    System.out.println(user.getLogin() + " " + user.getRole());
	    List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
	    return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getEncryptedPassword(), authorities);
    }
}
