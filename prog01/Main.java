package prog01;

/**
 * Main.java
 * @author Ian Pascoe
 * Created 2-3-2019
 *
 * Simple main to test my Function1D and child class implementations. All output is printed to 'Output.txt'
 * in the project directory.
 */

import java.io.*;
import csc450lib.*;

public class Main 
{

	public static void main(String[] args) throws FileNotFoundException 
	{
		
//      Creating a File object for output 
        PrintStream outputFile = new PrintStream(new File("Output.txt")); 
  
//      Store current System.out before assigning a new value 
        @SuppressWarnings("unused")
		PrintStream console = System.out; 
  
//      Assign output to output stream 
        System.setOut(outputFile); 
        
//		System.out.println("============================================================================");
//		
//		
//		
////		Testing the functionality of PolynomialFunction1D and PolynomialFunction1D_MMA classes
//		System.out.println("Testing PolynomialFunction1D.java & PolynomialFunction1D_MMA.java");
//		System.out.println();
//		
//		float coefficients[] = {2.4f, -3.9f, -5.1f, 2.7f, -1f};
//		Function1D myPolyFunc = new PolynomialFunction1D(coefficients);
////		Function1D myPolyFuncMMA = new PolynomialFunction1D_MMA(coefficients);
//		
//		System.out.println(myPolyFunc.getExpression());
//		
//		System.out.println("Lower Bound: " + myPolyFunc.getLowerBound() 
//							+ " Upper Bound: " + myPolyFunc.getUpperBound());
//		
//		System.out.print("\n\tBegin Testing Output Comparisons\n"
//				+ "--------------------------------------------\n");
//		
//		for(float i=1; i<=10; i = i + 1)
//		{
//			System.out.println("F[" + Float.toString(i) + "] = " + myPolyFunc.func(i));
//			
//			if(i != 0.005)
//				System.out.println();
//		}
		
//		System.out.println();
//		System.out.println("============================================================================");
//		
//		
//		
////		Testing functionality of CustomCosineFunction1D and CustomCosineFunction1D_MMA classes	
//		System.out.println("Testing CustomCosineFunction1D.java & CustomCosineFunction1D_MMA.java");
//		System.out.println();
//		
//		Function1D myCosFunc = new CustomCosineFunction1D();
////		Function1D myCosFuncMMA = new CustomCosineFunction1D_MMA();
//		
//		System.out.println(myCosFunc.getExpression());
//		
//		System.out.println("Lower Bound: " + myCosFunc.getLowerBound() 
//							+ " Upper Bound: " + myCosFunc.getUpperBound());
//		
//		System.out.print("\n\tBegin Testing Output Comparisons\n"
//				+ "--------------------------------------------\n");
//		
//		for(float i=-40; i<=40; i = i + .25f)
//		{
//			System.out.println("F[" + Float.toString(i) + "] = " + myCosFunc.func(i));
//			
//			if(i != 4.0005)
//				System.out.println();
//		}
		
//		System.out.println();
		System.out.println("============================================================================");
		
		
		
//		Testing functionality of LogarithmicFunction1D and LogarithmicFunction1D_MMA classes
		System.out.println("Testing CustomLogarithmicFunction1D.java & CustomLogarithmicFunction1D_MMA.java");
		System.out.println();
	
//		Create an instance of the functions
		Function1D myLogFunc = new CustomLogarithmicFunction1D();
		Function1D myLogFuncMMA = new CustomLogarithmicFunction1D_MMA();

//		Demonstrate getExpression functionality
		System.out.println(myLogFunc.getExpression());
	
//		Demonstrate upper and lower bound functionality
		System.out.println("Lower Bound: " + myLogFunc.getLowerBound() 
							+ " Upper Bound: " + myLogFunc.getUpperBound());
		
//		Demonstrate isDefinedAt functionality
		try {
		System.out.println("Function defined at -0.99999999: " + myLogFunc.isDefinedAt(-0.9999999f));
		} catch(CSC450Exception e) {
			e.printStackTrace();
		}
		
//		Begin testing different function values for comparison
		System.out.print("\n\tBegin Testing Output Comparisons\n"
				+ "--------------------------------------------\n");
		
		for(float i=-5; i<=-1; i = i + 1)
		{
			System.out.println("F[" + Float.toString(i) + "] = " + myLogFunc.func(i) 
			+ "\t\tMMA: " + myLogFuncMMA.func(i));
		}
		
	}	
}