package fr.treeptik.local.run;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.treeptik.conf.ApplicationConfiguration;
import fr.treeptik.entity.Breakfast;
import fr.treeptik.entity.Ingredient;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.IngredientService;
import fr.treeptik.service.UserService;

public class Run {

	//public static ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

	public static void main(String[] args) throws ServiceException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub

//		ProtectService<Breakfast> ps = new ProtectService<>();
//		
//	
//		Breakfast breakfast = new Breakfast();
//		breakfast.setDate(new Date());
//		breakfast.setName("Pain à la confiture de myrtille");
//		breakfast.setComment("<script>alert(0);</script>");
//		
//		Ingredient ingredient = new Ingredient();
//		ingredient.setName("Pain");
//		ingredient.setType(Ingredient.BreakfastType.BOTH);
//		breakfast.getIngredients().add(ingredient);
//		
//		ingredient = new Ingredient();
//		ingredient.setName("Confiture de myrtille");
//		ingredient.setType(Ingredient.BreakfastType.SWEET);
//		breakfast.getIngredients().add(ingredient);
//		
//		ps.protect(breakfast);
		
		
	}

}
