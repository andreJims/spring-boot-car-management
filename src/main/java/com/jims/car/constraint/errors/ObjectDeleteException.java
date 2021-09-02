package com.jims.car.constraint.errors;

public class ObjectDeleteException extends TechnicalException {
	
	private static final long serialVersionUID = 69L;

	public ObjectDeleteException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}

	public ObjectDeleteException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}
}


