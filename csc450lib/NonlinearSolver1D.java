package csc450lib;

/**
 * NonlinearSolver1D.java
 * @author Ian Pascoe
 * Created 2-24-2019
 * 
 * The objective of this class is to find one solution of a nonlinear equation.
 * This abstract parent class will have one method that will be implemented by
 * its child classes. These child classes will be the different methods for finding
 * the solution where f[x]=0 in a certain interval.
 */

import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;

public class NonlinearSolver1D 
{
	
	/**
	 * Default constructor.
	 */
	public NonlinearSolver1D() 
	{}
	
	/**
	 * This is the implementation of the bisection algorithm for finding a function's
	 * root. It takes a search bracket and divides it until a solution is found in the
	 * initial interval. If max intervals is reached, the return value of the function
	 * will be zero and the boolean success will be false.
	 * 
	 * @param f Function1D to find the root of.
	 * @param a Beginning of the search bracket.
	 * @param b End of the search bracket.
	 * @param n Maximum number of intervals.
	 * @param tolerance Smallest interval in which the root can exist.
	 * @return The approximation of the root in the interval.
	 */
	public NonlinearSolverRecord1D bisection(Function1D f, float a, float b, int n, float tolerance)
	{
		NonlinearSolverRecord1D result = null;
		float c = 0;
		int iter = 1;
		
		if((f.func(b) * f.func(a) > 0) ||  (a == b))
		{
			throw new CSC450Exception(CSC450ErrorCode.INVALID_SEARCH_BRACKET);
		}
		
		while(iter <= n)
		{
			c = (a + b)/2;
			if(f.func(c) == 0 || (b - a)/2 < tolerance)
			{
				result = new NonlinearSolverRecord1D(c, f.func(c), iter, true);
				return result;
			}  

			iter++;

			if((f.func(c) * f.func(a) > 0))
			{
				a = c; 
			} 

			else 
			{
				b = c;
			}
		}	
		result = new NonlinearSolverRecord1D(0, 0, n, false);	
		return result;
	}
	
	
	/**
	 * This is the implementation of the Newton-Rhapson algorithm for finding a
	 * function's root.
	 * 
	 * @param f Function1D to find the root of.
	 * @param a Starting point for the Newton-Rhapson algorithm.
	 * @param b End of the interval in which Newton-Rhapson will iterate to. If reached, solution was not found.
	 * @param n Maximum number of iterations.
	 * @param tolerance Smallest interval in which solution will exist.
	 * @return Package of information about the approximation of the root of the function.
	 */
	public NonlinearSolverRecord1D newtonRhapson(Function1D f, float x0, int n, float tolerance)
	{
		int iter = 1;
		boolean foundSolution = false;
		NonlinearSolverRecord1D result;
		
		while(iter<=n)
		{
			float x1 = x0 - (f.func(x0)/f.dfunc(x0));

			if((Math.abs(x1-x0)) <= (tolerance*Math.abs(x1)))
			{
				foundSolution = true;
				result = new NonlinearSolverRecord1D(x1, f.func(x1), iter, foundSolution);
				return result;
			}
			
			x0 = x1;
			iter++;
		}
		
		result = new NonlinearSolverRecord1D(0, 0, n, foundSolution);
		return result;
	}
	
	/**
	 * This function implements the secant method of finding a functions root.
	 * 
	 * @param f Function1D to find the root of.
	 * @param a x0 value for the algorithm
	 * @param b x1 value for the algorithm.
	 * @param n Maximum number of iterations.
	 * @param tolerance The smallest interval in which the value of the function can exist.
	 * @result A package of information containing the secant-method approximation of the root.
	 */
	public NonlinearSolverRecord1D secant(Function1D f, float x0, float x1, int n, float tolerance)
	{
		float y0 = f.func(x0);
		float y1 = f.func(x1);
		int iter = 1;
		NonlinearSolverRecord1D result;
		
		while(iter < n)
		{
			y0 = f.func(x0);
			y1 = f.func(x1);
			float x = x1-y1*((x1-x0)/(y1-y0));
			float y = f.func(x);
			
			if(Math.abs(y) <= tolerance)
			{
				result = new NonlinearSolverRecord1D(x, y, iter, true);
				return result;
			}
			
			x0 = x1;
			x1 = x;
			y0 = y1; 
			y1 = y;
			iter++;
		}
		
		result = new NonlinearSolverRecord1D(0, 0, iter, false);
		return result;
	}
	
