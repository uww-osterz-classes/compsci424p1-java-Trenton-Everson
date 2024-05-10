/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;


import java.util.ArrayList;


/** 
 * Implements the process creation hierarchy for Version 1, which uses
 * linked lists.
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */


public class Version1 
{
	// Declare any class/instance variables that you need here.
	ArrayList<Version1PCB> pcbArray = new ArrayList<Version1PCB>();
	Version1PCB V1;
	/**
	 * Default constructor. Use this to allocate (if needed) and
	 * initialize the PCB array, create the PCB for process 0, and do
	 * any other initialization that is needed. 
	 */
	public Version1() 
	{
		V1 = new Version1PCB(-1);
		pcbArray.add(V1);
		V1.setpId(pcbArray.indexOf(V1));
		V1.setFirstPID(pcbArray.indexOf(V1));
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
		if ((parentPid >= pcbArray.size() || (pcbArray.get(parentPid).children.isEmpty() && pcbArray.get(parentPid).parent == -1)) && parentPid != 0)
		{
			System.err.println("Process "+ parentPid +" is not in the system\n");
			return -1;
		}

		// Assuming you've found the PCB for parentPid in the PCB array:
		// 1. Allocate and initialize a free PCB object from the array
		//    of PCB objects
		pcbArray.add(V1 = new Version1PCB(parentPid));
		V1.setpId(pcbArray.indexOf(V1));
		V1.setFirstPID(pcbArray.indexOf(V1));
		// 2. Insert the newly allocated PCB object into parentPid's
		//    list of children
		pcbArray.get(parentPid).children.add(V1.getpId());

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
		
		
		
		for (int i = 0; i < pcbArray.size(); i++)
		{
			if (pcbArray.get(i).getFirstPID() == targetPid)
			{
				targetPid = pcbArray.get(i).getpId();
				break;
			}
		}
		if ((targetPid >= pcbArray.size() || (pcbArray.get(targetPid).children.isEmpty() && pcbArray.get(targetPid).parent == -1)) && targetPid != 0)
		{
			System.err.println("Process "+ targetPid +" is not in the system\n");
			return -1;
		}
		// If targetPid is not in the process hierarchy, do nothing; 
		// your code may return an error code or message in this case,
		// but it should not halt

		// Assuming you've found the PCB for targetPid in the PCB array:
		// 1. Recursively destroy all descendants of targetPid, if it
		//    has any, and mark their PCBs as "free" in the PCB array 
		//    (i.e., deallocate them)
		if (!pcbArray.get(targetPid).children.isEmpty())
		{
			do {
				destroy(pcbArray.get(targetPid).children.getLast());
			} while (!pcbArray.get(targetPid).children.isEmpty());

		}

		// 2. Remove targetPid from its parent's list of children
		int tmp = pcbArray.get(pcbArray.get(targetPid).parent).children.indexOf(targetPid);
		pcbArray.get(pcbArray.get(targetPid).parent).children.remove(tmp);


		// 3. Deallocate targetPid's PCB and mark its PCB array entry
		//    as "free"
		pcbArray.remove(targetPid);
		
		for (int i = 0; i < targetPid; i++)
		{
			for (int j = 0; j < pcbArray.get(i).children.size(); j++)
			{
				if (pcbArray.get(i).children.get(j) >= targetPid)
				{
					pcbArray.get(i).children.set(j, pcbArray.get(i).children.get(j) - 1);
				}
			}
		}
		for (int i = targetPid; i < pcbArray.size(); i++)
		{
			pcbArray.get(i).setpId(i);
		}

		// You can decide what the return value(s), if any, should be.
		// If you change the return type/value(s), update the Javadoc.
		// often means "success" or "terminated normally"

		return 0;


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
		for (int i = 0; i <= pcbArray.size()-1; i++)
		{
			
			if (!pcbArray.get(i).children.isEmpty())
			{
				System.out.println("Process " + pcbArray.get(i).getpId() + ": Parent is " + pcbArray.get(i).getParent() + " and children are " + pcbArray.get(i).children.toString());
			}
			else
			{
				System.out.println("Process " + pcbArray.get(i).getpId() + ": Parent is " + pcbArray.get(i).getParent() + " and has no children");
			}

		}
		System.out.println("\nend\n");
	}


	/* If you need or want more methods, feel free to add them. */

}