package fr.treeptik.controller.checkin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;

import fr.treeptik.entity.Breakfast;
import fr.treeptik.exception.FormException;
import fr.treeptik.exception.FormException.FormExceptionFeedBack;

public class BreakfastCheckin {

	
	public static void checkBreakfast(Breakfast breakfast, BindingResult result) throws FormException{
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
		
		if (result.hasFieldErrors("name")){
			errors.add(result.getFieldError("name").getDefaultMessage());
			feedBacks.put("Name", FormExceptionFeedBack.ERROR);
		}
		if (result.hasFieldErrors("comment")){
			errors.add(result.getFieldError("comment").getDefaultMessage());
			feedBacks.put("Comment", FormExceptionFeedBack.ERROR);
		}
		if (errors.size() > 0 || result.hasErrors()) throw new FormException("Erreur sauvegarde membre", feedBacks, errors);
	}
	
	
}
