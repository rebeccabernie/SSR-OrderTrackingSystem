package com.sales.exceptions;

import java.lang.Exception;

@SuppressWarnings("serial")
public class InvalidQty extends Exception{

	public InvalidQty(String message) {
        super(message);
    }

}
