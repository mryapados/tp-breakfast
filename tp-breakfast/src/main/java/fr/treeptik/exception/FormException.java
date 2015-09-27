package fr.treeptik.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormException extends MessageErrorException {

	private static final long serialVersionUID = 1L;

	public enum FormExceptionFeedBack{
		SUCCESS, WARNING, ERROR
	}
	
	private Map<String, FormExceptionFeedBack> feedBacks;
	
	public FormException(String message, Map<String, FormExceptionFeedBack> feedBacks, List<String> errors) {
		super(message, errors);
		this.feedBacks = feedBacks;
	}
	
	public FormException(String message, Map<String, FormExceptionFeedBack> feedBacks, List<String> errors, Throwable e) {
		super(message, errors, e);
		this.feedBacks = feedBacks;
	}
	
	public FormException(String message, List<String> errors) {
		super(message, errors);
	}
	
	public FormException(String message, List<String> errors, Throwable e) {
		super(message, errors, e);
	}

	public Map<String, FormExceptionFeedBack> getFeedBacks() {
		if (this.feedBacks == null) this.feedBacks = new HashMap<>();
		return feedBacks;
	}

	public void setFeedBacks(Map<String, FormExceptionFeedBack> feedBacks) {
		this.feedBacks = feedBacks;
	}
	
	
	
}
