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
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.Breakfast;
import fr.treeptik.entity.Ingredient;
import fr.treeptik.exception.FormException;
import fr.treeptik.exception.FormException.FormExceptionFeedBack;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.BreakfastService;
import fr.treeptik.service.IngredientService;

@Controller
@RequestMapping(value = "/admin/breakfast")
public class BreakfastController {

	@Autowired
	private BreakfastService breakfastService;
	@Autowired
	private IngredientService ingredientService;
	
	private Map<String, Ingredient> ingredientsCache;
	private List<Ingredient> initCache() throws ServiceException{
		ingredientsCache = new HashMap<>();
		List<Ingredient> ingredients;
		ingredients = ingredientService.findAll();
		for (Ingredient ingredient : ingredients) {
			ingredientsCache.put(ingredient.getId().toString(), ingredient);
		}
		return ingredients;
	}
	
	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("/admin/breakfast/breakfast");
		
		modelAndView.addObject("title", "Ajouter un petit déjeuné");
		try {
			modelAndView.addObject("ingredientsCache", initCache());
		} catch (ServiceException e) {
			// TODO gestion correct
			return list();
		}
		
		GregorianCalendar calendar = new java.util.GregorianCalendar(); 
		calendar.add(Calendar.DATE, 7);
	
		Breakfast defaultBreakfast = new Breakfast();
		defaultBreakfast.setDate(calendar.getTime());
		
		modelAndView.addObject("breakfast", defaultBreakfast);

		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			
			ModelAndView modelAndView = new ModelAndView("/admin/breakfast/breakfast");
			modelAndView.addObject("title", "Editer un membre");
			modelAndView.addObject("breakfast", breakfastService.findByIdWithIngredients(id));
			try {
				modelAndView.addObject("ingredientsCache", initCache());
			} catch (ServiceException e) {
				// TODO gestion correct
				return list();
			}
			
			return modelAndView;
		} catch (Exception e) {
			// TODO gestion correct
			return list();
		}
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("/admin/breakfast/breakfasts");

		modelAndView.addObject("title", "Liste des petit déjeunés");
		
		try {
			List<Breakfast> breakfasts = breakfastService.findAll();
			modelAndView.addObject("breakfasts", breakfasts);
		
			
		} catch (ServiceException e) {
			e.printStackTrace();
			List<String> errors = new ArrayList<>();
			errors.add(e.getMessage());
			modelAndView.addObject("errors", errors);
		}
		
		return modelAndView;

	}


	private void checkBreakfast(Breakfast breakfast) throws FormException{
		Date date = breakfast.getDate();
		String name = breakfast.getName();
		String commentaire = breakfast.getComment();
		List<String> errors = new ArrayList<>();
		Map<String, FormExceptionFeedBack> feedBacks = new HashMap<>();
		if (date == null) {
			errors.add("La date de l'évènement est obligatoire.");
			feedBacks.put("Date", FormExceptionFeedBack.ERROR);
		}
		if (name == null || name == "") {
			errors.add("Le nom est obligatoire.");
			feedBacks.put("Name", FormExceptionFeedBack.ERROR);
		}
		if (commentaire == null || commentaire == "") {
			feedBacks.put("Comment", FormExceptionFeedBack.WARNING);
		}
		if (errors.size() > 0) throw new FormException("Erreur sauvegarde membre", feedBacks, errors);
	}
	
	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("breakfast") Breakfast breakfast, BindingResult result){
		System.out.println("save");
		try {
			try {
				checkBreakfast(breakfast);

				breakfastService.save(breakfast);
				
				ModelAndView modelAndView = new ModelAndView("redirect:list.html");
				return modelAndView;
				
			} catch (FormException e) {

				ModelAndView modelAndView = new ModelAndView("/admin/breakfast/breakfast");
				
				modelAndView.addObject("breakfast", breakfast);
				modelAndView.addObject("title", "Ajouter un petit déjeuné");
				modelAndView.addObject("errors", e.getErrors());
				
				for(Entry<String, FormExceptionFeedBack> entry : e.getFeedBacks().entrySet()) {
					modelAndView.addObject("fb" + entry.getKey(), "has-" + entry.getValue().toString().toLowerCase());
				}
				
				try {
					modelAndView.addObject("ingredientsCache", initCache());
				} catch (ServiceException e1) {
					// TODO gestion correct
					return list();
				}
				
				return modelAndView;
			}
			
		} catch (Exception e) {
			ModelAndView modelAndView = edit(breakfast.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}

	}
	
	@RequestMapping(value = "/del.html", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("id") Integer id) {
		try {
			breakfastService.remove(breakfastService.findById(id));
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
		System.out.println("initBinder");
		binder.registerCustomEditor(Ingredient.class, new PropertyEditorSupport() {
		    @Override 
		    public void setAsText(final String text) throws IllegalArgumentException
		    {
		    	if(text == null || text == "") setValue(null);
		    	else {
		    		setValue(ingredientsCache.get(text));
		    	}
		    }
		    @Override
		    public String getAsText() {
		    	System.out.println("getAsText" + ((Ingredient) getValue()).getName());
			    if(getValue() == null) return "";
			    return "1";//((Ingredient) getValue()).getId().toString();
		    }
		});
	}

	@InitBinder
	protected void initBinderDate(WebDataBinder binder) {
		System.out.println("initBinderDate");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	
}
