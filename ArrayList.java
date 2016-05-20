/*
 * Author: Clifford A. Shaffer
 * Modified by: Bridgette Stranko
 * CSCI 315 - Data Structures
 * Program 2: ADT List
 */

/**
 * ArrayList class is a generic class
 * that creates an abstract data type list
 * using an array.
 */
public class ArrayList<E> implements ListADT<E> {

  private static final int defaultSize = 10; // Default size
  private int maxSize; // Maximum size of list
  private int listSize; // Current # of list items
  private int curr; // Position of current element
  private E[] listArray; // Array holding list elements
  private E[] tempArray; // Array used for copying array

  /** Constructors */

  /**
   * Create a list with the default capacity.
   */
  public ArrayList() { this(defaultSize); }

  /** Create a new list object.
   * @param size Max # of elements list can contain.
   */
  @SuppressWarnings("unchecked") // Generic array allocation
  public ArrayList(int size) {
    maxSize = size;
    listSize = 0;
    curr = 0;
    listArray = (E[])new Object[size]; // Create listArray
  }

  public void clear() { // Reinitialize the list
    // Simply reinitialize values
    listSize = 0;
    curr = 0;
    }

  /**
   * Insert "it" at current position
   */
  @SuppressWarnings("unchecked")
  public void insert(E it) {
    if (listSize >= maxSize) {  //check to see if the array is full
     // Creates a temporary array to store elements in listArray
     tempArray = (E[])new Object[listArray.length];

      // Copies the elements from listArray into tempArray
      for (int i=0; i < listArray.length; i++) {
	 	  tempArray[i] = listArray[i];
	   }

      // Creates a new listArray that is twice the size of list array
	   listArray = (E[]) new Object [listArray.length*2];

      // Copies the elements from the temp array into listArray
	   for (int i = 0; i < tempArray.length; i++) {
		  listArray[i] = tempArray[i];
	   }
    }

    // Makes room to add the new element
    for (int i=listSize; i>curr; i--){
      listArray[i] = listArray[i-1];
	 }

    listArray[curr] = it; // put "it" in current element
    listSize++; // Increment list size
  }

  /**
   * Append "it" to list
   */
  @SuppressWarnings("unchecked")
  public void append(E it) {
    if (listSize >= maxSize) { // Checks to see if the array is full
     // Creates a temporary array to store elements in listArray
     tempArray = (E[])new Object[listArray.length];

      for (int i=0; i < listArray.length; i++) {
	 	  tempArray[i] = listArray[i];
	   }

      // Creates a new listArray that is twice the size of list array
	   listArray = (E[]) new Object [listArray.length*2];

      // Copies the elements from the temp array into listArray
	   for (int i = 0; i < tempArray.length; i++) {
		  listArray[i] = tempArray[i];
	   }
    }
    listArray[listSize] = it; // Add "it" to the end of the list
    listSize++; // Increment list size
  }

  /**
   * Remove and return the current element
   * @return the element that was removed
   */
  public E remove() {
    E item; // The item that is being removed
      // Make sure curr is pointing to an element in the array
      if (curr < 0 || curr >= listSize){
        item =  null;
      }

      else {
        item = listArray[curr]; // Store the current element
        // Copy the next element over the existing element
        // starting with the element in the current position
        for (int i = curr; i < listSize-1; i++){
          listArray[i] = listArray[i+1];
        }
        listSize--;  // Decrement listSize
        }

      return item;
    }

  public void moveToStart() {
    curr = 0; // Set to front
  }

  public void moveToEnd() {
    curr = listSize; // Set at end
  }

  public void prev() {
    if (curr != 0) curr--; // Back up
  }

  public void next() {
    if (curr < listSize) curr++; // Move up one
  }

  /**
   * @return List size
   */
  public int length() {
    return listSize;
  }

  /**
   * @return current position
   */
  public int currPos() {
    return curr;
  }

  /**
   * Set current list position to "pos"
   */
  public void moveToPos(int pos) {
    if (pos<0 || pos>listSize) // Make sure "pos" is in range
	   System.out.println("Pos out of range");
	 else
     curr = pos; //change current to "pos"
  }

  /**
   * @return Current element
   */
  public E getValue() {
    if (curr<0 || curr>listSize) { // Make sure curr is pointing to an element
      System.out.println("no current element");
      return null;
	 }
    else
      return listArray[curr]; // return the value in the current element
  }
}