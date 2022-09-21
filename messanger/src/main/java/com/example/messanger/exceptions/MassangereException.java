package com.example.messanger.exceptions;

import java.security.GeneralSecurityException;

public class MassangereException  extends  RuntimeException {

	public MassangereException(String string, Exception e) {
		super (string , e)  ;
	} 
	
	
	public MassangereException(String exMessage) {
        super(exMessage);
    }

}
