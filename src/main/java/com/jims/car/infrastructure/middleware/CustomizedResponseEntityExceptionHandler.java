package com.jims.car.infrastructure.middleware;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.jims.car.data.dto.commun.*;
import com.jims.car.constraint.errors.ErrorsEnum;
import com.jims.car.constraint.errors.FunctionalException;
import com.jims.car.constraint.errors.TechnicalException;
import com.jims.car.constraint.errors.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<BaseDTO> handleUGlobalException(Exception e, WebRequest request) {

        String uuid = UUID.randomUUID().toString();
        log.error("Unhandled Exception {} ", uuid, e);
        UnknownErrorDTO unhandledErrorDTO = new UnknownErrorDTO();

        unhandledErrorDTO.setUuid(uuid);
        unhandledErrorDTO.setError(ErrorsEnum.ERR_API_UNKW.getError());
        unhandledErrorDTO.setWarning(ErrorsEnum.ERR_API_UNKW.getWarning());
        unhandledErrorDTO.setErrorCode(ErrorsEnum.ERR_API_UNKW.getErrorCode());
        unhandledErrorDTO.setMessage(e.getMessage());

        return new ResponseEntity<>(unhandledErrorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<Oauth2ErrorDTO> handleAccessDeniedException(AccessDeniedException e, WebRequest request) {
        String uuid = UUID.randomUUID().toString();
        log.error("Access Denied Error ID {} ", uuid, e);
        Oauth2ErrorDTO error = new Oauth2ErrorDTO();
        error.setError("access_denied");
        error.setError_description("Access Denied");
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(FunctionalException.class)
    public final ResponseEntity<BaseDTO> handleFunctionalException(FunctionalException e, WebRequest request) {
        String uuid = UUID.randomUUID().toString();
        log.error("Functional Error ID {} ", uuid, e);
        CommunErrorDTO communErrorDTO = new CommunErrorDTO();
        ErrorsEnum errorsEnum = e.getErrorsEnum();
        log.error("Error message : {} ", errorsEnum.getErrorMessage());

        communErrorDTO.setError(errorsEnum.getError());
        communErrorDTO.setWarning(errorsEnum.getWarning());
        communErrorDTO.setUuid(uuid);
        communErrorDTO.setErrorCode(errorsEnum.getErrorCode());
        communErrorDTO.setErrorMessage(errorsEnum.getErrorMessage());

        return new ResponseEntity<>(communErrorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TechnicalException.class)
    public final ResponseEntity<BaseDTO> handleTechnicalException(TechnicalException e, WebRequest request) {
        String uuid = UUID.randomUUID().toString();
        log.error("Technical Error ID {} ", uuid, e);
        CommunErrorDTO communErrorDTO = new CommunErrorDTO();
        ErrorsEnum errorsEnum = e.getErrorsEnum();
        log.error("Error message : {} ", errorsEnum.getErrorMessage());

        communErrorDTO.setError(errorsEnum.getError());
        communErrorDTO.setWarning(errorsEnum.getWarning());
        communErrorDTO.setUuid(uuid);
        communErrorDTO.setErrorCode(errorsEnum.getErrorCode());
        communErrorDTO.setErrorMessage(errorsEnum.getErrorMessage());

        return new ResponseEntity<>(communErrorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<ValidationResponseDTO> handleValidationException(ValidationException e,
                                                                                 WebRequest request) {
        String uuid = UUID.randomUUID().toString();
        log.error("Validation Error ID {} ", uuid, e);

        ValidationResponseDTO validationResponseDTO = new ValidationResponseDTO();

        Map<String, String> mapErrors = new HashMap<>();

        Errors vaidationErrors = e.getErrors();
        List<ObjectError> errorsList = vaidationErrors.getAllErrors();
        for (ObjectError objectError : errorsList) {
            mapErrors.put(objectError.getCode(), objectError.getDefaultMessage());
        }
        validationResponseDTO.setMapErrors(mapErrors);
        validationResponseDTO.setIsError(true);

        return new ResponseEntity<>(validationResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}