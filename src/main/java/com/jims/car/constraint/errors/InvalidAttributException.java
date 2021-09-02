package com.jims.car.constraint.errors;

public class InvalidAttributException extends FunctionalException{
	private static final long serialVersionUID = 1L;
	

	public InvalidAttributException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}
	
	public InvalidAttributException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}
	
}
