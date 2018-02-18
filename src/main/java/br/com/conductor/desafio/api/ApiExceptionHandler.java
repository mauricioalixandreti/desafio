package br.com.conductor.desafio.api;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.conductor.desafio.view.ErroView;

@RestController
@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ErroView handleException(Exception ex) {
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    	Validator validator = factory.getValidator();
    	
    	if(ex.getCause() instanceof ConstraintViolationException){
    		String msg = ex.getCause().getCause().getMessage();
    		return new ErroView(msg);
    	}
        return new ErroView(ex.getMessage());
    }

}
