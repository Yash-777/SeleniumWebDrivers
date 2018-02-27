package com.github.yash777.driver;

/**
 * WebDriverException is a custom exception class.
 * 
 * @author yashwanth.m
 *
 */
public class WebDriverException extends RuntimeException {

	private static final long serialVersionUID = 1448252457771538150L;
	public WebDriverException(String message) {
		super(message);
	}
	
	public WebDriverException(Throwable cause) {
		super(cause);
	}
}