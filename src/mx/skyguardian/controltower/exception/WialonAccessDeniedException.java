package mx.skyguardian.controltower.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="Unknown user or Password incorrect") 
public class WialonAccessDeniedException extends RuntimeException {
	
	private static final long serialVersionUID = 123904213L;

}
