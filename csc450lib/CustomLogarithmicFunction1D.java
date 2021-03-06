package csc450lib;

/**
 * CustomLogarithmicFunction1D.java
 * @author Ian Pascoe
 * Created 2-4-2019
 * 
 * This class will represent the custom log function F[x_]:=2*ln(x+1)
 */

import java.lang.Math;

public class CustomLogarithmicFunction1D extends Function1D {

	/**
	 * Default constructor. Since this function is not defined for all real numbers, 
	 * domain of definition is ]-1-Float.MAX_VALUE[.
	 */
	public CustomLogarithmicFunction1D()
	{
		super(-1, Float.MAX_VALUE);
	}
	
	/**
	 * Finds the solution of the function at x using Mathematica. MathLink is established in this function.
	 * @param x point of interest
	 * @return solution of function at x
	 */
	public float func(float x) {
		float returnValue = 0;
		
		try {
		this.isDefinedAt(x);
		} catch(CSC450Exception e) {
			e.printStackTrace();
		}
		
		returnValue = (float) (2*Math.log((Math.pow(x, 5)+1)/(Math.pow(x,3)+5)));
		
		return returnValue;
	}

	/**
	 * Simple function returning the Mathematica-Compatible expression
	 * @return String expression
	 */
	public String getExpression() {
		return "F[x_]:= 2*Log[((x^5)+1)/((x^3)+5)]";
	}
	
	/**
	 * To be implemented by child classes. Returns a boolean revealing
	 * whether the implementation of the derivative gives an exact result.
	 * @return Boolean describing whether the derivative is exact.
	 */
	public boolean derivativeIsExact()
	{
		return false;
	}

}
