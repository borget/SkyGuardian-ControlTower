package mx.skyguardian.controltower.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason=" The request could not be understood by the server due to malformed syntax.") 
public class WialonBadRequestException extends RuntimeException {

	private static final long serialVersionUID = 14324689418L;

}
