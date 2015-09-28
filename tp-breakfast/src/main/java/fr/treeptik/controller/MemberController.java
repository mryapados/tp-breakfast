package fr.treeptik.controller;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.Ingredient;
import fr.treeptik.entity.Member;
import fr.treeptik.exception.FormException;
import fr.treeptik.exception.FormException.FormExceptionFeedBack;
import fr.treeptik.exception.MessageErrorException;
import fr.treeptik.exception.PasswordException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.maker.MemberMaker;
import fr.treeptik.maker.MemberToMaker;
import fr.treeptik.service.MemberService;

@Controller
@RequestMapping(value = "/admin/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("/admin/member/member");
		modelAndView.addObject("memberMaker", new MemberMaker());
		modelAndView.addObject("title", "Ajouter un membre");

		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("/admin/member/member");
			Member member = memberService.findById(id);

			MemberMaker memberMaker = MemberToMaker.to(member);

			modelAndView.addObject("memberMaker", memberMaker);
			modelAndView.addObject("action", "Editer");
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("/admin/member/members");

		modelAndView.addObject("title", "Liste des membres");
		
		try {
			List<Member> members = memberService.findAll();
			
			modelAndView.addObject("members", members);
		
			
		} catch (ServiceException e) {
			List<String> errors = new ArrayList<>();
			errors.add(e.getMessage());
			modelAndView.addObject("errors", errors);
		}
		
		
		
		
		try {
			modelAndView.addObject("members", memberService.findAll());
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
	
	private void checkMember(MemberMaker memberMaker) throws FormException{
		String login = memberMaker.getLogin();
		String password = memberMaker.getPassword();
		String passwordMatch = memberMaker.getPasswordMatch();
		Boolean enabled = memberMaker.getEnabled();
		String role = memberMaker.getRole();
		String firstName = memberMaker.getFirstName();
		String lastName = memberMaker.getLastName();
		Ingredient.BreakfastType preference = memberMaker.getPreference();
		
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
		
		if (firstName == null || firstName == "") {
			errors.add("Le prénom est obligatoires.");
			feedBacks.put("FirstName", FormExceptionFeedBack.ERROR);
		}
		if (lastName == null || lastName == "") {
			errors.add("Le nom est obligatoires.");
			feedBacks.put("LastName", FormExceptionFeedBack.ERROR);
		}
		if (errors.size() > 0) throw new FormException("Erreur sauvegarde membre", feedBacks, errors);
	}
	
	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(MemberMaker memberMaker) {
		try {
			try {
				checkMember(memberMaker);
				
				Member member = null;
				if (memberMaker.getId() == null) member = new Member();
				else member = memberService.findById(memberMaker.getId());
				
				member.setId(memberMaker.getId());
				member.setLogin(memberMaker.getLogin());
				
				String encryptPassword = "";
				ShaPasswordEncoder sha = new ShaPasswordEncoder();
				encryptPassword = sha.encodePassword(memberMaker.getPassword(), null);
				
				member.setEncryptPassword(encryptPassword);
				member.setEnabled(memberMaker.getEnabled());
				member.setRole(memberMaker.getRole());
				
				member.setPreference(memberMaker.getPreference());
				
				memberService.save(member);
				
				ModelAndView modelAndView = new ModelAndView("redirect:list.html");
				return modelAndView;
				
			} catch (FormException e) {

				ModelAndView modelAndView = new ModelAndView("/admin/member/member");
				
				memberMaker.setPassword("");
				memberMaker.setPasswordMatch("");
				
				modelAndView.addObject("memberMaker", memberMaker);
				modelAndView.addObject("title", "Ajouter un membre");
				modelAndView.addObject("errors", e.getErrors());
				
				for(Entry<String, FormExceptionFeedBack> entry : e.getFeedBacks().entrySet()) {
					modelAndView.addObject("fb" + entry.getKey(), "has-" + entry.getValue().toString().toLowerCase());
				}
				return modelAndView;
			}
			
		} catch (Exception e) {
			ModelAndView modelAndView = edit(memberMaker.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}

	}
	
	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("id") Integer id) {
		try {
			memberService.remove(memberService.findById(id));
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(id);
			modelAndView.addObject("error", "Impossible de supprimer l'élément.");
			return modelAndView;
		}
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(Ingredient.BreakfastType.class, new PropertyEditorSupport() {
		    @Override 
		    public void setAsText(final String text) throws IllegalArgumentException
		    {
		    	if(text == null || text == "") setValue(null);
		    	else setValue(Ingredient.BreakfastType.valueOf(text));
		    }
		    @Override
		    public String getAsText() {
			    if(getValue() == null) return "";
			    return ((Ingredient.BreakfastType) getValue()).name();
		    }
		});
	}

}
