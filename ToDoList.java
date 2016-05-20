/*
 * Author: Bridgette Stranko
 * CSCI 315 - Data Structures
 * Program 2: ADT List
 */

import java.util.Scanner;	// Needed for Scanner class
import java.io.*; 		// Needed for File I/O class
import java.util.InputMismatchException; //Needed to check user input

/**
 * the ToDoList class creates a ADTList and
 * allows the user to manage the items in the list.
 */
public class ToDoList {

  @SuppressWarnings("unchecked")
  public static void main (String[] args) throws IOException {

    ToDoController list = new ToDoController(); // Creates a new list
    final int ARRAY_SIZE = 5; // The starting size of the array list
    String input; // To hold user input
    char answer;  // To hold converted answer
    int taskNum = 0;  // The number of the task
    int newPos = 0;   // The position to move the element into
    String task;  // To hold the task

    // To get user input
    Scanner keyboard = new Scanner(System.in);

    System.out.println("Welcome to GetItDone!");

    // Get the type of list the user wants to use
    // Keep going until you get a valid answer
    do {
      System.out.println("\nDo you want to run the program with");
      System.out.println("A) an array-based list or");
      System.out.println("L) a linked-based list");
      System.out.print("\n[USER]: ");

      input = keyboard.nextLine();  // Get user input
	   input = input.toUpperCase();  // Change to upper case
      answer = input.charAt(0);  // Get first character

      if (answer == 'A') {
        // Adding a size will initiate an array-based list
        // Default size is small to test insert and append
        // to make sure that it will double and display correctly.
        list = new ToDoController(ARRAY_SIZE);
	   }
      else if (answer == 'L') {
		  // Not passing a parameter will initiate an link-based list
        list = new ToDoController();
	   }
      else
        System.out.println("That is not a valid option. Try again.");
    } while(answer != 'A' && answer!= 'L');

    // Add the first three items to the list
    list.append("Shovel snow.");
    list.append("Write a data structures program.");
    list.append("Shovel snow.");

    System.out.print("\nOkay! ");

    // Display current tasks and modify per user's input
    // Until they chose to quit
    do {
      System.out.println("Your current tasks are:");

      // Start at the beginning of the list
      list.moveToStart();

      // Display all items in the list
      for (int i = 0; i < list.length(); i++) {
		  // Display the item
  	     System.out.println(i+1 + ") " + list.getValue());
	     list.next(); // Move to the next item in the list
	   }

      System.out.println("\nDo you want to: ");
      System.out.println("A) Add an item");
      System.out.println("C) Change the order of the items");
      System.out.println("D) Delete an item");
      System.out.println("Q) Quit");
      System.out.print("\n[USER]: ");

      input = keyboard.nextLine();  // Get user input
	   input = input.toUpperCase();  // Change to upper case
      answer = input.charAt(0);  // Get first character

      // Take the user's modified input to determine what to do next
		switch (answer)
		{
		  case 'A':
		    System.out.println("\nEnter the task you would like to add?");
		    System.out.print("\n[USER]: ");
		    task = keyboard.nextLine();  // User's input will be new task
		    list.append(task); // Add to end of the list
		    System.out.println(""); //  For formatting
		    break;

		  case 'C':

		    System.out.println("\nUsing the number before the item in the to do list, ");
		    System.out.println("enter the item would you like to move:");
		    System.out.print("\n[USER]: ");

          try { //Make sure it is a number
			   taskNum = keyboard.nextInt();  // Get user's input

            // Make sure the position is in range
            if ( taskNum <= 0 || (taskNum) > list.length()) {
			     System.out.println("\nThat was not a valid task number!\n");
			     // Consume the remaining new line character
			     keyboard.nextLine();
			   }
            else {
              // Get the user input for new pos
		        System.out.println("\nWhere would you like to move it to?");
		        System.out.print("\n[USER]: ");
		        newPos = keyboard.nextInt();

		        // Make sure the position is in range
		        if ( taskNum <= 0 || (taskNum) > list.length()) {
		          System.out.println("That was not a valid task number!\n");
			     }

			     else {
				    list.moveToPos(taskNum-1);  // Move to the position of the list item
				    task = (String)list.remove(); // Remove the item and store it in task
				    list.moveToPos(newPos-1);  // Move to the new position
                list.insert(task); // Insert the removed task in the new position

                // Consume the remaining new line character
				    keyboard.nextLine();
				    System.out.println(""); //  For formatting
			     }
			   }
		    }

			 catch (InputMismatchException e) {
				// send error and exit
			   System.out.println("\nThat was not a valid task number.\n");

			   // Consume the remaining new line character
			   keyboard.nextLine();
			   break;
			 }

			 break;


		  case 'D':
		    System.out.println("\nUsing the number before the item in the to do list, ");
		    System.out.println("enter the item would you like to delete:");
          System.out.print("\n[USER]: ");

          // Make sure the position is in range
		    try {
				// Get user input
		      taskNum = keyboard.nextInt();

            if ( taskNum <= 0 || (taskNum) > list.length()) {
			     System.out.println("\nThat was not a valid task number!\n");
			   }
            else {
		        list.moveToPos(taskNum - 1);	// Move to the position of the item to be removed
		        task = (String)list.remove(); // Remove the item and store in task

		        System.out.println("\nYou removed: " + task); // Display item removed
			   }
			 }
			 catch (InputMismatchException e) {
				// If it is not in range, display an error message and
				// start back at top.  Don't change anything.
			 	System.out.println("\nThat was not a valid task number!\n");

			 	// Consume the remaining new line character
				keyboard.nextLine();
				System.out.println(""); //  For formatting
				break;
			 }

			 // Consume the remaining new line character
			 keyboard.nextLine();
		    break;

        case 'Q':
          // Don't do anything
          break;

		  default:
		    // Display an error message if it was not a valid choice
		    System.out.println("\nThat is not a valid option. Try again.\n"); }

	 } while(answer != 'Q');

	 //Open new to do list file.
	 PrintWriter outputFile = new PrintWriter("myToDoList.txt");

      list.moveToStart(); // Start at the beginning of the list

	   //Get data and write it to the file.
      for (int i = 0; i < list.length(); i++) {
  	     outputFile.println(i+1 + ") " + list.getValue());
	     list.next();
	   }
 		//Close the file.
 		outputFile.close();
		System.out.println("Your to do list has been written to the text file: myToDoList");
  }
}

