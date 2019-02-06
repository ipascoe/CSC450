package csc450lib;

/**
 * DomainOfDefinition.java
 * @author Ian Pascoe
 * Created 2-4-2019
 * 
 * This class is used to represent domain of definition that may skip over certain x values.
 * It allows you to store multiple open-ended ranges that act as though they are in union.
 */

public class DomainOfDefinition
{
	/**
	 * 2D array to store the multiple open bounds
	 */
	private float[][] bounds;
	
	/**
	 * Default constructor. Will check to see if the user is inputting a domain in the
	 * correct format which is an array[][2].
	 * @param a the 2D array of open bounds
	 */
	public DomainOfDefinition(float a[][]) 
	{
		if(a.length == 1)
		{
			if((a[0].length != 2) || (a[0][0] >= a[0][1]))
				throw new CSC450Exception(CSC450ErrorCode.DOMAIN_OF_DEF_BAD_FORMAT);
		}
		
		else 
		{
			
			for(int i=0;i<a.length;i++)
			{
				if((a[i].length != 2) || (a[i][0] >= a[i][1]))
				{
					throw new CSC450Exception(CSC450ErrorCode.DOMAIN_OF_DEF_BAD_FORMAT);
				}
					
				if(i > 0 && i < a.length-1)
				{
					if(a[i][1] >= a[i+1][0] || a[i][0] <= a[i-1][1])
					{
						throw new CSC450Exception(CSC450ErrorCode.DOMAIN_OF_DEF_BAD_FORMAT);
					}
				}
			}
		}	
		bounds = a;
	}
	
	/**
	 * Retrieves the amount of boundary pairs in the DoD
	 * @return amount of boundary pairs in DoD
	 */
	public int getLength()
	{
		return bounds.length;
	}
	
	/**
	 * Retrieves the lower bound of the pair of index i
	 * @param i index of the pair being accessed
	 * @return lower bound of pair at i
	 */
	public float getLower(int i)
	{
		return bounds[i][0];
	}
	
	/**
	 * Gets the upper bound at index i of the boundary pairs
	 * @param i index of the pair being accessed
	 * @return upper bound of the pair at i
	 */
	public float getUpper(int i)
	{
		return bounds[i][1];
	}
}
