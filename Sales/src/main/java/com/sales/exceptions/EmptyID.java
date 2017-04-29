package com.sales.exceptions;

import java.lang.Exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("serial")
public class EmptyID extends Exception{

	public EmptyID(String message) {
        super(message);
    }
	
}