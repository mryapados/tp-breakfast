package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.treeptik.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	
	@Query("SELECT u FROM User u WHERE u.login =:login")
	User FindByLogin(@Param("login") String login);
	
}
