import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * The implementation of a sorted doubly-linked list
 * @author Ha T Dao
 * @param <T> data type
 */

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

	/** 
	 * Comparator interface sorts the doubly linked list
	 */
	protected Comparator<T> comparator = null;

	/**
	 * Constructor to set type for the Double Linked List sorter
	 * @param comparator tool that sorts the list
	 */
	public SortedDoubleLinkedList(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
	public <head> SortedDoubleLinkedList<T> add(T data) {
        Node newNode = new Node(data);
        
        if (size == 0) {
            head = tail = newNode;
        } else if (comparator.compare(head.data, data) > 0) {
            head.previous = newNode;
            newNode.next = head;
            head = newNode;
        } else if (comparator.compare(tail.data, data) < 0) {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        } else {
            Node search = head;
            while (search != null) {
                if (comparator.compare(search.data, data) <= 0) {
                    Node before = search;
                    Node after = search.next;
                    after.previous = before.next = newNode;
                    newNode.next = after;
                    newNode.previous = before;   
                }
                search = search.next;
            }
        }
        
        size++;
        return this;
    }
	
	/**
	 * no needed to implement, throws unsupportedOperationException
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	/**
	 * no needed to implement, throws unsupportedOperationException
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * Implements the iterator by calling the super class iterator method.
	 */
		@Override
		public ListIterator<T> iterator() {
			return super.iterator();
		}
	/**
	 * Implements the remove operation by calling the super class remove method.
	 */
		@Override
		public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator) {
			return (SortedDoubleLinkedList<T>) super.remove(data, comparator);
		}
}


	
	