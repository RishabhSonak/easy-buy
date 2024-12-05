package com.easybuy.user_details_service.exception_handler;

import com.easybuy.user_details_service.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Optional;

@RestControllerAdvice
@Component
public class ExceptionHandler {
      private final Log logger = LogFactory.getLog(ExceptionHandler.class);
      @org.springframework.web.bind.annotation.ExceptionHandler(UsernameNotFoundException.class)
      public Optional<User> usernameNotFoundException(UsernameNotFoundException usernameNotFoundException){
      logger.error("UsernameNotFoundException occured");
      return Optional.empty();
      }
      @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
      public ResponseEntity<String> generalExceptionHandler(Exception exception){
            StackTraceElement location = exception.getStackTrace()[0];  // location of the exception
            logger.error("An exception occurred: " + exception.getMessage() + " at location: " +
                     location.getClassName() + "." + location.getMethodName() + " (line " +
                     location.getLineNumber() + ")"
            + Arrays.toString(exception.getStackTrace()));

            return new ResponseEntity<>(exception.getMessage(),HttpStatus.valueOf(500));
      }

}
