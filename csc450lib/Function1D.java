package csc450lib;

/**
 * Function1D.java
 * @author Ian Pascoe 
 * Created 2-3-2019
 * 
 * The Function1D parent class as defined in the assignment documentation.
 */

public abstract class Function1D  
{
	/**
	 * Domain of Definition for the function
	 */
	private DomainOfDefinition DoD;
	
	/**
	 * Default constructor over entire float range
	 */
	public Function1D()
	{
		float[][] temp = {{-Float.MAX_VALUE, Float.MAX_VALUE}};
		DoD = new DomainOfDefinition(temp);
	}
	
	/**
	 * Constructor for function defined over range ]xmin,xmax[
	 * @param xmin lower bound
	 * @param xmax upper bound
	 */
	public Function1D(float xmin, float xmax)
	{
		float[][] temp = {{xmin, xmax}};
		DoD = new DomainOfDefinition(temp);
	}
	
	/**
	 * Constructor for function defined over a domain
	 * @param d Domain of Definition (2D array of pairs)
	 */
	public Function1D(DomainOfDefinition d)
	{
		DoD = d;
	}
	
	/**
	 * Will find the solution of the function at the parameter provided.
	 * @param x function solved at this value
	 * @return value of function at x
	 */
	public abstract float func(float x);
	
	/**
	 * Will return a Mathematica-Compatible string expression for the function.
	 * @return a Mathematica-Compatible expression
	 */
	public abstract String getExpression();
	
	/**
	 * Reveals whether the function provided is defined at x
	 * @param x point of interest
	 * @return boolean revealing whether a function is defined at x
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
	 * Finds the lower-bound of the function
	 * @return the lower-bound of the function
	 */
	public float getLowerBound() 
	{
		return DoD.getLower(0);
	}
	
	/**
	 * Finds the upper-bound of the function
	 * @return the upper-bound of the function
	 */
	public float getUpperBound()
	{
		return DoD.getUpper(DoD.getLength()-1);
	}
}
