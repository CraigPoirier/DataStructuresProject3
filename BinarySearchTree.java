/**  
 *  The BinarySearchTree class contains methods for creating, inserting, finding
 *  and deleting elements of a binary search tree.  The Node class inside the 
 *  BinarySearchTree class creates the Node before it is inserted into the tree.
 *  The BinarySearchTree class also contains a Node Array for storing, sorting and
 *  displaying Nodes based on population.  The number of Nodes traversed while 
 *  performing a search is tracked and displayed as well.
 *    
 *  @author Craig Poirier
 *  @version 28JUN2019
 */ 

import java.text.NumberFormat;
import java.util.Locale;

public class BinarySearchTree {

	private Node root;
	private int count;
	
	private Node[] minOrMax = new Node[60];	
	private int nTrav = 0;
	
	/**
	 * 
	 * This class creates a Node object.  String stateName and int statePopulation
	 * are taken as arguments for the default constructor.
	 *
	 */
	private class Node 
	{    
		String stateName;    
		int statePopulation;    
		Node leftChild;    
		Node rightChild; 
    
		public Node(String state, int population) 
		{       
			stateName = state;       
			statePopulation = population;    
		}    
		
		/**
		 * Prints the stateName and statePopulation of a node.
		 */
		public void printNode() 
		{      
			System.out.printf("%-25s%,10d\n", stateName, statePopulation);    
		} 
	} 
	
	/**
	 * Default, no-arg constructor the root of the Binary Search Tree.
	 */
	public BinarySearchTree()
	{
		root = null;
	}
	
	/**
	 * nTrav refers to the number of nodes visited when traversing entire Binary Tree.
	 * @return nTrav
	 */
	public int getNTrav()
	{
		return nTrav;
	}
	
	/**
	 * Prints the five minimum population states stored in the states array.
	 */
	public void printFiveMin()
	{
		System.out.println("States with five minimal populations\n");
		System.out.printf("%-26s" + "%10s", "State Name", "State Population\n-------------------------------------------\n");
		
		for(int i = 0; i < 5; i++)
		{
			
			System.out.printf("%-25s%,10d\n", minOrMax[i].stateName, minOrMax[i].statePopulation);
		}
	}
	
	/**
	 * Prints the five maximum population states stored in the states array.
	 */
	public void printFiveMax()
	{
		System.out.println("States with five maximal populations\n");
		System.out.printf("%-26s" + "%10s", "State Name", "State Population\n-------------------------------------------\n");
		
		for(int i = (nTrav - 1); i > (nTrav - 6); i--)
		{
			
			System.out.printf("%-25s%,10d\n", minOrMax[i].stateName, minOrMax[i].statePopulation);
		}
	}
	
	/**
	 * Selection Sort method for sorting the states array in ascending order by population.
	 */
	public void sortNodeAry()
	{
		int out, in, min;
		
		for(out = 0; out < (nTrav - 1); out++)
		{
			min = out;
			for(in = (out + 1); in < nTrav; in++)	
				if(minOrMax[in].statePopulation < minOrMax[min].statePopulation)
					min = in;
				swap(out,min);		
		}
	}
	
	/**
	 * Swap function utilized by the SortNodeAry method.
	 * @param one First node to swap.
	 * @param two Second node to swap.
	 */
	public void swap(int one, int two)
	{
		Node temp = minOrMax[one];
		minOrMax[one] = minOrMax[two];
		minOrMax[two] = temp;
	}
	
