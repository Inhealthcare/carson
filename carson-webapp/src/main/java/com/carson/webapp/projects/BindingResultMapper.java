package com.carson.webapp.projects;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class BindingResultMapper {

	public void addErrors(ConnectionValidationResult result, String objectName, BindingResult binding) {

		List<String> errors = result.getErrors();
		for (String error : errors) {
			binding.addError(new ObjectError(objectName, error));
		}

	}

}
