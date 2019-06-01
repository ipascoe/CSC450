/**
 * Main.java
 */
package Prog04;

import csc450lib.*;

/**
 * @author Ian Pascoe
 * Created 4-12-2019
 * 
 * This class is just to test the newly implemented optimizer functions in
 * the CSC450lib.
 */
public class Main {

	public static void main(String[] args) {
		Function1D func = new CustomCosineFunction1D();
		
		System.out.println("Function: " + func.getExpression() + '\n'
				+ "=================================================");
		
		Optimizer1D solver = new GSSOptimizer1D();
		float x = solver.solve(func, 7.5f, 11, 1e-7f);
		System.out.println(Float.toString(x) + '\t' + Float.toString(func.func(x)));
		
		solver = new DerivativeOptimizer1D();
		x = solver.solve(func, 7.5f, 11, 1e-5f);
		System.out.println(Float.toString(x) + '\t' + Float.toString(func.func(x)));
		
		solver = new BrentsOptimizer1D();
		x = solver.solve(func, 7.5f, 11, 1e-7f);
		System.out.println(Float.toString(x) + '\t' + Float.toString(func.func(x)));
		
		System.out.println();	
		
		float coef[] = {0,1,1};
		func = new PolynomialFunction1D(coef);
		
		System.out.println("Function: " + func.getExpression() + '\n'
						+ "=================================================");
		
		solver = new GSSOptimizer1D();
		x = solver.solve(func, -5.12452f, 5.65427f, 1e-7f);
		System.out.println(Float.toString(x) + '\t' + Float.toString(func.func(x)));
		
		solver = new DerivativeOptimizer1D();
		x = solver.solve(func, -2, 1, 1e-7f);
		System.out.println(Float.toString(x) + '\t' + Float.toString(func.func(x)));
		
		solver = new BrentsOptimizer1D();
		x = solver.solve(func, -5.98754f, 5.12345f, 1e-7f);
		System.out.println(Float.toString(x) + '\t' + Float.toString(func.func(x)));
	}

}
