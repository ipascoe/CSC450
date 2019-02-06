package csc450lib;

/**
 * PolynomialFunction1D_MMA.java
 * @author Ian Pascoe
 * Created 2-4-2019
 * 
 * This is the Mathematica-Aware version of PolynomialFunction1D. It calls the mathkernal using a KernalLink.
 */

import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;

public class PolynomialFunction1D_MMA extends Function1D {

	/**
	 * Float array for the coefficients of the polynomial function.
	 */
	private float coef[];
	
	/**
	 * Default constructor for the polynomial function using an array of floats as the coefficients.
	 * @param a[] coefficients for the polynomial
	 */
	public PolynomialFunction1D_MMA(float a[])
	{
		super();
		coef = a;
	}
	
	/**
	 * Finds the solution of the polynomial at x using Mathematica. MathLink is established in this function.
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
		
		KernelLink ml = null;
		String expression;
		expression = this.getExpression();
		
		try {
			
			ml = MathLinkFactory.createKernelLink("-linkmode launch -linkname 'C:/Program Files\\Wolfram Research\\Mathematica\\11.3\\MathKernel.exe\'");
		
		} catch (MathLinkException e) {
			
			System.out.println("Fatal error opening link: " + e.getMessage());
			
		}
		
		float returnValue = 0;
		
		try {
			
			ml.discardAnswer();
			
			ml.evaluate(expression);
			ml.discardAnswer();
			
			String evaluation = "F[" + Float.toString(x) + "]";
			ml.evaluate(evaluation);
			ml.waitForAnswer();
			
			returnValue = (float)ml.getDouble();
			
		} catch (MathLinkException e) {
			
			System.out.println("MathLinkException occurred: " + e.getMessage());
			
		} finally {
			
			ml.close();
			
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
