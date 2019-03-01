package Prog02;

/**
 * Main.java
 * @author Ian Pascoe
 * Created 2-10-2019
 * 
 * This program will simply test the functionality of the new function in the CSC450lib:
 * 		
 * 		dfunc(float x)
 */

import csc450lib.*;

public class Main {

	public static void main(String[] args) 
	{
		float coef[] = {2.0f, 3.4f, 2.5f, 1f}; 
		Function1D myPolyFunc = new PolynomialFunction1D(coef);
		Function1D myLogFunc = new CustomLogarithmicFunction1D();
		
		System.out.println(myPolyFunc.getExpression());
		for(float x=2; x<5; x=x+.5f)
		{
			System.out.println("f'[" + x + "]= " + myPolyFunc.dfunc(x));
		}
		
		System.out.println();
		
		System.out.println(myLogFunc.getExpression());
		for(float x=3; x<3.5; x=x+.05f)
		{
			System.out.println("f'[" + x + "]= " + myLogFunc.dfunc(x));
		}
	}

}
