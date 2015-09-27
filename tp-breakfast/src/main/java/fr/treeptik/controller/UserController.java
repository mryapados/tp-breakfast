package fr.treeptik.controller;

import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.exception.FormException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.exception.FormException.FormExceptionFeedBack;
import fr.treeptik.exception.PasswordException;
import fr.treeptik.maker.UserMaker;
import fr.treeptik.maker.UserToMaker;
import fr.treeptik.entity.User;
import fr.treeptik.service.UserService;

@Controller
@RequestMapping(value = "/admin/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("/admin/user/user");
		modelAndView.addObject("userMaker", new UserMaker());
		modelAndView.addObject("action", "Ajouter");

		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("/admin/user/user");
			User user = userService.findById(id);

			UserMaker userMaker = UserToMaker.To(user);

			modelAndView.addObject("userMaker", userMaker);
			modelAndView.addObject("action", "Editer");
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("/admin/user/list-user");
		try {
			modelAndView.addObject("users", userService.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;

	}


	private static void checkPassword(String password) throws PasswordException{
		List<String> errors = new ArrayList<>();
		int nbMajuscule = 0;
		int nbMinuscule = 0;
		int nbChiffre = 0;
		int nbCaracSpe = 0;
		for (int i = 0 ; i < password.length() ; i++){
		    Character c = password.charAt(i);
		    Integer asc = (int) c;
		    if (asc >= 33 && asc <= 47) nbCaracSpe += 1;
		    if (asc >= 48 && asc <= 57) nbChiffre += 1;
		    if (asc >= 58 && asc <= 64) nbCaracSpe += 1;
		    if (asc >= 65 && asc <= 90) nbMajuscule += 1;
		    if (asc >= 91 && asc <= 96) nbCaracSpe += 1;
		    if (asc >= 97 && asc <= 122) nbMinuscule += 1;
		    if (asc >= 123 && asc <= 126) nbCaracSpe += 1;
		}
		if (nbMajuscule == 0) errors.add("Il faut au moins 1 majuscule dans le mot de passe");
		if (nbMinuscule == 0) errors.add("Il faut au moins 1 minuscule dans le mot de passe");
		if (nbChiffre == 0) errors.add("Il faut au moins 1 chiffre dans le mot de passe");
		if (nbCaracSpe == 0) errors.add("Il faut au moins 1 caractère spécial dans le mot de passe");
		if (password.length() < 6) errors.add("Le mot de passe doit avoir au moins 6 caractères");
		if (password.length() > 10) errors.add("Le mot de passe doit avoir moins de 10 caractères");

		if (errors.size() > 0) throw new PasswordException("Erreur", errors);
	}
	
	private void checkUser(UserMaker userMaker) throws FormException{
		String login = userMaker.getLogin();
		String password = userMaker.getPassword();
		String passwordMatch = userMaker.getPasswordMatch();
		Boolean enabled = userMaker.getEnabled();
		String role = userMaker.getRole();
		
		List<String> errors = new ArrayList<>();
		Map<String, FormExceptionFeedBack> feedBacks = new HashMap<>();
		if (login == null || login == "") {
			errors.add("Le login est obligatoires.");
			feedBacks.put("Login", FormExceptionFeedBack.ERROR);
		}
		if (password == null || password == "") {
			errors.add("Le mot de passe est obligatoires.");
			feedBacks.put("Password", FormExceptionFeedBack.ERROR);
			feedBacks.put("PasswordMatch", FormExceptionFeedBack.ERROR);
		}
		else{
			try {
				checkPassword(password);
			} catch (PasswordException e) {
				errors.addAll(e.getErrors());
				feedBacks.put("Password", FormExceptionFeedBack.ERROR);
				feedBacks.put("PasswordMatch", FormExceptionFeedBack.ERROR);
			}
		}
		if (enabled == null) {
			errors.add("Enabled est obligatoires.");
			feedBacks.put("Enabled", FormExceptionFeedBack.ERROR);
		}
		if (role == null || role == "") {
			errors.add("Role est obligatoires.");
			feedBacks.put("Role", FormExceptionFeedBack.ERROR);
		}
		if (!password.equals(passwordMatch)) {
			errors.add("Les mots de passe ne concordent pas.");
			feedBacks.put("Password", FormExceptionFeedBack.ERROR);
			feedBacks.put("PasswordMatch", FormExceptionFeedBack.ERROR);
		}
		if (errors.size() > 0) throw new FormException("Erreur sauvegarde utilisateur", feedBacks, errors);
	}
	
	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(UserMaker userMaker) {
		try {
			try {
				checkUser(userMaker);
				
				User user = null;
				if (userMaker.getId() == null) user = new User();
				else user = userService.findById(userMaker.getId());
				
				user.setId(userMaker.getId());
				user.setLogin(userMaker.getLogin());
				
				String encryptPassword = "";
				ShaPasswordEncoder sha = new ShaPasswordEncoder();
				encryptPassword = sha.encodePassword(userMaker.getPassword(), null);
				
				user.setEncryptPassword(encryptPassword);
				user.setEnabled(userMaker.getEnabled());
				user.setRole(userMaker.getRole());
				
				userService.save(user);
				
				ModelAndView modelAndView = new ModelAndView("redirect:list.html");
				return modelAndView;
				
			} catch (FormException e) {

				ModelAndView modelAndView = new ModelAndView("/admin/user/user");
				
				userMaker.setPassword("");
				userMaker.setPasswordMatch("");
				
				modelAndView.addObject("userMaker", userMaker);
				modelAndView.addObject("action", "Ajouter");
				modelAndView.addObject("errors", e.getErrors());
				
				for(Entry<String, FormExceptionFeedBack> entry : e.getFeedBacks().entrySet()) {
					modelAndView.addObject("fb" + entry.getKey(), "has-" + entry.getValue().toString().toLowerCase());
				}
				return modelAndView;
			}
			
		} catch (Exception e) {
			ModelAndView modelAndView = edit(userMaker.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}

	}
	
	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("id") Integer id) {
		try {
			userService.remove(userService.findById(id));
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(id);
			modelAndView.addObject("error", "Impossible de supprimer l'élément.");
			return modelAndView;
		}

	}
	

}
