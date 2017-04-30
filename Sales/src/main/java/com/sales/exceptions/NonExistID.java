package com.sales.exceptions;

import java.lang.Exception;

@SuppressWarnings("serial")
public class NonExistID extends Exception{
	
	public NonExistID(String message) {
        super(message);
    }

}
