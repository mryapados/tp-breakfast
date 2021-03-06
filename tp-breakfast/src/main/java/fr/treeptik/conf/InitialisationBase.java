package fr.treeptik.conf;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

import fr.treeptik.entity.Breakfast;
import fr.treeptik.entity.Ingredient;
import fr.treeptik.entity.Member;
import fr.treeptik.entity.User;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.BreakfastService;
import fr.treeptik.service.IngredientService;
import fr.treeptik.service.MemberService;
import fr.treeptik.service.UserService;


@Component
public class InitialisationBase {
	public InitialisationBase() {

	}

	@Autowired
	private UserService userService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private BreakfastService breakfastService;
	
	public void run() throws ServiceException {
		System.out.println("init");
		initUsers();
		initIngredients();
		initBreakfasts();
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
		memberService.save(member);

		member = new Member();
		member.setLogin("mrYapados");
		sha = new ShaPasswordEncoder();
		member.setEncryptPassword(sha.encodePassword("852963", null));
		member.setEnabled(true);
		member.setRole("ROLE_USER");
		member.setFirstName("Cédric");
		member.setLastName("Sevestre");
		member.setPreference(Ingredient.BreakfastType.SWEET);
		memberService.save(member);
		
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
		ingredient.setName("Beurre");
		ingredient.setType(Ingredient.BreakfastType.SALTED);
		ingredientService.save(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setName("Confiture de fraise");
		ingredient.setType(Ingredient.BreakfastType.SWEET);
		ingredientService.save(ingredient);

		ingredient = new Ingredient();
		ingredient.setName("Café");
		ingredient.setType(Ingredient.BreakfastType.BOTH);
		ingredientService.save(ingredient);
	}
	
	public void initBreakfasts() throws ServiceException{
		System.out.println("init breakfasts");
		Breakfast breakfast = new Breakfast();
		breakfast.setDate(new Date());
		breakfast.setName("Pain à la confiture de myrtille");
		breakfast.setComment("Venez vous régaler !");
		breakfast.setOrganizer(userService.findByLogin("admin"));
		
		Ingredient ingredient = new Ingredient();
		ingredient.setName("Pain");
		ingredient.setType(Ingredient.BreakfastType.BOTH);
		ingredientService.save(ingredient);
		breakfast.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setName("Confiture de myrtille");
		ingredient.setType(Ingredient.BreakfastType.SWEET);
		ingredientService.save(ingredient);
		breakfast.getIngredients().add(ingredient);
		
		breakfastService.save(breakfast);
		
		
		
		
		breakfast = new Breakfast();
		breakfast.setDate(new Date());
		breakfast.setName("Biscottes à la confiture de groseille");
		breakfast.setComment("Youpi !!");
		breakfast.setOrganizer(userService.findByLogin("mryapados"));
		
		ingredient = new Ingredient();
		ingredient.setName("Biscottes");
		ingredient.setType(Ingredient.BreakfastType.BOTH);
		ingredientService.save(ingredient);
		breakfast.getIngredients().add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setName("Confiture de groseille");
		ingredient.setType(Ingredient.BreakfastType.SWEET);
		ingredientService.save(ingredient);
		breakfast.getIngredients().add(ingredient);
		
		breakfastService.save(breakfast);
	}
	
	

}
