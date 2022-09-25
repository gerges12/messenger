package com.example.messanger.exceptions;

public class BlockedWordsException extends RuntimeException {
	
	public BlockedWordsException(String exMessage) {
        super(exMessage);
    }

}
