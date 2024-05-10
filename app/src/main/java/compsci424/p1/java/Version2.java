/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.ArrayList;

/** 
 * Implements the process creation hierarchy for Version 2, which does
 * not use linked lists.
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */


/**
 * Didnt know whether we're supposed to remove processes from the array completely or just make the blank so I just removed them completely
 * 
 * 
 */
public class Version2 
{
	// Declare any class/instance variables that you need here.
	ArrayList<Version2PCB> pcbArray = new ArrayList<Version2PCB>();
	Version2PCB V2;
	/**
	 * Default constructor. Use this to allocate (if needed) and
	 * initialize the PCB array, create the PCB for process 0, and do
	 * any other initialization that is needed. 
	 */
	public Version2() 
	{
		V2 = new Version2PCB(-1);
		pcbArray.add(V2);
		V2.setPId(pcbArray.indexOf(V2));
		V2.setFirstPID(pcbArray.indexOf(V2));
	}

	/**
	 * Creates a new child process of the process with ID parentPid. 
	 * @param parentPid the PID of the new process's parent
	 * @return 0 if successful, not 0 if unsuccessful
	 */
	int create(int parentPid) 
	{
		// If parentPid is not in the process hierarchy, do nothing; 
		// your code may return an error code or message in this case,
		// but it should not halt
		if (parentPid >= pcbArray.size())
		{
			System.err.println("Process "+ parentPid +" is not in the system\n");
			return -1;
		}
		// Assuming you've found the PCB for parentPid in the PCB array:
		// 1. Allocate and initialize a free PCB object from the array
		//    of PCB objects
		pcbArray.add(V2 = new Version2PCB(parentPid));
		V2.setPId(pcbArray.indexOf(V2));
		V2.setFirstPID(pcbArray.indexOf(V2));
		// 2. Connect the new PCB object to its parent, its older
		//    sibling (if any), and its younger sibling (if any)
		if (pcbArray.get(parentPid).getFirstChild() == 0) //Check if parentPid has a first child
		{
			pcbArray.get(parentPid).setFirstChild(V2.getPId());
			pcbArray.get(V2.getPId()).setParent(parentPid);
		}
		else
		{
			pcbArray.get(V2.getPId()).setParent(parentPid);
			for (int i = pcbArray.get(parentPid).getFirstChild(); i < pcbArray.size(); i++)
			{
				if (pcbArray.get(i).getYoungerSibling() == 0 && pcbArray.get(i).getParent() == parentPid && i != V2.getPId())
				{
					pcbArray.get(i).setYoungerSibling(V2.getPId());
					pcbArray.get(V2.getPId()).setOlderSibling(pcbArray.get(i).getPId());
				}
			}
		}




		// You can decide what the return value(s), if any, should be.
		// If you change the return type/value(s), update the Javadoc.
		return 0; // often means "success" or "terminated normally"
	}