/*-------------------ARRAY LIST TEST RUN------------------------------------------------------

		Welcome to GetItDone!

		Do you want to run the program with
		A) an array-based list or
		L) a linked-based list

		[USER]: 1
		That is not a valid option. Try again.

		Do you want to run the program with
		A) an array-based list or
		L) a linked-based list

		[USER]: b
		That is not a valid option. Try again.

		Do you want to run the program with
		A) an array-based list or
		L) a linked-based list

		[USER]: z
		That is not a valid option. Try again.

		Do you want to run the program with
		A) an array-based list or
		L) a linked-based list

		[USER]: a

		Okay! Your current tasks are:
		1) Shovel snow.
		2) Write a data structures program.
		3) Shovel snow.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: 1

		That is not a valid option. Try again.

		Your current tasks are:
		1) Shovel snow.
		2) Write a data structures program.
		3) Shovel snow.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: z

		That is not a valid option. Try again.

		Your current tasks are:
		1) Shovel snow.
		2) Write a data structures program.
		3) Shovel snow.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: a

		Enter the task you would like to add?

		[USER]: Test data structures program.

		Your current tasks are:
		1) Shovel snow.
		2) Write a data structures program.
		3) Shovel snow.
		4) Test data structures program.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: c

		Using the number before the item in the to do list,
		enter the item would you like to move:

		[USER]: -1

		That was not a valid task number!

		Your current tasks are:
		1) Shovel snow.
		2) Write a data structures program.
		3) Shovel snow.
		4) Test data structures program.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: c

		Using the number before the item in the to do list,
		enter the item would you like to move:

		[USER]: 5

		That was not a valid task number!

		Your current tasks are:
		1) Shovel snow.
		2) Write a data structures program.
		3) Shovel snow.
		4) Test data structures program.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: c

		Using the number before the item in the to do list,
		enter the item would you like to move:

		[USER]: 4

		Where would you like to move it to?

		[USER]: 3

		Your current tasks are:
		1) Shovel snow.
		2) Write a data structures program.
		3) Test data structures program.
		4) Shovel snow.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: d

		Using the number before the item in the to do list,
		enter the item would you like to delete:

		[USER]: -1

		That was not a valid task number!

		Your current tasks are:
		1) Shovel snow.
		2) Write a data structures program.
		3) Test data structures program.
		4) Shovel snow.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: d

		Using the number before the item in the to do list,
		enter the item would you like to delete:

		[USER]: 5

		That was not a valid task number!

		Your current tasks are:
		1) Shovel snow.
		2) Write a data structures program.
		3) Test data structures program.
		4) Shovel snow.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: d

		Using the number before the item in the to do list,
		enter the item would you like to delete:

		[USER]: 1

		You removed: Shovel snow.
		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Shovel snow.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: d

		Using the number before the item in the to do list,
		enter the item would you like to delete:

		[USER]: 4

		That was not a valid task number!

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Shovel snow.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: 1

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: a

		Enter the task you would like to add?

		[USER]: Make dinner.

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Make dinner.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: a

		Enter the task you would like to add?

		[USER]: Eat.

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Make dinner.
		4) Eat.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: a

		Enter the task you would like to add?

		[USER]: Do the dishes.

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Make dinner.
		4) Eat.
		5) Do the dishes.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: a

		Enter the task you would like to add?

		[USER]: Clean the kitchen

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Make dinner.
		4) Eat.
		5) Do the dishes.
		6) Clean the kitchen

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: a

		Enter the task you would like to add?

		[USER]: Change into pajamas

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Make dinner.
		4) Eat.
		5) Do the dishes.
		6) Clean the kitchen
		7) Change into pajamas

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: d

		Using the number before the item in the to do list,
		enter the item would you like to delete:

		[USER]: a

		That was not a valid task number!


		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Make dinner.
		4) Eat.
		5) Do the dishes.
		6) Clean the kitchen
		7) Change into pajamas

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: a

		Enter the task you would like to add?

		[USER]: Read a book

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Make dinner.
		4) Eat.
		5) Do the dishes.
		6) Clean the kitchen
		7) Change into pajamas
		8) Read a book

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: a

		Enter the task you would like to add?

		[USER]: Kiss husband goodnight.

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Make dinner.
		4) Eat.
		5) Do the dishes.
		6) Clean the kitchen
		7) Change into pajamas
		8) Read a book
		9) Kiss husband goodnight.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: d

		Using the number before the item in the to do list,
		enter the item would you like to delete:

		[USER]: 9

		You removed: Kiss husband goodnight.
		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Make dinner.
		4) Eat.
		5) Do the dishes.
		6) Clean the kitchen
		7) Change into pajamas
		8) Read a book

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: a

		Enter the task you would like to add?

		[USER]: Pick on husband

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Make dinner.
		4) Eat.
		5) Do the dishes.
		6) Clean the kitchen
		7) Change into pajamas
		8) Read a book
		9) Pick on husband

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: a

		Enter the task you would like to add?

		[USER]: Go to bed.

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Make dinner.
		4) Eat.
		5) Do the dishes.
		6) Clean the kitchen
		7) Change into pajamas
		8) Read a book
		9) Pick on husband
		10) Go to bed.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: a

		Enter the task you would like to add?

		[USER]: Fall asleep.

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Make dinner.
		4) Eat.
		5) Do the dishes.
		6) Clean the kitchen
		7) Change into pajamas
		8) Read a book
		9) Pick on husband
		10) Go to bed.
		11) Fall asleep.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: 1

		That is not a valid option. Try again.

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Make dinner.
		4) Eat.
		5) Do the dishes.
		6) Clean the kitchen
		7) Change into pajamas
		8) Read a book
		9) Pick on husband
		10) Go to bed.
		11) Fall asleep.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: q
		Your to do list has been written to the text file: myToDoList
		Press any key to continue . . .
		*/

