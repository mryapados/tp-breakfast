package fr.treeptik.controller;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.controller.checkin.BreakfastCheckin;
import fr.treeptik.entity.Breakfast;
import fr.treeptik.entity.Ingredient;
import fr.treeptik.entity.User;
import fr.treeptik.entity.dto.BreakfastDto;
import fr.treeptik.exception.ForbiddenException;
import fr.treeptik.exception.FormException;
import fr.treeptik.exception.FormException.FormExceptionFeedBack;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.BreakfastService;
import fr.treeptik.service.IngredientService;
import fr.treeptik.service.UserService;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin/breakfast")
public class BreakfastController extends AbtractController{

	@Autowired
	private BreakfastService breakfastService;
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private UserService userService;
	
	private String title;
	
	private Map<String, Ingredient> ingredientsCache;
	@ModelAttribute("ingredientsCache")
	private List<Ingredient> initIngredientCache() throws ServiceException{
		ingredientsCache = new HashMap<>();
		List<Ingredient> ingredients;
		ingredients = ingredientService.findAll();
		for (Ingredient ingredient : ingredients) {
			ingredientsCache.put(ingredient.getId().toString(), ingredient);
		}
		return ingredients;
	}
	
	private Map<String, User> usersCache;
	@ModelAttribute("usersCache")
	private List<User> initUserCache() throws ServiceException{
		System.out.println("initUserCache");
		usersCache = new HashMap<>();
		List<User> users;
		users = userService.findAll();
		for (User user : users) {
			usersCache.put(user.getId().toString(), user);
		}
		return users;
	}

	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("/admin/breakfast/breakfast");
		
		title = "Ajouter un petit déjeuné";
		modelAndView.addObject("title", title);
		
		GregorianCalendar calendar = new java.util.GregorianCalendar(); 
		calendar.add(Calendar.DATE, 7);
	
		Breakfast defaultBreakfast = new Breakfast();
		defaultBreakfast.setDate(calendar.getTime());
		
		modelAndView.addObject("breakfast", defaultBreakfast);

		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("/admin/breakfast/breakfast");
		
		title = "Modifier un petit déjeuné";
		modelAndView.addObject("title", title);
		try {
			modelAndView.addObject("breakfast", breakfastService.findByIdWithIngredients(id));
		} catch (ServiceException e) {
			// TODO gestion correct
			return list();
		}

		return modelAndView;
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		
		
		ModelAndView modelAndView = new ModelAndView("/admin/breakfast/breakfasts");

		modelAndView.addObject("title", "Liste des petits déjeunés");
		
		try {
			List<Breakfast> breakfasts = breakfastService.findAllWithIngredients();
			List<BreakfastDto> breakfastDtos = new ArrayList<>();
			for (Breakfast breakfast : breakfasts) {
				BreakfastDto breakfastDto = BreakfastDto.from(breakfast);
				
				if (breakfastService.AllowAdministration(getUser(), breakfast)){
					breakfastDto.setAllowDel(true);
					breakfastDto.setAllowEdit(true);
				}
				if (breakfastService.AllowRegistration(getUser(), breakfast)){
					breakfastDto.setAllowRegister(true);
				}

				breakfastDtos.add(breakfastDto);
			}
			modelAndView.addObject("breakfasts", breakfastDtos);

		} catch (ServiceException e) {
			e.printStackTrace();
			List<String> errors = new ArrayList<>();
			errors.add(e.getMessage());
			modelAndView.addObject("errors", errors);
		}
		
		return modelAndView;

	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("breakfast") Breakfast breakfast, BindingResult result){
		ModelAndView modelAndView = new ModelAndView("/admin/breakfast/breakfast");
		modelAndView.addObject("title", title);
		modelAndView.addObject("breakfast", breakfast);
		try {
			BreakfastCheckin.checkBreakfast(breakfast, result);
		} catch (FormException e) {
			modelAndView.addObject("errors", e.getErrors());
			for(Entry<String, FormExceptionFeedBack> entry : e.getFeedBacks().entrySet()) {
				modelAndView.addObject("fb" + entry.getKey(), "has-" + entry.getValue().toString().toLowerCase());
			}
			return modelAndView;
		}


		try {
			if (breakfast.getOrganizer() == null) breakfast.setOrganizer(getUser());
			
			if (!breakfastService.AllowAdministration(getUser(), breakfast)){
				throw new ForbiddenException("Vous n'avez pas le droit d'éditer " + breakfast.getName());
			}
			breakfastService.save(breakfast);
			return new ModelAndView("redirect:list.html");
				
		} catch (Exception e) {
			List<String> errors = new ArrayList<>();
			errors.add(e.getMessage());
			modelAndView.addObject("errors", errors);
			return modelAndView;

		}

	}
	
	@RequestMapping(value = "/del.html", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("id") Integer id) {

		try {
			Breakfast breakfast = breakfastService.findById(id);
			if (!breakfastService.AllowAdministration(getUser(), breakfast)){
				throw new ForbiddenException("Vous n'avez pas le droit de supprimer " + breakfast.getName());
			}
			
			breakfastService.remove(breakfast);
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
			
		} catch (ServiceException e) {
			// TODO gestion correct
			return list();
		}
		

	}
	
	@InitBinder
	protected void initBinderIngredient(WebDataBinder binder) throws Exception {
		System.out.println("initBinderIngredient");
		binder.registerCustomEditor(Ingredient.class, "ingredients" , new PropertyEditorSupport() {
		    @Override 
		    public void setAsText(final String text) throws IllegalArgumentException
		    {
		    	if(text == null || text == "") setValue(null);
		    	else {
		    		setValue(ingredientsCache.get(text));
		    	}
		    }
//		    @Override
//		    public String getAsText() { // Inutile ?!
//		    	System.out.println("getAsText" + ((Ingredient) getValue()).getName());
//			    if(getValue() == null) return "";
//			    return ((Ingredient) getValue()).getId().toString();
//		    }
		});
	}
	
	
	@InitBinder
	protected void initBinderOrganizer(WebDataBinder binder) throws Exception {
		System.out.println("initBinderOrganizer");
		binder.registerCustomEditor(User.class, "organizer" , new PropertyEditorSupport() {
		    @Override 
		    public void setAsText(final String text) throws IllegalArgumentException
		    {
		    	if(text == null || text == "") setValue(null);
		    	else {
		    		setValue(usersCache.get(text));
		    	}
		    }
		    @Override
		    public String getAsText() { // Inutile ?!
			    if(getValue() == null) return "";
			    return ((User) getValue()).getId().toString();
		    }
		});
	}
	

	@InitBinder
	protected void initBinderDate(WebDataBinder binder) {
		System.out.println("initBinderDate");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "date", new CustomDateEditor(dateFormat, true));
	}
	
	
}
