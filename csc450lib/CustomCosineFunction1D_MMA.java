package csc450lib;

import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;

public class CustomCosineFunction1D_MMA extends Function1D {
	
	public CustomCosineFunction1D_MMA()
	{
		super();
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
	 * Returns a string in Mathematica format
	 * @return a Mathematica-Compatible expression
	 */
	public String getExpression() 
	{
		
		String expression = "F[x_]:= Cos[((x^2)/(2*Pi)) + Pi] + 4";
		
		return expression;
		
	}
}
