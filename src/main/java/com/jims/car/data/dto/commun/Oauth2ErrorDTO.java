package com.jims.car.data.dto.commun;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Oauth2ErrorDTO {

	private String error;
	private String error_description;

	public Oauth2ErrorDTO(String error, String errorDescription) {
		this.error = error;
		this.error_description = errorDescription;
	}

	public Oauth2ErrorDTO() {
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError_description() {
		return error_description;
	}

	public void setError_description(String error_description) {
		this.error_description = error_description;
	}
	
}
