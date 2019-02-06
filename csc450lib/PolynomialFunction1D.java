package csc450lib;

/**
 * PolynomialFunction1D.java
 * @author Ian Pascoe
 * Created 2-3-2019
 * 
 * Implementation of PolynomialFunction1D as defined in assignment documentation. 
 */

import java.lang.Math;

public class PolynomialFunction1D extends Function1D 
{

	/**
	 * Float array for the coefficients of the polynomial function.
	 */
	private float coef[];
	
	/**
	 * Default constructor for the polynomial function using an array of floats as the coefficients.
	 * @param a[] coefficients of the polynomial
	 */
	public PolynomialFunction1D(float a[])
	{
//		Calls the default constructor from Function1D due to polynomials being continuous
		super();
		
//		Sets the coefficient array to the array supplied in the constructor
		coef = a;
	}
	
	/**
	 * Finds the solution of the polynomial at x.
	 * @param x point of interest
	 * @return solution of polynomial at x
	 */
	public float func(float x) 
	{
		try {
		this.isDefinedAt(x);
		} catch(CSC450Exception e) {
			e.printStackTrace();
		}
		
		float returnValue = 0;
		
		for(int i=0; i<coef.length; i++)
		{
			returnValue += (float)(coef[i] * Math.pow(x, i));
		}
		
		return returnValue;
	}
	

	/**
	 * Returns the function as a Mathematica-Style expression in String format.
	 * @return String expression
	 */
	public String getExpression()
	{
		String expression = "F[x_]:= ";
		
		for(int i=0; i<coef.length; i++)
		{
			expression += " " + Float.toString(coef[i]) + "*x^";
			expression += Integer.toString(i);
			if(i != coef.length-1)
				expression += " +";
		}
		
		return expression;
	}

}
