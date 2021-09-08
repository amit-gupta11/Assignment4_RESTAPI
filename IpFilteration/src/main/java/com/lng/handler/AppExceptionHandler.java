package com.lng.handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.lng.exception.CustomException;

@ControllerAdvice
public class AppExceptionHandler {
	
	
	 static Logger LOGGER = LogManager.getLogger(AppExceptionHandler.class);
	@ExceptionHandler(value= {CustomException.class})
	 
	public ResponseEntity<Object> handleUserServiceException(CustomException e, WebRequest request)
	{
		
		LOGGER.error(e.getMessage());
		JSONObject result= new  JSONObject();
		result.put("message",e.getMessage().trim());
		return new ResponseEntity<Object>(result.toString(),new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	  
	}
}
