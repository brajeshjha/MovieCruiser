package com.stackroute.moviecruiserserverapp.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@RestControllerAdvice
public class ExceptionHandler
{
   @ExceptionHandler(value = {MovieAlreadyExistsException.class})
  public String MovieAlreadyExistsException(MovieAlreadyExistsException e)
  {
    return e.getMessage();
  }
   
    @ExceptionHandler(value = {MovieNotFoundException.class})
  public String newMovieNotFoundException(MovieNotFoundException e)
  {
    return e.getMessage();
  }
    
  @ExceptionHandler(value = {Exception.class})
  public String handleBase(Exception e)
  {
    return e.getMessage();
  }
  
}