	/**
	 * Recursively destroys the process with ID parentPid and all of
	 * its descendant processes (child, grandchild, etc.).
	 * @param targetPid the PID of the process to be destroyed
	 * @return 0 if successful, not 0 if unsuccessful
	 */
	int destroy (int targetPid) 
	{
		// If targetPid is not in the process hierarchy, do nothing; 
		// your code may return an error code or message in this case,
		// but it should not halt
		
		
		
		for (int i = 0; i <= pcbArray.size(); i++)
		{
			if (i ==  pcbArray.size())
			{
				System.err.println("Process "+ targetPid +" is not in the system\n");
				return -1;
			}
			else if (pcbArray.get(i).getFirstPID() == targetPid)
			{
				targetPid = pcbArray.get(i).getPId();
				break;
			}
			
		}
		
		
		
		// Assuming you've found the PCB for targetPid in the PCB array:
		// 1. Recursively destroy all descendants of targetPid, if it
		//    has any, and mark their PCBs as "free" in the PCB array 
		//    (i.e., deallocate them)
		if (pcbArray.get(targetPid).getFirstChild() != 0) //Check if parentPid has a first child
		{
			destroy(pcbArray.get(targetPid).getFirstChild());
		}
		else if (pcbArray.get(targetPid).getYoungerSibling() != 0)
		{
			destroy(pcbArray.get(targetPid).getYoungerSibling());
		}
		// 2. Adjust connections within the hierarchy graph as needed to
		//    re-connect the graph
		
		
		if (pcbArray.get(pcbArray.get(targetPid).getParent()).getFirstChild() == targetPid) //Check if you're the first child
		{
			pcbArray.get(pcbArray.get(targetPid).getParent()).setFirstChild(0);
		}
		if (pcbArray.get(targetPid).getOlderSibling() != 0) //Check if you have an older sibling
		{
			pcbArray.get(pcbArray.get(targetPid).getOlderSibling()).setYoungerSibling(0);
		}
		if (pcbArray.get(targetPid).getOlderSibling() != 0 && pcbArray.get(targetPid).getYoungerSibling() != 0 && pcbArray.get(targetPid).getFirstChild() == 0)
			// Check to see if you are at the top level and you aren't the youngest or oldest
		{
			//Set older sibling's younger sibling to your younger sibling
			pcbArray.get(pcbArray.get(targetPid).getOlderSibling()).setYoungerSibling(pcbArray.get(targetPid).getYoungerSibling());
		}
		
		// 3. Deallocate targetPid's PCB and mark its PCB array entry
		//    as "free"
		pcbArray.remove(targetPid);

		//Fix graph to no longer show processes
		for (int i = 0; i < targetPid; i++)
		{
			if (pcbArray.get(i).getFirstChild() >= targetPid)
			{
				pcbArray.get(i).setFirstChild(pcbArray.get(i).getFirstChild() - 1);
			}
			if (pcbArray.get(i).getYoungerSibling() >= targetPid)
			{
				pcbArray.get(i).setYoungerSibling(pcbArray.get(i).getYoungerSibling() - 1);
			}
		}
		for (int i = targetPid; i < pcbArray.size(); i++)
		{
			if (pcbArray.get(i).getParent() >= targetPid)
			{
				pcbArray.get(i).setParent(pcbArray.get(i).getParent() - 1);
			}
			if (pcbArray.get(i).getYoungerSibling() >= targetPid)
			{
				pcbArray.get(i).setYoungerSibling(pcbArray.get(i).getYoungerSibling() - 1);
			}
			if (pcbArray.get(i).getOlderSibling() >= targetPid)
			{
				pcbArray.get(i).setOlderSibling(pcbArray.get(i).getOlderSibling() - 1);
			}
			if (pcbArray.get(i).getFirstChild() >= targetPid)
			{
				pcbArray.get(i).setFirstChild(pcbArray.get(i).getFirstChild() - 1);
			}
			pcbArray.get(i).setPId(i);
		}

		

		// You can decide what the return value(s), if any, should be.
		// If you change the return type/value(s), update the Javadoc.
		return 0; // often means "success" or "terminated normally"
	}

	/**
	 * Traverse the process creation hierarchy graph, printing
	 * information about each process as you go. See Canvas for the
	 * *required* output format. 
	 *         
	 * You can directly use "System.out.println" statements (or
	 * similar) to send the required output to stdout, or you can
	 * change the return type of this function to return the text to
	 * the main program for printing. It's your choice. 
	 */
	void showProcessInfo() 
	{
		for (int i = 0; i < pcbArray.size(); i++)
		{
			if (pcbArray.get(i).getFirstChild() != 0)
			{
				System.out.print("Process " + pcbArray.get(i).getPId() + ": Parent is " + pcbArray.get(i).getParent() + " and children are " 
						+ pcbArray.get(i).getFirstChild() + " ");
				int temp = 0;
				if (pcbArray.get(i).getFirstChild() != 0 && pcbArray.get(pcbArray.get(i).getFirstChild()).getYoungerSibling() != 0)
				{
					temp = pcbArray.get(i).getFirstChild();
					do {

						System.out.print(pcbArray.get(temp).getYoungerSibling() + " ");
						temp = pcbArray.get(temp).getYoungerSibling();

					} while (pcbArray.get(temp).getYoungerSibling() != 0);
				}
				System.out.println();
			}
			else
			{
				System.out.println("Process " + pcbArray.get(i).getPId() + ": Parent is " + pcbArray.get(i).getParent() + " and has no children");
			}

		}
		System.out.println("\nend\n");

	}

}

/* If you need or want more methods, feel free to add them. */


