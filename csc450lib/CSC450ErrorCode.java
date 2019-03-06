package csc450lib;

/**
 * CSC450ErrorCode.java
 * @author Ian Pascoe
 * @author Jean-Yves Herve
 * Edited 2-3-2019
 * 
 * Enumerated type to store error codes and companion messages.
 */

public enum CSC450ErrorCode 
{
	//	0-99 error codes for generic errors
	UNKNOWN_ERROR(0, "Unknown CSC450Lib error"),
	
	//	Error for incorrect format of Domain of Definition
	DOMAIN_OF_DEF_BAD_FORMAT(1, "Incorrect format for Domain of Definition"),
	
	//	100-199 error codes for calculus-related exceptions 
	FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT(100, "Function is not defined at evaluation point"),
	
	ROOT_NOT_FOUND_IN_INTERVAL(101, "Root was not found in the interval provided."),
	
	INVALID_SEARCH_BRACKET(102, "Function is the same sign at both endpoints or both endpoints are equal");
	
	//	200-299 error codes for linear algebra-related exceptions
	
	
	/**
	 * 	The error code
	 */
	private final int code_;

	/**
	 * 	The error message for each error code
	 */
	private final String message_;
	
	CSC450ErrorCode(int code, String message) 
	{
		code_ = code;
		message_ = message;
	}
	
	public int getCode()
	{
		return code_;
	}
	
	public String getMessage()
	{
		return message_;
	}
}
