package com.sales.exceptions;

import java.lang.Exception;

@SuppressWarnings("serial")
public class EmptyID extends Exception{

	public EmptyID(String message) {
        super(message);
    }
	
}