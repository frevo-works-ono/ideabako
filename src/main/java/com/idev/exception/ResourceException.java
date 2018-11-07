package com.idev.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class ResourceException extends RuntimeException {
	@Getter
	private HttpStatus status;
	
	public ResourceException(HttpStatus status) {
		super(status.getReasonPhrase());
		this.status = status;
	}
	
	public ResourceException(HttpStatus status,String msg) {
		super(msg);
		this.status = status;
	}
}
