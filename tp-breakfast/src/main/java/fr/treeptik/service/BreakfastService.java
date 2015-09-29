package fr.treeptik.service;

import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.dao.BreakfastDao;
import fr.treeptik.entity.Breakfast;
import fr.treeptik.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class BreakfastService {

	private Logger logger = Logger.getLogger(BreakfastService.class);

	@Autowired
	private BreakfastDao breakfastDao;

	@Transactional
	public Breakfast save(Breakfast breakfast) throws ServiceException {
		logger.debug("appel de la methode save Breakfast " + breakfast.toString());
		try {
			return breakfastDao.save(breakfast);
		} catch (PersistenceException e) {
			logger.error("erreur save Breakfast " + e.getMessage());
			throw new ServiceException("erreur save Breakfast", e);
		}
	}

	@Transactional
	public void remove(Breakfast breakfast) throws ServiceException {
		logger.debug("appel de la methode remove Breakfast " + breakfast.toString());
		try {
			breakfastDao.delete(breakfast);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Breakfast " + e.getMessage());
			throw new ServiceException("Breakfast id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Breakfast " + e.getMessage());
			throw new ServiceException("erreur remove Breakfast", e);
		} catch (Exception e) {
			logger.error("erreur remove Breakfast " + e.getMessage());
			throw new ServiceException("erreur remove Breakfast", e);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public void removeById(Integer id) throws ServiceException {
		logger.debug("appel de la methode removeById Breakfast id " + id);
		try {
			breakfastDao.delete(id);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Breakfast " + e.getMessage());
			throw new ServiceException("Breakfast id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Breakfast " + e.getMessage());
			throw new ServiceException("erreur removeById Breakfast", e);
		} catch (Exception e) {
			logger.error("erreur remove Breakfast " + e.getMessage());
			throw new ServiceException("erreur removeById Breakfast", e);
		}
	}
	
	public Breakfast findById(Integer id) throws ServiceException {
		try {
			return breakfastDao.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById player", e);
		}
	}
	public Breakfast findByIdWithIngredients(Integer id) throws ServiceException {
		try {
			return breakfastDao.findByIdWithIngredients(id);
			
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById player", e);
		}
	}
	
	public List<Breakfast> findAll() throws ServiceException {
		try {
			return breakfastDao.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll Breakfast", e);
		}
	}
	
	public List<Breakfast> findAllWithIngredients() throws ServiceException {
		try {
			return breakfastDao.findAllWithIngredients();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll Breakfast", e);
		}
	}
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public BreakfastDao getBreakfastDao() {
		return breakfastDao;
	}

	public void setBreakfastDao(BreakfastDao breakfastDao) {
		this.breakfastDao = breakfastDao;
	}




}
