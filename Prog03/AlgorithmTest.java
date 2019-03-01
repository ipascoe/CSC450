package Prog03;

/**
 * AlgorithmTest.java - Prog03
 * @author Ian Pascoe
 * Created 2-24-2019
 * 
 * This class simply implements tests on algorithms for finding the root of various 
 * functions that I have implemented previously. The root is found using various 
 * algorithms as defined in the assignment documentation.
 */

import csc450lib.*;

public class AlgorithmTest {

	public static void main(String[] args)
	{
		
//		set up storage objects
		NonlinearSolverRecord1D cosResultBi;
		NonlinearSolverRecord1D cosResultNR;
		NonlinearSolverRecord1D cosResultSec;
		NonlinearSolverRecord1D cosResultCustom;
		NonlinearSolverRecord1D cosResultMMA;
		
//		initialize the nonlinearsolver1d class
		NonlinearSolver1D cosSol = new NonlinearSolver1D();		
		
//		initialize the function we will test the algorithm on
		Function1D myCosFunc = new CustomCosineFunction1D();
		Function1D myCosFuncMMA = new CustomCosineFunction1D_MMA();
		
//		fill the storage objects with the results from the nonlinear solver
		cosResultBi = cosSol.bisection(myCosFunc, 3f, 4f, 10000, (float) 1E-6);
		cosResultNR = cosSol.newtonRhapson(myCosFunc, 3f, 50, (float) 1E-7);
		cosResultSec = cosSol.secant(myCosFunc, 1f, 4f, 100, (float) 1E-7);
		cosResultCustom = cosSol.custom(myCosFunc, 2f, 4f, 50, (float) 1E-7);
		cosResultMMA = cosSol.MMA(myCosFuncMMA, 2, 4);
		
		System.out.println("=======================================================================");
		System.out.println("Testing root of custom cosine function: " 
							+ myCosFunc.getExpression() + '\n');
		
//		print results
		System.out.println("Bisection\n Successful: " + cosResultBi.success() + 
							"; x = " + Float.toString(cosResultBi.returnX()) + "; f[x] = "
							+ Float.toString(cosResultBi.returnVal()) + "; Iterations: "
							+ Integer.toString(cosResultBi.returnIter()) + '\n');
		
		System.out.println("Newton-Rhapson\n Successful: " + cosResultNR.success() + "; x = "
							+ Float.toString(cosResultNR.returnX()) + "; f[x] = "
							+ Float.toString(cosResultNR.returnVal()) + "; Iterations: "
							+ Integer.toString(cosResultNR.returnIter())+ '\n');	
		
		System.out.println("Secant\n Successful " + cosResultSec.success() + "; x = "
							+ Float.toString(cosResultSec.returnX()) + "; f[x] = "
							+ Float.toString(cosResultSec.returnVal()) + "; Iterations: "
							+ Integer.toString(cosResultSec.returnIter()) + '\n');	
		
		System.out.println("Custom\n Successful: " + cosResultCustom.success() + "; x = "
							+ Float.toString(cosResultCustom.returnX()) + "; f[x] = "
							+ Float.toString(cosResultCustom.returnVal()) + "; Iterations: "
							+ Integer.toString(cosResultCustom.returnIter()) + '\n');	
		
		System.out.println("Mathematica Aware\n Successful: " + cosResultMMA.success() + "; x = "
							+ Float.toString(cosResultMMA.returnX()) + "; f[x] = "
							+ Float.toString(cosResultMMA.returnVal()) + "; Iterations: "
							+ Integer.toString(cosResultMMA.returnIter()));
		
		System.out.println("=======================================================================");
	}
}
