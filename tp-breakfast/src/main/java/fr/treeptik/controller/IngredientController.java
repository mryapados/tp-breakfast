package fr.treeptik.controller;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.Ingredient;
import fr.treeptik.exception.FormException;
import fr.treeptik.exception.FormException.FormExceptionFeedBack;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.IngredientService;

@Controller
@RequestMapping(value = "/admin/ingredient")
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;

	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("/admin/ingredient/ingredient");
		modelAndView.addObject("ingredient", new Ingredient());
		modelAndView.addObject("title", "Ajouter un ingrédient");
		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			
			ModelAndView modelAndView = new ModelAndView("/admin/ingredient/ingredient");
			modelAndView.addObject("title", "Editer un membre");
			modelAndView.addObject("ingredient", ingredientService.findById(id));

			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("/admin/ingredient/ingredients");

		modelAndView.addObject("title", "Liste des ingrédients");
		
		try {
			List<Ingredient> ingredients = ingredientService.findAll();
			modelAndView.addObject("ingredients", ingredients);
		
			
		} catch (ServiceException e) {
			e.printStackTrace();
			List<String> errors = new ArrayList<>();
			errors.add(e.getMessage());
			modelAndView.addObject("errors", errors);
		}
		
		return modelAndView;

	}


	private void checkIngredient(Ingredient ingredient) throws FormException{
		String name = ingredient.getName();
		Ingredient.BreakfastType type = ingredient.getType();
		List<String> errors = new ArrayList<>();
		Map<String, FormExceptionFeedBack> feedBacks = new HashMap<>();
		if (name == null || name == "") {
			errors.add("Le nom est obligatoires.");
			feedBacks.put("Name", FormExceptionFeedBack.ERROR);
		}
		if (type == null) {
			errors.add("Le type est obligatoires.");
			feedBacks.put("Type", FormExceptionFeedBack.ERROR);
		}
		if (errors.size() > 0) throw new FormException("Erreur sauvegarde membre", feedBacks, errors);
	}
	
	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(Ingredient ingredient) {
		try {
			try {
				checkIngredient(ingredient);

				ingredientService.save(ingredient);
				
				ModelAndView modelAndView = new ModelAndView("redirect:list.html");
				return modelAndView;
				
			} catch (FormException e) {

				ModelAndView modelAndView = new ModelAndView("/admin/ingredient/ingredient");
				
				modelAndView.addObject("ingredient", ingredient);
				modelAndView.addObject("title", "Ajouter un ingrédient");
				modelAndView.addObject("errors", e.getErrors());
				
				for(Entry<String, FormExceptionFeedBack> entry : e.getFeedBacks().entrySet()) {
					modelAndView.addObject("fb" + entry.getKey(), "has-" + entry.getValue().toString().toLowerCase());
				}
				return modelAndView;
			}
			
		} catch (Exception e) {
			ModelAndView modelAndView = edit(ingredient.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}

	}
	
	@RequestMapping(value = "/del.html", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("id") Integer id) {
		try {
			ingredientService.remove(ingredientService.findById(id));
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
