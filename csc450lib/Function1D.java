package csc450lib;

/**
 * Function1D.java
 * @author Ian Pascoe 
 * Created 2-3-2019
 * 
 * The Function1D parent class as defined in the assignment documentation.
 */

import java.lang.Math;

public abstract class Function1D  
{
	/**
	 * Domain of Definition for the function.
	 */
	private DomainOfDefinition DoD;
	
	/**
	 * Default constructor over entire float range.
	 */
	public Function1D()
	{
		float[][] temp = {{-Float.MAX_VALUE, Float.MAX_VALUE}};
		DoD = new DomainOfDefinition(temp);
	}
	
	/**
	 * Constructor for function defined over range ]xmin,xmax[.
	 * @param xmin Lower bound.
	 * @param xmax Upper bound.
	 */
	public Function1D(float xmin, float xmax)
	{
		float[][] temp = {{xmin, xmax}};
		DoD = new DomainOfDefinition(temp);
	}
	
	/**
	 * Constructor for function defined over a domain.
	 * @param d Domain of Definition (2D array of pairs).
	 */
	public Function1D(DomainOfDefinition d)
	{
		DoD = d;
	}
	
	/**
	 * Will find the solution of the function at the parameter provided.
	 * @param x Function solved at this value.
	 * @return Value of function at x.
	 */
	public abstract float func(float x);
	
	/**
	 * Will return a Mathematica-Compatible string expression for the function.
	 * @return A Mathematica-Compatible expression.
	 */
	public abstract String getExpression();
	
	/**
	 * Reveals whether the function provided is defined at x.
	 * @param x Point of interest.
	 * @return Boolean revealing whether a function is defined at x.
	 */
	public boolean isDefinedAt(float x) throws CSC450Exception
	{
		boolean result = false;
		for(int i=0;i<DoD.getLength(); i++)
		{
			if(x>DoD.getLower(i) && x<DoD.getUpper(i))
			{
				result = true;
			}
			
			else
			{
				throw new CSC450Exception(CSC450ErrorCode.FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT);
			}
		}
		
		return result;
	}
	
	/**
	 * Finds the lower-bound of the function.
	 * @return The lower-bound of the function.
	 */
	public float getLowerBound() 
	{
		return DoD.getLower(0);
	}
	
	/**
	 * Finds the upper-bound of the function.
	 * @return The upper-bound of the function.
	 */
	public float getUpperBound()
	{
		return DoD.getUpper(DoD.getLength()-1);
	}
	
	/**
	 * Finds the first derivative of the function at the x coordinate provided.
	 * This uses Richardson's extrapolation.
	 * @param x Float for where the derivative is to be found.
	 * @return The first derivative of the function.
	 */
	public float dfunc(float x) 
	{
		float result=0;
		float h = 1f;
		for(int n=2, i=1; n<Math.pow(2, 8); n=(int) Math.pow(2, i), i++)
			result = (float) (this.lApprox(x, h/n) + ((this.lApprox(x, h/n) - this.lApprox(x, (float) (h/Math.pow(2, i-1))))/(Math.pow(4, i)-1)));
		return result;
	}
	
	/**
	 * To be implemented by child classes. Returns a boolean revealing
	 * whether the implementation of the derivative gives an exact result.
	 * @return Boolean describing whether the derivative is exact.
	 */
	public abstract boolean derivativeIsExact();
	
	/**
	 * This function is used for the dfunc() function. dfunc uses
	 * Richardson's Extrapolation to find a more precise derivative.
	 * @param x Float for where approximation is to be found.
	 * @return The linear approximation of the first derivative.
	 */
	public float lApprox(float x, float h)
	{
		return (this.func(x+h) - this.func(x-h))/(2*h);
	}
}