	/**
	 * This custom algorithm uses a mix of bisection and the secant algorithm to find
	 * the root of a nonlinear function. It first splits the search interval to a
	 * certain extent (0.01), then uses the Newton-Rhapson algorithm to iterate along
	 * the smaller interval to find the root.
	 * 
	 * @param f Function1D to find the root of.
	 * @param a Beginning of search interval.
	 * @param b End of search interval.
	 * @param n Maximum # of iterations.
	 * @param tolerance The smallest interval of where the root can exist.
	 * @return A package of information containing the custom approximation of the root.
	 */
	public NonlinearSolverRecord1D custom(Function1D f, float a, float b, int n, float tolerance)
	{
		NonlinearSolverRecord1D result = null;
		float c = 0;
		int iter = 1;
		
		if((f.func(b) * f.func(a) > 0) ||  (a == b))
		{
			throw new CSC450Exception(CSC450ErrorCode.INVALID_SEARCH_BRACKET);
		}
		
		while(iter <= n)
		{
			iter++;
			c = (a + b)/2;
			if(f.func(c) == 0 || (b - a)/2 < tolerance)
			{
				result = new NonlinearSolverRecord1D(c, f.func(c), iter, true);
				return result;
			}

			if(f.func(c) * f.func(a) > 0)
			{
				a = c;
				if(Math.abs(b-a) < 1)
				{
					result = this.secant(f, a, b, n-iter, tolerance);
					result.numIter = result.numIter + iter;
					return result;
				}
				
			} 

			else 
			{
				b = c;
				if(Math.abs(b-a) < 1)
				{
					result = this.secant(f, a, b, n-iter, tolerance);
					result.numIter = result.numIter + iter;
					return result;
				}
			}
		}	
		result = new NonlinearSolverRecord1D(0, 0, n, false);	
		return result;
	}
	
	/**
	 * This implementation of finding the root uses Mathematica's built in functions
	 * to find a more exact approximation of the function's root in a search interval.
	 * 
	 * @param f Function1D to find the root of.
	 * @param a	Beginning of search bracket.
	 * @param b	End of search bracket.
	 * @param n Unused in Mathematica Wrapper. Use any integer.
	 * @param tolerance Unused in Mathematica Wrapper. Use any float.
	 * @return Package of information about the approximation of the root of the function.
	 */
	public NonlinearSolverRecord1D MMA(Function1D f, float a, float b) 
	{
		KernelLink ml = null;
		String expression;
		expression = f.getExpression();
		
		try {
			
			ml = MathLinkFactory.createKernelLink("-linkmode launch -linkname 'C:/Program Files\\Wolfram Research\\Mathematica\\11.3\\MathKernel.exe\'");
		
		} catch (MathLinkException e) {
			
			System.out.println("Fatal error opening link: " + e.getMessage());
			
		}
		
		NonlinearSolverRecord1D result = null;
		
		try {
			
			ml.discardAnswer();
			
			ml.evaluate(expression);
			ml.discardAnswer();
			
			String evaluation = "x/.FindRoot[F[x],{x," + Float.toString(a) + "," + Float.toString(b) + "}]";
			
			ml.evaluate(evaluation);
			ml.waitForAnswer();
			float value = (float) ml.getDouble();
			
			result = new NonlinearSolverRecord1D(value, f.func(value), 1, true);
			
		} catch (MathLinkException e) {
			
			System.out.println("MathLinkException occurred: " + e.getMessage());
			
		} finally {
			
			ml.close();
			
		}
		
		return result;
	}
}
