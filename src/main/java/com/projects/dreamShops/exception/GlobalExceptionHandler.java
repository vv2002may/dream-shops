package com.projects.dreamShops.exception;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ProblemDetail;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.servlet.NoHandlerFoundException;

// @ControllerAdvice
// public class GlobalExceptionHandler {

// @ExceptionHandler(NoHandlerFoundException.class)
// public ProblemDetail handleNotFoundException(NoHandlerFoundException ex) {
// ProblemDetail problemDetail =
// ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Resource not found");
// problemDetail.setTitle("Not Found!");
// return problemDetail;
// }
// }

// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.servlet.ModelAndView;
// import org.springframework.web.servlet.NoHandlerFoundException;

// @Controller
// public class GlobalExceptionHandler {

// @ExceptionHandler(NoHandlerFoundException.class)
// public ModelAndView handleNotFoundException() {
// ModelAndView modelAndView = new ModelAndView();
// modelAndView.setViewName("error/404"); // This maps to error/404.html or
// error/404.jsp
// return modelAndView;
// }
// }