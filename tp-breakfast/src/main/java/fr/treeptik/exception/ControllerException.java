package fr.treeptik.exception;

import fr.treeptik.response.ErrorResponse;

public class ControllerException extends Exception {

	private static final long serialVersionUID = 1L;

	private ErrorResponse err;
	
	public ControllerException(String message) {
		super(message);
	}
	
	public ControllerException(String message, Throwable e) {
		super(message, e);
	}
	
	public ControllerException(String message, ErrorResponse err, Throwable e) {
		super(message, e);
		this.err = err;
	}

	public ErrorResponse getErr() {
		return err;
	}

	public void setErr(ErrorResponse err) {
		this.err = err;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