	/**
	 * Gets the root of the Binary Search Tree;
	 * @return Binary Search Tree root.
	 */
	public Node getRoot()
	{
		return root;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getCount()
	{
		return count;
	}
	
	/**
	 * Method for inserting a Node object into the Binary Search Tree.
	 * @param state stateName
	 * @param pop statePopulation
	 */
	public void insert(String state, int pop)
	{
		Node newNode = new Node(state,pop);
		
		if(root == null)
			root = newNode;
		else
		{
			Node current = root;
			Node parent;
			while(true)
			{
				parent = current;
				if(state.compareTo(current.stateName) < 0)
				{
					current = current.leftChild;
					if(current == null)
					{
						parent.leftChild = newNode;
						return;
					}
					
				}
				
				else
				{
					current = current.rightChild;
					if(current == null)
					{
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
		
	}
	
	/**
	 * Method for finding a Node based on stateName;
	 * @param name stateName
	 * @return the node containing stateName
	 */
	public Node find(String name)
	{
		Node current = root;
		count = 1;
		
		while(current.stateName.compareTo(name) != 0)
		{
			if(name.compareTo(current.stateName) < 0)
			{
				current = current.leftChild;
				count++;
			}
			else
			{
				current = current.rightChild;
				count++;
			}
			if(current == null)
			{
				System.out.println(name + " is not found");
				System.out.println(count + " nodes visited\n");
				return null;
			}
		}
		NumberFormat number = NumberFormat.getNumberInstance(Locale.US);
		String numberString = number.format(current.statePopulation);
		System.out.println(current.stateName + " is found with a population of " + numberString);
		System.out.println(count + " nodes visited\n");
		return current;
	}
	
	/**
	 * Method to delete a node in the Binary Search Tree.
	 * @param key
	 * @return
	 */
	public boolean delete(String key)
	{
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		
		while(current.stateName.compareTo(key) != 0)
		{
			parent = current;
			if (key.compareTo(current.stateName) < 0)
			{
				isLeftChild = true;
				current = current.leftChild;
			}
			else
			{
				isLeftChild = false;
				current = current.rightChild;
			}
			
			if(current == null)
			{
				return false;
			}
		}
		
		if (current.leftChild==null && current.rightChild == null)
		{
			if(current == root)
				root = null;
			else if (isLeftChild)
				parent.leftChild = null;
			else 
				parent.rightChild = null;
		}
		
		else if (current.rightChild == null)
		{
			if (current == root)
				root = current.leftChild;
			else if(isLeftChild)
				parent.leftChild = current.leftChild;
			else
				parent.rightChild = current.leftChild;
		}
		
		else if (current.leftChild==null)
		{
			if(current == root)
				root = current.rightChild;
			else if (isLeftChild)
				parent.leftChild = parent.rightChild;
			else
				parent.rightChild = current.rightChild;
		}
		
		else
		{
			Node successor = getSuccessor(current);
			
			if(current == root)
				root = successor;
			else if(isLeftChild)
				parent.leftChild = successor;
			else
				parent.rightChild = successor;
			
			successor.leftChild = current.leftChild;
		}
		System.out.println(current.stateName + " has been deleted from tree");
		return true;
		
	}
	
	/**
	 * Method to get the successor node in the Binary Search Tree.
	 * @param delNode node to be deleted
	 * @return successor
	 */
	private Node getSuccessor(Node delNode)
	{
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;
		
		while (current != null)
		{
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}
		
		if (successor != delNode.rightChild)
		{
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		
		return successor;
	}
	
	/**
	 * Method to print the Binary Tree if traversed in order.
	 * @param localRoot root of tree
	 */
	public void printInorder(Node localRoot)	
	{
		if (localRoot == root)
		{
			System.out.println("Inorder Traversal:\n");
			System.out.printf("%-26s" + "%10s", "State Name", "State Population\n-------------------------------------------\n");
		}
		
		if(localRoot != null)
		{
			printInorder(localRoot.leftChild);
			localRoot.printNode();
			printInorder(localRoot.rightChild);
		}
	}
	
	/**
	 * Method to print Binary Tree in preorder.
	 * @param localRoot root of tree
	 */
	public void printPreorder(Node localRoot)
	{
		if (localRoot == root)
		{
			System.out.println("Preorder Traversal:\n");
			System.out.printf("%-26s" + "%10s", "State Name", "State Population\n-------------------------------------------\n");
		}
			
		if(localRoot != null)
		{		
			localRoot.printNode();
			printPreorder(localRoot.leftChild);
			printPreorder(localRoot.rightChild);		
		}
	}
	
	/**
	 * Method to print Binary Tree in postorder.
	 * @param localRoot root of tree
	 */
	public void printPostorder(Node localRoot)
	{
		if (localRoot == root)
		{
			System.out.println("Postorder Traversal:\n");
			System.out.printf("%-26s" + "%10s", "State Name", "State Population\n-------------------------------------------\n");
		}
		
		if(localRoot != null)
		{
			printPostorder(localRoot.leftChild);
			printPostorder(localRoot.rightChild);
			localRoot.printNode();
		}
	}
	
	/**
	 * Method to recursively traverse the entire Binary Search Tree.
	 * @param localRoot root of tree
	 */
	public void traverse(Node localRoot)	
	{
		if(localRoot != null)
		{
			traverse(localRoot.leftChild);
			minOrMax[nTrav] = localRoot;
			nTrav++;
			traverse(localRoot.rightChild);
		}
	}
}


