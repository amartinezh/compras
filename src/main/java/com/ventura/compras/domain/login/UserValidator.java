package com.ventura.compras.domain.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator{

	private Pattern pattern;  
	private Matcher matcher;  
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
			   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required.id", "Id is required.");
		
		if (!(user.getId() != null && user.getId().isEmpty())) {  
			   pattern = Pattern.compile(EMAIL_PATTERN);  
			   matcher = pattern.matcher(user.getId());  
			   if (!matcher.matches()) {  
			    errors.rejectValue("email", "email.incorrect",  
			      "Enter a correct email");  
			   }  
			  } 
	}

}
