/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.LinkedList;

/**
 * The process control block structure that is used to track a
 * process's parent and children (if any) in Version 1.
 */
public class Version1PCB 
{
    int parent = 0;
    LinkedList<Integer> children = new LinkedList<Integer>();
    private int pId = 0;
    private int firstPID = 0;
    
    Version1PCB(int parent)
    {
    	setParent(parent);
    }

	public int getParent() 
	{
		return parent;
	}

	public void setParent(int parent) 
	{
		this.parent = parent;
	}

	public LinkedList<Integer> getChildren() 
	{
		return children;
	}

	public void setChildren(LinkedList<Integer> children) 
	{
		this.children = children;
	}

	public int getpId()
	{
		return pId;
	}
	public void setpId(int pId) 
	{
		this.pId = pId;
	}
	public int getFirstPID()
	{
		return firstPID;
	}
	public void setFirstPID(int firstPID) 
	{
		this.firstPID = firstPID;
	}
}