package mx.skyguardian.controltower.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="The server encountered an unexpected condition which prevented it from fulfilling the request.") 
public class WialonInternalServerError extends RuntimeException {
	
	private static final long serialVersionUID = 14325196843118L;

}
