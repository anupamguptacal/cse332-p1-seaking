package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.FIFOWorkList;
import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/FIFOWorkList.java
 * for method specifications.
 * @param <E>
 */

// Node class for list elements
class ListNode<E> {
	E data;
	ListNode<E> next;
	
	ListNode() {
		this(null, null);
	}
	
	ListNode(E data) {
		this(data, null);
	}
	
	ListNode(E data, ListNode<E> next) {
		this.data = data;
		this.next = next;
	}
}

// stores elements in a FIFO list
public class ListFIFOQueue<E> extends FIFOWorkList<E> {
	private int size;
	private ListNode<E> front;
	private ListNode<E> back;
    
    public ListFIFOQueue() {
        this.size = 0;
        this.front = null;
        this.back = null;
    }

    @Override
    // adds element to the list
    public void add(E work) {
    	if (this.hasWork()) {
    		this.back.next = new ListNode(work);
        	this.back = this.back.next;
    	} else { // list is empty, this will be first node
    		this.front = new ListNode(work);
    		this.back = this.front;
    	}
    	this.size++;
    }

    @Override
    // returns next element of the list without removing it
    public E peek() {
        if (!this.hasWork()) {
        	throw new NoSuchElementException();
        }
        
        return this.front.data;
    }

    @Override
    // removes and returns next element of the list
    public E next() {
    	if (!this.hasWork()) {
        	throw new NoSuchElementException();
        }
    	
        E value = this.front.data;
        
        // for the case of a list with one element
        if (this.front == this.back) {
        	this.back = null;
        }
        
        this.front = this.front.next;
        this.size--;
        return value;
    }

    @Override
    // returns the number of elements in the list
    public int size() {
        return this.size;
    }

    @Override
    // resets list to beginning state (empty)
    public void clear() {
    	this.size = 0;
        this.front = null;
        this.back = null;
    }
}
