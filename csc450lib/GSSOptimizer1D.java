/**
 * GSSOptimizer1D.java
 */
package csc450lib;

/**
 * @author Ian Pascoe
 * Created 4-12-2019
 * 
 * This class implements the golden section search algorithm for
 * finding relative minimums.
 */
public class GSSOptimizer1D extends Optimizer1D {
	
	/**
	 * This algorithm was adapted from "Numerical Recipes in C: The Art of Scientific Computing"
	 * with a few edits to make it work with Java. I had to remove some of the modularity due
	 * to Java's By-Reference system.
	 * 
	 * @param f The function we are trying to find the minimum of.
	 * @param a Lower bound of the search bracket.
	 * @param b Upper Bound of the search bracket.
	 * @param tol The desired tolerance for the algorithm.
	 * @return The x-value of the minimum that was found.
	 */
	public float solve(Function1D f, float a, float b, float tol) {
	    //The golden ratio values.
		float R=0.61803399f;
	    float C=(float) (1.0-R);
	    
	    //Set up necessary search values.
	    float f1=0,f2=0,x0,x1,x2,x3;
	    x0 = a;
		float c = b + R*(b - a);
		x3 = c;
		
		if (Math.abs(c-b) > Math.abs(b-a)) {
			x1=b;
			x2=b+C*(c-b);
		} else {
			x2=b;
			x1=b-C*(b-a);
		}
		
		while (Math.abs(x3-x0) > tol*(Math.abs(x1)+Math.abs(x2))) {
			f1=f.func(x1);
			f2=f.func(x2);
			if (f2 < f1) {
				x0 = x1;
				x1 = x2;
				x2 = R*x2+C*x3;
			} else {
				x3 = x2;
				x2 = x1;
				x1 = R*x1+C*x0;
			}
		}
		
		if (f1 < f2) {
			float xmin=x1;
			return xmin;
		} else {
			float xmin=x2;
			return xmin;
		}
	}
}