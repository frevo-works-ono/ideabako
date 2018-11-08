package com.idev.exception;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.idev.app.response.ErrorResponse;
import com.idev.util.DateUtil;
import com.idev.util.JsonUtil;

@RestControllerAdvice
public class RestExceptionHandler {
  /**
   * 4XXエラー
   */
  @ExceptionHandler(ResourceException.class)
  public ResponseEntity<ErrorResponse> notReadable(ResourceException e, HttpServletRequest req) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse(DateUtil.now().getTime(), e.getStatus().value(), e.getMessage()), e.getStatus());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> notReadable(MethodArgumentNotValidException e, HttpServletRequest req) {

    Map<String, String> errors = new HashMap<>();
    e.getBindingResult().getFieldErrors().forEach(f -> {
      errors.put(f.getField(), f.getDefaultMessage());
    });
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse(DateUtil.now().getTime(), HttpStatus.BAD_REQUEST.value(), JsonUtil.bean2string(errors)),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> anotherException(Exception e, HttpServletRequest req) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

    // ログは、大事
    System.err.println("ERROR(500) " + (sdf.format((Calendar.getInstance()).getTime()))
        + ": remoteAddr:" + req.getRemoteAddr()
        + ", remoteHost:" + req.getRemoteHost()
        + ", requestURL:" + req.getRequestURL());
    e.printStackTrace();

    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse(DateUtil.now().getTime(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "unknown error"),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
