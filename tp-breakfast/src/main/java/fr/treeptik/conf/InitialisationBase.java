package fr.treeptik.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

import fr.treeptik.entity.Ingredient;
import fr.treeptik.entity.Member;
import fr.treeptik.entity.User;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.IngredientService;
import fr.treeptik.service.UserService;


@Component
public class InitialisationBase {
	public InitialisationBase() {

	}

	@Autowired
	private UserService userService;
	@Autowired
	private IngredientService ingredientService;
	
	public void run() throws ServiceException {
		System.out.println("init");
		initUsers();
		initIngredients();
	}

	public void initUsers() throws ServiceException{
		System.out.println("init users");
		Member member = new Member();
		member.setLogin("admin");
		ShaPasswordEncoder sha = new ShaPasswordEncoder();
		member.setEncryptPassword(sha.encodePassword("852963", null));
		member.setEnabled(true);
		member.setRole("ROLE_ADMIN");
		member.setFirstName("Super");
		member.setLastName("Boss");
		userService.save(member);

		member = new Member();
		member.setLogin("mrYapados");
		sha = new ShaPasswordEncoder();
		member.setEncryptPassword(sha.encodePassword("852963", null));
		member.setEnabled(true);
		member.setRole("ROLE_USER");
		member.setFirstName("Cédric");
		member.setLastName("Sevestre");
		member.setPreference(Ingredient.BreakfastType.SWEET);
		userService.save(member);
		
	}
	
	public void initIngredients() throws ServiceException{
		System.out.println("init ingredients");
		Ingredient ingredient = new Ingredient();
		ingredient.setName("Nutella");
		ingredient.setType(Ingredient.BreakfastType.SWEET);
		ingredientService.save(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setName("Tranches de pain de mie");
		ingredient.setType(Ingredient.BreakfastType.BOTH);
		ingredientService.save(ingredient);

		ingredient = new Ingredient();
		ingredient.setName("Pain");
		ingredient.setType(Ingredient.BreakfastType.BOTH);
		ingredientService.save(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setName("Beurre");
		ingredient.setType(Ingredient.BreakfastType.SALTED);
		ingredientService.save(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setName("Biscottes");
		ingredient.setType(Ingredient.BreakfastType.BOTH);
		ingredientService.save(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setName("Confiture de fraise");
		ingredient.setType(Ingredient.BreakfastType.SWEET);
		ingredientService.save(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setName("Confiture de myrtille");
		ingredient.setType(Ingredient.BreakfastType.SWEET);
		ingredientService.save(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setName("Café");
		ingredient.setType(Ingredient.BreakfastType.BOTH);
		ingredientService.save(ingredient);
	}
	
	
	
	

}
