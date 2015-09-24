// CS 0445 Spring 2014
// I took the author's BinaryNode class and added the traversal methods to it
// to demonstrate their functionality.  See the added methods at the bottom
// of this file.  Note that this class is within a package.  For information on
// creating and using Java packages, see:
// http://java.sun.com/docs/books/tutorial/java/package
// Since the class was not meant to be used outside of the package, it was
// declared by the author with "package access", which is the default in Java.
// (i.e. no keyword appeared before the class name).  However, I have changed
// the declaration to public, so that I can use this class in my Example14.java
// handout.
//      Note that the BinaryNode class alone is enough to implement a binary
// tree, if we don't want a separate level of abstraction for the tree class.
// This is demonstrated in Example14.java
package TreePackage;
/**
 * A class that represents nodes in a binary tree.
 * 
 * @author Frank M. Carrano
 * @version 2.0
 */
public class BinaryNode<T> implements BinaryNodeInterface<T>, 
                               java.io.Serializable
{
	private T data;
	private BinaryNode<T> left;
	private BinaryNode<T> right;
  
	public BinaryNode()
	{
		this(null); // call next constructor
	} // end default constructor
  
	public BinaryNode(T dataPortion)
	{
		this(dataPortion, null, null); // call next constructor
	} // end constructor

	public BinaryNode(T dataPortion, BinaryNode<T> leftChild,
                                   BinaryNode<T> rightChild)
	{
		data = dataPortion;
		left = leftChild;
		right = rightChild;
	} // end constructor
  
	public T getData()
	{
		return data;
	} // end getData
  
	public void setData(T newData)
	{
		data = newData;
	} // end setData
  
	public BinaryNodeInterface<T> getLeftChild()
	{
		return left;
	} // end getLeftChild
 
	public BinaryNodeInterface<T> getRightChild()
	{
		return right;
	} // end getRightChild

	public void setLeftChild(BinaryNodeInterface<T> leftChild)
	{
		left = (BinaryNode<T>)leftChild;
	} // end setLeftChild
  
	public void setRightChild(BinaryNodeInterface<T> rightChild)
	{
		right = (BinaryNode<T>)rightChild;
	} // end setRightChild	
	
	public boolean hasLeftChild()
	{
		return left != null;
	} // end hasLeftChild
  
	public boolean hasRightChild()
	{
		return right != null;
	} // end hasRightChild
	
	public boolean isLeaf()
	{
		return (left == null) && (right == null);
	} // end isLeaf
  
  	// 26.06
	public BinaryNodeInterface<T> copy()
	{
		BinaryNode<T> newRoot = new BinaryNode<T>(data);
	  
		if (left != null)
			newRoot.left = (BinaryNode<T>)left.copy();
	    if (right != null)
			newRoot.right = (BinaryNode<T>)right.copy();
	    
		return newRoot;	
	} // end copy

	// 26.11
	public int getHeight()
	{
		return getHeight(this); // call private getHeight
	} // end getHeight

	// 26.11
	private int getHeight(BinaryNode<T> node)
	{
		int height = 0;
	  
		if (node != null)
			height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
	                          
		return height;
	} // end getHeight

	// 26.11
	public int getNumberOfNodes()
	{
		int leftNumber = 0;
		int rightNumber = 0;
	  
		if (left != null)
			leftNumber = left.getNumberOfNodes();
	    
		if (right != null)
			rightNumber = right.getNumberOfNodes();
	    
		return 1 + leftNumber + rightNumber;
	} // end getNumberOfNodes

	// *************************
	// My added methods start here.  Note that the only differences in the three
	// traversal methods are the order of the statements.
	// *************************

	public void inOrder()
	{
		inOrder(this);
	}

	private void inOrder(BinaryNode<T> node)
	{
		if (node != null)
		{
			inOrder(node.left);
			System.out.print(node.data + " ");
			inOrder(node.right);
		}
	}

	public void preOrder()
	{
		preOrder(this);
	}

	private void preOrder(BinaryNode<T> node)
	{
		if (node != null)
		{
			System.out.print(node.data + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	public void postOrder()
	{
		postOrder(this);
	}

	private void postOrder(BinaryNode<T> node)
	{
		if (node != null)
		{
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node.data + " ");
		}
	}
} // end BinaryNode