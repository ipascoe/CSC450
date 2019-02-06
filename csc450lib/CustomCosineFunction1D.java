package csc450lib;

/**
 * CustomCosineFunction1D.java
 * @author Ian Pascoe
 * Created 2-4-2019
 * 
 * This class will implement the function Cos((x/(2*pi))+pi)+4
 */

import java.lang.Math;

public class CustomCosineFunction1D extends Function1D {
	
	/**
	 * Default constructor for this function
	 * Calls Function1D default constructor due to full real DoD
	 */
	public CustomCosineFunction1D() 
	{
		
		super();
		
	}

	/**
	 * Finds the solution to the function at x.
	 * @param x point of interest
	 * @return solution of function at provided x
	 */
	public float func(float x) 
	{
		try {
		this.isDefinedAt(x);
		} catch(CSC450Exception e) {
			e.printStackTrace();
		}
		
		return (float)Math.cos(((Math.pow(x, 2))/(2*Math.PI)) + Math.PI) + 4;	
		
	}

	/**
	 * Returns a string in Mathematica format
	 * @return a Mathematica-Compatible expression
	 */
	public String getExpression() 
	{
		
		String expression = "F[x_]:= Cos[((x^2)/(2*Pi)) + Pi] + 4";
		
		return expression;
		
	}

}
