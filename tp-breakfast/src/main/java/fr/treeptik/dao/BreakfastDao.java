package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.treeptik.entity.Breakfast;

public interface BreakfastDao extends JpaRepository<Breakfast, Integer> {

}
