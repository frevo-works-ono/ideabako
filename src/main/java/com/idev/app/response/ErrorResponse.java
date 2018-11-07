package com.idev.app.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long timestamp;
	
	private int code;
	
	private String error;
}
