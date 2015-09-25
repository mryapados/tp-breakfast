package fr.treeptik.controller;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.treeptik.exception.ControllerException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionController {


	@ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
	public void authenticationCredentialsNotFoundExceptionHandler(AuthenticationCredentialsNotFoundException ex, HttpServletResponse response) {
		response.setStatus(401);
	}

	@ExceptionHandler(ControllerException.class)
	public void serviceExceptionHandler(ControllerException ex, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		ServletOutputStream out = response.getOutputStream();
		ObjectMapper objectMapper = new ObjectMapper();

		String json = objectMapper.writeValueAsString(ex.getErr());
		out.println(json);
		
		response.setStatus(500);
	}
	
	@ExceptionHandler(ServiceException.class)
	public void serviceExceptionHandler(ServiceException ex, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		ServletOutputStream out = response.getOutputStream();
		ObjectMapper objectMapper = new ObjectMapper();

		String json = objectMapper.writeValueAsString(new ErrorResponse("Erreur ServiceException, veuillez contacter le service technique"));
		out.println(json);
		
		response.setStatus(500);
	}
	
}
