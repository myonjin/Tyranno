//package com.tyranno.ssg.global;
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class ExceptionHandler {
//
//    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
//    public ResponseEntity<ErrorResponseEntity> handleBusinessException(BusinessException e) {
//        ErrorCode errorCode = e.getErrorCode();
//        ErrorResponseEntity response = new ErrorResponseEntity(errorCode.getStatus(), errorCode.getCode(), errorCode.getDescription());
//
//        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
//    }
//
//    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponseEntity> handleException(Exception e) {
//        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
//        ErrorResponseEntity response = new ErrorResponseEntity(errorCode.getStatus(), e.getMessage(), errorCode.getDescription());
//
//        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
//    }
//
//}