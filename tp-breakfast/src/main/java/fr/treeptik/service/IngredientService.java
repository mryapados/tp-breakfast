package fr.treeptik.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.dao.IngredientDao;
import fr.treeptik.entity.Ingredient;
import fr.treeptik.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class IngredientService {

	private Logger logger = Logger.getLogger(IngredientService.class);

	@Autowired
	private IngredientDao ingredientDao;

	@Transactional
	public Ingredient save(Ingredient ingredient) throws ServiceException {
		logger.debug("appel de la methode save Ingredient " + ingredient.getName());
		try {
			ingredient.setName(ingredient.getName().toLowerCase());
			return ingredientDao.save(ingredient);
		} catch (PersistenceException e) {
			logger.error("erreur save Ingredient " + e.getMessage());
			throw new ServiceException("erreur save Ingredient", e);
		}
	}

	@Transactional
	public void remove(Ingredient ingredient) throws ServiceException {
		logger.debug("appel de la methode remove Ingredient " + ingredient.getName());
		try {
			ingredientDao.delete(ingredient);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Ingredient " + e.getMessage());
			throw new ServiceException("Ingredient id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Ingredient " + e.getMessage());
			throw new ServiceException("erreur remove Ingredient", e);
		} catch (Exception e) {
			logger.error("erreur remove Ingredient " + e.getMessage());
			throw new ServiceException("erreur remove Ingredient", e);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public void removeById(Integer id) throws ServiceException {
		logger.debug("appel de la methode removeById Ingredient id " + id);
		try {
			ingredientDao.delete(id);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Ingredient " + e.getMessage());
			throw new ServiceException("Ingredient id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Ingredient " + e.getMessage());
			throw new ServiceException("erreur removeById Ingredient", e);
		} catch (Exception e) {
			logger.error("erreur remove Ingredient " + e.getMessage());
			throw new ServiceException("erreur removeById Ingredient", e);
		}
	}
	
	public Ingredient findById(Integer id) throws ServiceException {
		try {
			return ingredientDao.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById player", e);
		}
	}
	public List<Ingredient> findAll() throws ServiceException {
		try {
			return ingredientDao.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll Ingredient", e);
		}
	}
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public IngredientDao getIngredientDao() {
		return ingredientDao;
	}

	public void setIngredientDao(IngredientDao ingredientDao) {
		this.ingredientDao = ingredientDao;
	}




}
