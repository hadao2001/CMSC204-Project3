import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ListIterator;

/**
 * The implementation of a basic doubly-linked list
 * CMSC 204 with Dr. Alexander
 * October 14, 2020
 * @author Ha T Dao
 * @param <T> data type
 */

public class BasicDoubleLinkedList<T> implements Iterable<T> {
    
    
    //Size of basic doubly-linked list
    protected int size = 0;
    
    //First element of basic doubly-linked list
    protected Node head;
    
    //Last element of basic doubly-linked list
    protected Node tail;
    
    /**
     * No-arg constructor that initializes list
     */
    public BasicDoubleLinkedList() {
        head = tail = null;
    }
    
    /**
	 * Checks if list is empty
	 * 
	 * @return True is list is empty
	 */
	public boolean isEmpty() {
		return size == 0; // head is null
	} 
	
    /**
     * Return size of doubly-linked list
     * @return size of list
     */
    public int getSize() {
        return size;
    }

    /**
	 * Adds an element to the front of the list.
	 * 
	 * @param data The data for the node within the linked list
	 * @return Reference to the current object
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		Node newNode = new Node(data);

		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			newNode.previous = tail;
			tail = newNode;
		}
		size++;

		return this;
	}

	/**
	 * Adds element to the front of the list
	 * 
	 * @param data The data for the node within the linked list
	 * @return Reference to the current object
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) {
		Node newNode = new Node(data);

		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			head.previous = newNode;
			newNode.next = head;
			head = newNode;
		}
		size++;

		return this;
	}
	/**
     * Return data of first element in the doubly-linked list
     * @return data of first element in list
     */
    public T getFirst() {
        return head.data;
    }
    
    /**
     * Return data of last element in the doubly-linked list
     * @return data of last element in list
     */
    public T getLast() {
        return tail.data;

    }

    /**
     * Returns instance of iterator inner class to traverse over the doubly-linked list
     * @return Instance of iterator inner class
     * @throws UnsupportedOperationException method has not been implemented yet
     * @throws NoSuchElementException element does not exist
     */
    @Override
    public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException {
        MyListIterator iterator = new MyListIterator();
        return iterator;
    }
    
    /**
     * Delete first instance of data element in the doubly-linked list using
     * the compare method from the Comparator interface to search and compare
     * between target data and data set in the list
     * @param targetData data to be deleted in list
     * @param comparator interface to compare data of elements
     * @return instance of doubly-linked list
     */
    public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
        Node node = head;
        while(node != null) {
            if (comparator.compare(node.data, targetData) == 0) {
                if (node == head) {
                    if (head.next != null) {
                        head = head.next;
                        head.previous = null;
                    } else {
                        head = tail = null;
                    }
                } else if (node == tail) {
                    if (tail.previous != null) {
                        tail = tail.previous;
                        tail.next = null;
                    } else {
                        head = tail = null;
                    }
                } else {
                    node.previous.next = node.next;
                    node.next.previous = node.previous;
                }
                break;
            }
            node = node.next;
        }
        size--;
        return this;
    }
    
    /**
	 * Remove and returns the first element from the list. If there are no elements
	 * the method reutrns null.
	 * 
	 * @return Data element or null
	 */
	public T retrieveFirstElement() {
		if (isEmpty()) {
			return null;
		}
		T firstData = head.data;
		head = head.next;
		head.previous = null;
		size--;
		return firstData;
	}

    
    /**
	 * Removes and returns the last element from the list. If there are no elements
	 * the method returns null.
	 * 
	 * @return Data element or null
	 */
	public T retrieveLastElement() {
		if (isEmpty()) {
			return null;
		}
		T lastData = tail.data;
		tail = tail.previous;
		tail.next = null;
		size--;
		return lastData;
	}
	
	/**
	 * This is an inner class that constructs nodes to hold
	 * elements in the doubly linked list, including the 
	 * data, the location of the next node, and the location
	 * of the previous node.
	 */
	protected class Node {

		// Create fields for the current, next, and previous elements
		protected T data;
		protected Node next;
		protected Node previous;

		/**
		 * Constructor for a node that doesn't have links
		 * @param element data of the current element
		 */
		Node (T element) {
			this.data = element;
			this.next = null;
			this.previous = null;
		}

		/**
		 * Constructor for a node with or without links
		 * @param previous previous element of the list
		 * @param element data of the current element
		 * @param next next element of the list
		 */
		Node (Node previous, T element, Node next) {
			this.data = element;
			this.next = next;
			this.previous = previous;
		}
	}

	/**
	 * This is an inner class that implements ListIterator for the 
	 * iterator method. It only implements the hasNext, next, hasPrevious
	 * and previous methods of ListIterator and throws an Unsupported Operation
	 * exception fo rall the other methods.
	 *
	 */
	protected class MyListIterator implements ListIterator<T> {
		/**
		 * Instantiate a pointer for the iterator.
		 */
		Node current;													// should these be private or protected?
		int size;

		/**
		 * Place the pointer to before the first element.
		 */
		public MyListIterator() {
			current = new Node(null, null, head);
		}


		@Override
		public boolean hasNext() {					
			return current.next != null;
		}

		@Override
		public boolean hasPrevious() {			
			return current.previous != null;
		}

		@Override
		public T next() {	
			if (!hasNext()) {
				throw new NoSuchElementException("There is no next element.");
			}
			else {
				current.previous = current.next;
				current.next = current.previous.next;
				return current.previous.data;
			}
		}

		@Override
		public T previous() {					

			if (!hasPrevious()) {
				throw new NoSuchElementException("There is no previous element.");

			} else {
				current.next = current.previous;
				current.previous = current.next.previous;
				return current.next.data;
			}
		}

		/**
		 * The following methods are not yet supported for this class. 
		 * Thus, the UnsupportedOperationException is thrown.
		 */
		@Override
		public void add(T argument) throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Unsupported operation");
		}

		@Override
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Unsupported operation");
		}

		@Override
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Unsupported operation");
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Unsupported operation");
		}

		@Override
		public void set(T argument) throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Unsupported operation");

		}

	}

	/**
	 * Returns an arraylist of the items in the list from head of list to tail of
	 * list
	 * 
	 * @return An arraylist of the items in the list
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<T>(getSize());

		Node current = head;

		while (current != null) {
			list.add(current.data);
			current = current.next;
		}

		return list;
	}

	
}

    

		
		
























