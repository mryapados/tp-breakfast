package fr.treeptik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.exception.FormException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.entity.Ingredient;
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
		modelAndView.addObject("action", "Ajouter");
		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("/admin/ingredient/ingredient");
			Ingredient ingredient = ingredientService.findById(id);

			modelAndView.addObject("ingredient", ingredient);
			modelAndView.addObject("action", "Editer");
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("/admin/ingredient/list-ingredient");
		try {
			modelAndView.addObject("ingredients", ingredientService.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;

	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(Ingredient ingredient) throws ServiceException {
		try {

			
			
			ingredientService.save(ingredient);

			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(ingredient.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}

	}
	
	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
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
	

}
