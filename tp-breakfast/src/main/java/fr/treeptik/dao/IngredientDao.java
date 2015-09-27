package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.treeptik.entity.Ingredient;

public interface IngredientDao extends JpaRepository<Ingredient, Integer> {

}
