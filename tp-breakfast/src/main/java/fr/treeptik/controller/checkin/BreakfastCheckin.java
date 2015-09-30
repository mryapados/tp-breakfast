package fr.treeptik.controller.checkin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.treeptik.entity.Breakfast;
import fr.treeptik.exception.FormException;
import fr.treeptik.exception.FormException.FormExceptionFeedBack;

public class BreakfastCheckin {

	
	public static void checkBreakfast(Breakfast breakfast) throws FormException{
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
	
	
}
