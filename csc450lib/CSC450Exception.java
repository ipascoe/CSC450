package csc450lib;

/**
 * CSC450Exception.java
 * @author Ian Pascoe
 * @author Jean-Yves Herve
 * Edited 2-3-2019 
 *
 * Exception starter to handle errors in the CSC450lib package.
 */

public class CSC450Exception extends RuntimeException 
{
	private static final long serialVersionUID = 4468140123798179067L;
	
	private String message_;
	private int code_;
	
	public CSC450Exception(CSC450ErrorCode code)
	{
		message_ = code.getMessage();
	}

	public int getCode()
	{
		return code_;
	}
	
	public String getMessage()
	{
		return message_;
	}
	
	public String getCodeAndMessage()
	{
		String outStr = "Exception code " + code_ + ": " + message_;

		return outStr;
	}
}
