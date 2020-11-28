/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atta.springboot.sys.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiBaseException extends RuntimeException
{

	public ApiBaseException(String message)
	{
		super(message);
	}

	public abstract HttpStatus getStatusCode();
}
