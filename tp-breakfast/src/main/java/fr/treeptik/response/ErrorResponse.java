package fr.treeptik.response;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

	private String message;
	private List<String> errors;

	public ErrorResponse() {

	}

	public ErrorResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		if (this.errors == null) this.errors = new ArrayList<>();
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "ErrorResponse [message=" + message + ", errors=" + errors + "]";
	}

	
	
}
