/**
 * DerivativeOptimizer1D.java
 */
package csc450lib;

/**
 * @author Ian Pascoe
 * Created 4-15-2019
 * 
 * This is my own custom optimization algorithm. It looks at the derivative at one point and
 * moves it in either direction based on the value of its derivative at that point. If the 
 * derivative is negative, we take a small step to the right, and vis versa for a positive
 * derivative.
 */
public class DerivativeOptimizer1D extends Optimizer1D {
	
	/**
	 * @param f Function to find the minimum of.
	 * @param a The lower bound of the search bracket.
	 * @param b The upper bound of the search bracket.
	 * @param tol Desired machine precision.
	 * @return The x-value of the found minimum.
	 */
	public float solve(Function1D f, float a, float b, float tol) {
		float result = 0;
		
		if(f.dfunc(a)*f.dfunc(b)>0 || a>=b) {
			throw new CSC450Exception(CSC450ErrorCode.INVALID_SEARCH_BRACKET);
		}
		
		else if(f.dfunc(a)*f.dfunc(b)==0) {
			if(a==0) {
				return a;
			}
			
			else {
				return b;
			}
		}
		
		else {
		
			while(Math.abs(f.dfunc(a))>tol) {
				if(f.dfunc(a)<0) {
					a = a + Math.abs(f.dfunc(a)/(b-a));
				}
				
				else {
					a = a - Math.abs(f.dfunc(a)/(b-a));
				}
			}
			result = a;
		}
		
		return result;
	}

}
