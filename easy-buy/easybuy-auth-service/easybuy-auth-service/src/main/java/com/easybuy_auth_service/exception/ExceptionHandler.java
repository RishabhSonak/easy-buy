package com.easybuy_auth_service.exception;

import com.easybuy_auth_service.dto.ErrorDetails;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RestControllerAdvice
public class ExceptionHandler {
      Log logger= LogFactory.getLog(ExceptionHandler.class);
      @org.springframework.web.bind.annotation.ExceptionHandler({BadCredentialsException.class})
      public ResponseEntity<ErrorDetails> badCredentialsExceptionHandler(BadCredentialsException badCredentialsException){
            logger.error(badCredentialsException);
            ErrorDetails errorDetails =new ErrorDetails();
            errorDetails.setError("Unable to authenticate user, Either username or password are incorrect");
            errorDetails.setErrorDetails(badCredentialsException.getMessage());
            errorDetails.setStatus(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
      }
      @org.springframework.web.bind.annotation.ExceptionHandler({UsernameAlreadyExistsException.class})
      public ResponseEntity<ErrorDetails> usernameAlreadyExistsExceptionHandler(UsernameAlreadyExistsException usernameAlreadyExistsException){
            logger.error(usernameAlreadyExistsException);
            ErrorDetails errorDetails =new ErrorDetails();
            errorDetails.setError("Either try with different username or login");
            errorDetails.setErrorDetails(usernameAlreadyExistsException.getMessage());
            errorDetails.setStatus(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
      }
      @org.springframework.web.bind.annotation.ExceptionHandler({ExpiredJwtException.class})
      public ResponseEntity<ErrorDetails> expiredJwtException(ExpiredJwtException expiredJwtException){
            logger.error(expiredJwtException.getMessage());
            logger.error(expiredJwtException.getMessage());
            ErrorDetails errorDetails =new ErrorDetails();
            errorDetails.setError(expiredJwtException.getMessage());
            errorDetails.setErrorDetails(expiredJwtException.getStackTrace().toString());
            errorDetails.setStatus(HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
      }
      @org.springframework.web.bind.annotation.ExceptionHandler({JwtException.class})
      public ResponseEntity<ErrorDetails> jwtException(JwtException jwtException){
            logger.error(jwtException.getMessage());
            logger.error(jwtException.getMessage());
            ErrorDetails errorDetails =new ErrorDetails();
            errorDetails.setError(jwtException.getMessage());
            errorDetails.setErrorDetails(jwtException.getStackTrace().toString());
            errorDetails.setStatus(HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
      }
      @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
      public ResponseEntity<ErrorDetails> generalExceptionHandler(Exception exception){
            logger.error(exception.getMessage());
            ErrorDetails errorDetails =new ErrorDetails();
            errorDetails.setError(exception.getMessage());
            errorDetails.setErrorDetails(exception.getStackTrace().toString());
            errorDetails.setStatus(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
      }
}
