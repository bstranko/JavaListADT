/*
 * Author: Bridgette Stranko
 * CSCI 315 - Data Structures
 * Program 2: ADT List
 */

/**
 * ToDoController class is a generic class
 * that creates an abstract data type list
 * using either an array-based list or a
 * link-based list.
 */
public class ToDoController<E> implements ListADT<E> {

  private ListADT myList;

  /** Constructors */

  /**
   * Create a link-based list
   */
  @SuppressWarnings("unchecked")
  ToDoController() { myList = new LinkedList<E>(); }

  /**
   * Create an array-based list.
   * Ignore Size
   */
  @SuppressWarnings("unchecked") // Generic array allocation
  ToDoController(int size) {
    myList = new ArrayList(size);
  }

  public void clear() {
	 myList.clear();
  }

  /**
   * Insert "it" at current position
   */
  @SuppressWarnings("unchecked")
  public void insert(E it) {
    myList.insert(it);
  }

  /**
   * Append "it" to list
   */
  @SuppressWarnings("unchecked")
  public void append(E it) {
    myList.append(it);
  }

  /**
   * Remove and return the current element
   * @return the element that was removed
   */
  @SuppressWarnings("unchecked")
  public E remove() {
    E item;  // To store the element being removed
    item = (E)myList.remove();  // Remove the element
    return item; // Return the element that was removed.
  }
  /**
   *  Move to the beginning of the list
   */
  public void moveToStart() {
    myList.moveToStart(); // Set at beginning
  }

  /**
   *  Move to the end of the list
   */
  public void moveToEnd() {
    myList.moveToEnd(); // Set at end
  }

  /**
   * Sets the current position back one
   */
  public void prev() {
    myList.prev(); // Set back one
  }

  /**
   * Sets the current position forward one
   */
  public void next() {
    myList.next(); // Set forward one
  }

  /**
   * Retrieves the size of the list
   * @return List size
   */
  public int length() {
    int listSize;
    listSize = myList.length();
    return listSize;
  }

  /**
   * Retrieves the index of the current position
   * @return current position
   */
  public int currPos() {
    int curr;
    curr = myList.currPos();
    return curr;
  }

  /**
   * Set current list position to "pos"
   */
  public void moveToPos(int pos) {
    myList.moveToPos(pos);
  }

  /**
   * @return Current element
   */
  @SuppressWarnings("unchecked")
  public E getValue() {
    E value;
    value = (E)myList.getValue();
    return value;
  }
}



