package fr.treeptik.entity.dto;

import java.util.Date;
import java.util.Set;

import fr.treeptik.entity.Breakfast;
import fr.treeptik.entity.Diary;
import fr.treeptik.entity.Ingredient;
import fr.treeptik.entity.User;

public class BreakfastDto {	
	
	private Integer id;
	private Date date;
	private Float price;
	private String comment;
	private User organizer;
	private Set<Diary> diaries;
	
	private String name;
	private Set<Ingredient> ingredients;
	
	private Boolean allowDel;
	private Boolean allowEdit;
	private Boolean allowRegister;

	public static BreakfastDto from(Breakfast breakfast){
		BreakfastDto breakfastDto = new BreakfastDto();

		breakfastDto.setId(breakfast.getId());
		breakfastDto.setDate(breakfast.getDate());
		breakfastDto.setPrice(breakfast.getPrice());
		breakfastDto.setComment(breakfast.getComment());
		breakfastDto.setOrganizer(breakfast.getOrganizer());
		breakfastDto.setDiaries(breakfast.getDiaries());
		
		breakfastDto.setName(breakfast.getName());
		breakfastDto.setIngredients(breakfast.getIngredients());

		return breakfastDto;
	}
	public static Breakfast to(BreakfastDto breakfastDto){
		Breakfast breakfast = new Breakfast();
		
		breakfast.setId(breakfastDto.getId());
		breakfast.setDate(breakfastDto.getDate());
		breakfast.setPrice(breakfastDto.getPrice());
		breakfast.setComment(breakfastDto.getComment());
		breakfast.setOrganizer(breakfastDto.getOrganizer());
		breakfast.setDiaries(breakfastDto.getDiaries());
		
		breakfast.setName(breakfastDto.getName());
		breakfast.setIngredients(breakfastDto.getIngredients());
		
		return breakfast;
	}
	
	
	
	
	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public Float getPrice() {
		return price;
	}



	public void setPrice(Float price) {
		this.price = price;
	}



	public String getComment() {
		return comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}



	public User getOrganizer() {
		return organizer;
	}



	public void setOrganizer(User organizer) {
		this.organizer = organizer;
	}



	public Set<Diary> getDiaries() {
		return diaries;
	}



	public void setDiaries(Set<Diary> diaries) {
		this.diaries = diaries;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Set<Ingredient> getIngredients() {
		return ingredients;
	}



	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}



	public Boolean getAllowDel() {
		return allowDel;
	}



	public void setAllowDel(Boolean allowDel) {
		this.allowDel = allowDel;
	}



	public Boolean getAllowEdit() {
		return allowEdit;
	}



	public void setAllowEdit(Boolean allowEdit) {
		this.allowEdit = allowEdit;
	}



	public Boolean getAllowRegister() {
		return allowRegister;
	}



	public void setAllowRegister(Boolean allowRegister) {
		this.allowRegister = allowRegister;
	}



	
	
}
