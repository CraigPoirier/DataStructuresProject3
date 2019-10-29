/**  
 * COP 3538: Project 3 – Binary Search Trees
 * <p>  
 * This program reads states from .csv file and inserts 
 * the state name and population into a Node.  These nodes
 * are inserted into a Binary Search Tree.  The number of 
 * state nodes inserted into the search tree is displayed.
 * <p>
 * The program then displays the entire contents of the 
 * Binary Search Tree.  Multiple methods are called such as
 * printInorder, delete, printPreorder, find, printPostorder
 * and traverse.  The traverse function visits every Node and
 * copies the Nodes into an array of Nodes.  This array is then
 * sorted using selection sort in ascending order based on population.  
 * The first five items are printed (Minimum) and then the last
 * five items are printed (Maximum);
 * 
 * @author Craig Poirier
 * @version 28JUN2019
 */ 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project3 {

	public static void main(String[] args) {
		BinarySearchTree theTree = new BinarySearchTree();
		
		int nElems = 0;
		
		String fileName;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("COP3538 Project 1" + "\n" + "Instructor: Xudong Liu" + "\n\n" + "Array Searches and Sorts");
		
		String state, capital, abbrev, region;
		int pop, seats;
		
		while(true)
		{
			System.out.print("Enter file name: ");
			fileName = scan.nextLine();
			File file = new File(fileName);
				
			try 
			{
				Scanner input = new Scanner(file).useDelimiter(",|\\n");
				input.nextLine();
			
				while (input.hasNext())
				{			
					state = input.next();
					capital = input.next();
					abbrev = input.next();
					pop = input.nextInt();
					region = input.next();
					seats = input.nextInt();	
					
					theTree.insert(state, pop);
					nElems++;
					
				}
				input.close();
					
				System.out.println("\nThere were " + nElems + " state records put on the binary search tree.\n");
			}
			
			catch (FileNotFoundException e) 
			{
				System.out.println("\nError: File Not Found");
				continue;
			}
			
			theTree.printInorder(theTree.getRoot());
			System.out.println();
			
			theTree.delete("California");
			theTree.delete("Florida");
			theTree.delete("New York");
			System.out.println();
			
			theTree.printPreorder(theTree.getRoot());
			System.out.println();
			
			theTree.find("American Samoa");
			theTree.find("Rhode Island");	
			theTree.find("Florida");
			
			theTree.delete("U.S. Virgin Islands");
			theTree.delete("Wyoming");
			theTree.delete("West Virginia");
			theTree.delete("New Mexico");
			System.out.println();
			
			theTree.printPostorder(theTree.getRoot());
			System.out.println();
			
			theTree.traverse(theTree.getRoot()); //populates Node Array
			theTree.sortNodeAry(); //Sorts Node Array based on population
			
			theTree.printFiveMin();
			System.out.println();
				
			theTree.printFiveMax();
			
			System.out.println("\nThanks for using my program!");
			break;
			
		}

	}

}
