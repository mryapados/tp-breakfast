package fr.treeptik.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.treeptik.entity.Breakfast;

public interface BreakfastDao extends JpaRepository<Breakfast, Integer> {

	@Query("SELECT b FROM Breakfast b LEFT JOIN FETCH b.ingredients WHERE b.id =:id")
	Breakfast findByIdWithIngredients(@Param("id") Integer id);
	
	@Query("SELECT DISTINCT b FROM Breakfast b LEFT JOIN FETCH b.ingredients")
	List<Breakfast> findAllWithIngredients();
	
	
}
