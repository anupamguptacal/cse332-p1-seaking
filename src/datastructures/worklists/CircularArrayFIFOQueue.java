package datastructures.worklists;

import cse332.interfaces.worklists.FixedSizeFIFOWorkList;
import cse332.exceptions.NotYetImplementedException;

/**
 * See cse332/interfaces/worklists/FixedSizeFIFOWorkList.java
 * for method specifications.
 */
public class CircularArrayFIFOQueue<E> extends FixedSizeFIFOWorkList<E> {
	public E[] array;
	private int read;
	private int write;
	private int size;
	
    public CircularArrayFIFOQueue(int capacity) {
        super(capacity);
        this.array = (E[])new Comparable[capacity];
        this.read = 0;
        this.write = 0;
        this.size = 0;
    }

    @Override
    // read refers to the element, write refers to the last space where the last element goes
    public void add(E work) {
    	if(this.size() == this.array.length) {
    		throw new IllegalStateException();
    	}
    	/*if(this.write == this.array.length) {
    		this.write = 0;
    	}*/
    		this.array[(this.write)%this.array.length] = work;
    		this.write++;
    		this.size ++;
    }
    

    @Override
    public E peek() {
    	if(this.size() == 0) {
    		throw new java.util.NoSuchElementException();
    	} else {
    		if(this.read == this.array.length) {
    			this.read = 0;
    		}
    		return this.array[((this.read) % this.array.length)];
    	}
    }
    
    @Override
    public E peek(int i) {
    	if(this.size() == 0) {
    		throw new java.util.NoSuchElementException();
    	} else if ( i < 0 || i > this.size()) {
    		throw new IndexOutOfBoundsException();
    	} else {
    		if(this.read == this.array.length) {
    			this.read = 0;
    		}
    		return this.array[(this.read + i) % this.array.length];
    	}
    }
    
    @Override
    public E next() {
    	if(this.size() == 0) {
    		throw new java.util.NoSuchElementException();
    	}
    	int temp = this.read;
    	this.read++;
    	if(this.read > this.array.length) {
    		this.read = 0;
    	}
    	this.size--;
    	return this.array[temp % this.array.length];
    	
    }
    
    @Override
    public void update(int i, E value) {
    	if(this.size() == 0) {
    		throw new java.util.NoSuchElementException();
    	}
    	if(i < 0 || i > this.size()) {
    		throw new IndexOutOfBoundsException();
    	}	else  {
    		this.array[(this.read + i) % this.array.length] = value;
    	}
    }
    
    @Override
    public int size() {
    	return this.size;
        
    }
    
    @Override
    public void clear() {
    	this.size = 0;
    	this.write = 0;
    	this.read = 0;
    	this.array = (E[])new Comparable[super.capacity()];
    }

    @Override
    public int compareTo(FixedSizeFIFOWorkList<E> other) {
        // You will implement this method in p2. Leave this method unchanged for p1.
        throw new NotYetImplementedException();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        // You will finish implementing this method in p2. Leave this method unchanged for p1.
        if (this == obj) {
            return true;
        }
        else if (!(obj instanceof FixedSizeFIFOWorkList<?>)) {
            return false;
        }
        else {
            FixedSizeFIFOWorkList<E> other = (FixedSizeFIFOWorkList<E>) obj;

            // Your code goes here

            throw new NotYetImplementedException();
        }
    }

    @Override
    public int hashCode() {
        // You will implement this method in p2. Leave this method unchanged for p1.
        throw new NotYetImplementedException();
    }
}
