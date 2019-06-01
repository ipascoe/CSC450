/**
 * Optimizer1D.java
 */
package csc450lib;

/**
 * @author Ian Pascoe
 * Created 4-12-2019
 * 
 * This class is the abstract parent class for the optimizer
 * algorithms I will be implementing.
 */
public abstract class Optimizer1D {
	
	/**
	 * Default constructor for the optimizer.
	 */
	public Optimizer1D() {
	}
	
	/**
	 * Will be implemented by child classes using different
	 * extrema-finding algorithms.
	 * 
	 * @return the relative extrema between two points.
	 */
	public abstract float solve(Function1D f, float a, float b, float tol);
}
