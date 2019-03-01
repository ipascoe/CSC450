package csc450lib;

/**
 * NonlinearSolverRecord1D.java
 * @author Ian Pascoe
 * 
 * This is simply a storage class for the results of the NonlinearSolver1D class.
 */

public class NonlinearSolverRecord1D {
	
	/**
	 * Float to store the approximation root.
	 */
	private float xStar;
	
	/**
	 * Float to store the function value at the approximation.
	 */
	private float valStar;
	
	/**
	 * Int to store the number of iterations performed by the algorithm.
	 */
	public int numIter;
	
	/**
	 * Boolean to store whether the algorithm was successful.
	 */
	private boolean isSuccess;
	
	/**
	 * Default constructor for the storage object.
	 * 
	 * @param a xValue of the root. Will be 0 if not found.
	 * @param b yValue of the root. Will be 0 if not found.
	 * @param n Number of iterations performed.
	 * @param success True-False value of whether the algorithm was successful in finding the root.
	 */
	public NonlinearSolverRecord1D(float a, float b, int n, boolean success)
	{
		xStar = a;
		valStar = b;
		numIter = n;
		isSuccess = success;
	}
	
	/**
	 * Simply returns the root approximation
	 * 
	 * @return The x value of the root approximation
	 */
	public float returnX()
	{
		return xStar;
	}
	
	/**
	 * Simply returns the function value of the root approximation.
	 * 
	 * @return The function value at the root approximation.
	 */
	public float returnVal()
	{
		return valStar;
	}
	
	/**
	 * Simply returns the # of iterations performed by the algorithm.
	 * 
	 * @return # of iterations performed by the algorithm.
	 */
	public int returnIter()
	{
		return numIter;
	}
	
	/**
	 * Simply returns whether the algorithm was successful in it's search for the root.
	 * 
	 * @return True or False depending on algorithm's success/failure.
	 */
	public boolean success()
	{
		return isSuccess;
	}
}
