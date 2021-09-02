package com.jims.car.constraint.errors;

import java.util.HashMap;
import java.util.Map;

public enum ErrorsEnum {
    ERR_API_EMPTY_ID("ERR_API_EMPTY_ID", "Attribut Id is empty.", true, false),
    ERR_API_UNKW("ERR_API_UNKW", "Unknown exception occurred.", true, false),
    ERR_API_USER_IS_NOT_FOUND("ERR_API_USER_IS_NOT_FOUND", "User is not found.", true, false),
    ERR_API_CAR_IS_NOT_FOUND("ERR_API_CAR_IS_NOT_FOUND", "Car is not found.", true, false),
    ERR_API_COMMENT_IS_NOT_FOUND("ERR_API_COMMENT_IS_NOT_FOUND", "Comment is not found.", true, false);

    private final String errorCode;
    private final String errorMessage;
    private final Boolean error;
    private final Boolean warning;

    private static final Map<String, ErrorsEnum> BYID = new HashMap<>();

    static {
        for (ErrorsEnum e : ErrorsEnum.values()) {
            if (BYID.put(e.getErrorCode(), e) != null) {
                throw new IllegalArgumentException("duplicate id: " + e.getErrorCode());
            }
        }
    }

    ErrorsEnum(String errorCode, String errorMessage, Boolean error, Boolean warning) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.error = error;
        this.warning = warning;
    }

    @Override
    public String toString() {
        return "ErrorCode : " + errorCode + " errorMessage : " + errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static ErrorsEnum getById(String id) {
        return BYID.get(id);
    }

    public Boolean getError() {
        return error;
    }

    public Boolean getWarning() {
        return warning;
    }
}
