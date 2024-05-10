/* COMPSCI 424 Program 1
 * Name:
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */
package compsci424.p1.java;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Main class for this program. The required steps have been copied
 * into the main method as comments. Feel free to add more comments to
 * help you understand your code, or for any other reason. Also feel
 * free to edit this comment to be more helpful for you.
 */
public class Program1 
{
	// Declare any class/instance variables that you need here.

	/**
	 * @param args command-line arguments, which can be ignored
	 */
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);

		// 1. Ask the user to enter commands of the form "create N",
		//    "destroy N", or "end", where N is an integer between 0 
		//    and 15.

		System.out.println("Please enter a command from the list "
				+ "\n   create N"
				+ "\n   destroy N"
				+ "\n   end (to end)"
				+ "\n");
		// 2. While the user has not typed "end", continue accepting
		//    commands. Add each command to a list of actions to take 
		//    while you run the simulation.
		String reply = "";

		ArrayList<String> cmds = new ArrayList<>();

		while (!reply.equalsIgnoreCase("end"))
		{
			cmds.add(reply = sc.next());
		}
		cmds.remove(cmds.size() - 1);
		// 4. Create an object of the Version 1 class and an object of
		//    the Version 2 class.
		Version1 V1 = new Version1();
		Version2 V2 = new Version2();
		// 5. Run the command sequence once with the Version 1 object, 
		//    calling its showProcessTree method after each command to
		//    show the changes in the tree after each command.

		for (int i = 0; i < cmds.size(); i++)
		{
			if (cmds.get(i).contains("create"))
			{
				int temp = Integer.parseInt(cmds.get(i + 1));
				V1.create(temp);
				V1.showProcessInfo();
				i++;
			}

			if (cmds.get(i).contains("destroy"))
			{
				int temp = Integer.parseInt(cmds.get(i + 1));
				V1.destroy(temp);
				V1.showProcessInfo();
				i++;
			}
		}

		// 6. Repeat step 5, but with the Version 2 object.

		for (int i = 0; i<= cmds.size()-1; i++)
		{
			if (cmds.get(i).contains("create"))
			{
				int temp = Integer.parseInt(cmds.get(i + 1));
				V2.create(temp);
				V2.showProcessInfo(); //Didnt know wether to keep removed processes in the 
				i++;
			}

			if (cmds.get(i).contains("destroy"))
			{
				int temp = Integer.parseInt(cmds.get(i + 1));
				V2.destroy(temp);
				V2.showProcessInfo();
				i++;
			}
		}


		// 7. Store the current system time in a variable

		// ... then run the command sequence 200 times with Version 1.

		// ... After this, store the new current system time in a
		//     second variable. Subtract the start time from the end 
		//     time to get the Version 1 running time, then display 
		//     the Version 1 running time.
		long startTime = System.currentTimeMillis();
		for (int j = 0; j < 200; j++) {
			for (int i = 0; i < cmds.size(); i++)
			{
				if (cmds.get(i).contains("create"))
				{
					int temp = Integer.parseInt(cmds.get(i + 1));
					V1.create(temp);
					i++;
				}

				if (cmds.get(i).contains("destroy"))
				{
					int temp = Integer.parseInt(cmds.get(i + 1));
					V1.destroy(temp);
					i++;
				}
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time for version 1 to complete 200 times: " + (endTime - startTime) + "ms");
		// 8. Repeat step 7, but with the Version 2 object.
		startTime = System.currentTimeMillis();
		for (int j = 0; j < 200; j++) {
			for (int i = 0; i<= cmds.size()-1; i++)
			{
				if (cmds.get(i).contains("create"))
				{
					int temp = Integer.parseInt(cmds.get(i + 1));
					V2.create(temp);

					i++;
				}

				if (cmds.get(i).contains("destroy"))
				{
					int temp = Integer.parseInt(cmds.get(i + 1));
					V2.destroy(temp);
					i++;
				}
			}
		}
		endTime = System.currentTimeMillis();
		System.out.println("Time for version 2 to complete 200 times: " + (endTime - startTime) + "ms\n");
		// This line is here just to test the Gradle build procedure.
		// You can delete it.
		System.out.println("Builds without errors and runs to completion.");
	}
}