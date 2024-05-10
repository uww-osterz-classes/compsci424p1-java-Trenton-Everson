/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;


/**
 * The process control block structure that is used to track a
 * process's parent, first child, younger sibling, and older sibling
 * (if they exist) in Version 2.
 */
public class Version2PCB 
{
	private int parent = 0;
	private int firstChild  = 0;
	private int youngerSibling = 0;
	private int olderSibling = 0;
	private int pId = 0;
	private int firstPID = 0;

	Version2PCB (int parent)
	{
		setParent(parent);
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}
	
	public int getFirstChild() {
		return firstChild;
	}

	public void setFirstChild(int firstChild) {
		this.firstChild = firstChild;
	}
	
	public int getYoungerSibling() {
		return youngerSibling;
	}

	public void setYoungerSibling(int youngerSibling) {
		this.youngerSibling = youngerSibling;
	}

	public int getOlderSibling() {
		return olderSibling;
	}

	public void setOlderSibling(int olderSibling) {
		this.olderSibling = olderSibling;
	}
	public int getPId() {
		return pId;
	}

	public void setPId(int pId) {
		this.pId = pId;
	}
	public int getFirstPID() {
		return firstPID;
	}

	public void setFirstPID(int firstPID) {
		this.firstPID = firstPID;
	}


}
