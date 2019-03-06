package csc450lib;

import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;

public class CustomLogarithmicFunction1D_MMA extends Function1D {

	/**
	 * Default constructor. Domain of definition is needed for this function,
	 * therefore the super(xmin,xmax) constructor is called from Function1D.
	 */
	public CustomLogarithmicFunction1D_MMA()
	{
		super(-1, Float.MAX_VALUE);
	}
	
	/**
	 * Finds the solution of the function at x using Mathematica. MathLink is established in this function.
	 * @param x point of interest
	 * @return solution of function at x
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