/*------------------- LINKED LIST TEST RUN------------------------------------------------------

		Welcome to GetItDone!

		Do you want to run the program with
		A) an array-based list or
		L) a linked-based list

		[USER]: V
		That is not a valid option. Try again.

		Do you want to run the program with
		A) an array-based list or
		L) a linked-based list

		[USER]: L

		Okay! Your current tasks are:
		1) Shovel snow.
		2) Write a data structures program.
		3) Shovel snow.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: z

		That is not a valid option. Try again.

		Your current tasks are:
		1) Shovel snow.
		2) Write a data structures program.
		3) Shovel snow.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: 1

		That is not a valid option. Try again.

		Your current tasks are:
		1) Shovel snow.
		2) Write a data structures program.
		3) Shovel snow.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: a

		Enter the task you would like to add?

		[USER]: Test data structures program.

		Your current tasks are:
		1) Shovel snow.
		2) Write a data structures program.
		3) Shovel snow.
		4) Test data structures program.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: c

		Using the number before the item in the to do list,
		enter the item would you like to move:

		[USER]: -1

		That was not a valid task number!

		Your current tasks are:
		1) Shovel snow.
		2) Write a data structures program.
		3) Shovel snow.
		4) Test data structures program.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: 5

		That is not a valid option. Try again.

		Your current tasks are:
		1) Shovel snow.
		2) Write a data structures program.
		3) Shovel snow.
		4) Test data structures program.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: c

		Using the number before the item in the to do list,
		enter the item would you like to move:

		[USER]: 4

		Where would you like to move it to?

		[USER]: 1

		Your current tasks are:
		1) Test data structures program.
		2) Shovel snow.
		3) Write a data structures program.
		4) Shovel snow.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: c

		Using the number before the item in the to do list,
		enter the item would you like to move:

		[USER]: 2

		Where would you like to move it to?

		[USER]: 4
		Position out of range

		Your current tasks are:
		1) Test data structures program.
		2) Shovel snow.
		3) Write a data structures program.
		4) Shovel snow.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: c

		Using the number before the item in the to do list,
		enter the item would you like to move:

		[USER]: 2

		Where would you like to move it to?

		[USER]: 3

		Your current tasks are:
		1) Test data structures program.
		2) Write a data structures program.
		3) Shovel snow.
		4) Shovel snow.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: c

		Using the number before the item in the to do list,
		enter the item would you like to move:

		[USER]: 2

		Where would you like to move it to?

		[USER]: 1

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Shovel snow.
		4) Shovel snow.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: d

		Using the number before the item in the to do list,
		enter the item would you like to delete:

		[USER]: -1

		That was not a valid task number!

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Shovel snow.
		4) Shovel snow.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: d

		Using the number before the item in the to do list,
		enter the item would you like to delete:

		[USER]: 5

		That was not a valid task number!

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Shovel snow.
		4) Shovel snow.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: d

		Using the number before the item in the to do list,
		enter the item would you like to delete:

		[USER]: 3

		You removed: Shovel snow.
		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Shovel snow.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: d

		Using the number before the item in the to do list,
		enter the item would you like to delete:

		[USER]: 4

		That was not a valid task number!

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Shovel snow.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: d

		Using the number before the item in the to do list,
		enter the item would you like to delete:

		[USER]: 3

		You removed: Shovel snow.
		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: a

		Enter the task you would like to add?

		[USER]: Make dinner.

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Make dinner.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: a

		Enter the task you would like to add?

		[USER]: Eat dinner.

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Make dinner.
		4) Eat dinner.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: a

		Enter the task you would like to add?

		[USER]: Do dishes.

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Make dinner.
		4) Eat dinner.
		5) Do dishes.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: a

		Enter the task you would like to add?

		[USER]: Clean Kitchen

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Make dinner.
		4) Eat dinner.
		5) Do dishes.
		6) Clean Kitchen

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: a

		Enter the task you would like to add?

		[USER]: Clean house.

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Make dinner.
		4) Eat dinner.
		5) Do dishes.
		6) Clean Kitchen
		7) Clean house.

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: a

		Enter the task you would like to add?

		[USER]: Just go to bed already!

		Your current tasks are:
		1) Write a data structures program.
		2) Test data structures program.
		3) Make dinner.
		4) Eat dinner.
		5) Do dishes.
		6) Clean Kitchen
		7) Clean house.
		8) Just go to bed already!

		Do you want to:
		A) Add an item
		C) Change the order of the items
		D) Delete an item
		Q) Quit

		[USER]: q
		Your to do list has been written to the text file: myToDoList
		Press any key to continue . . .

*/