package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.FixedSizeFIFOWorkList;

/**
 * See cse332/interfaces/worklists/FixedSizeFIFOWorkList.java
 * for method specifications.
 */
public class CircularArrayFIFOQueue<E> extends FixedSizeFIFOWorkList<E> {
	public E[] array;
	int read;
	int write;
	int size;
    public CircularArrayFIFOQueue(int capacity) {
        super(capacity);
        array = (E[])new Comparable[capacity];
        read = 0;
        write = 0;
        size = 0;
    }

    @Override
    // read refers to the element, write refers to the last space where the last element goes
    public void add(E work) {
    	if(this.size() == array.length) {
    		throw new IllegalStateException();
    	}
    	if(write == array.length) {
    		write = 0;
    	}
    		array[write++] = work;
    		size ++;
    	}
    

    @Override
    public E peek() {
    	if(this.size() == 0) {
    		throw new java.util.NoSuchElementException();
    	} else {
    		if(read == array.length)
    			read = 0;
    		return array[read];
    	}
    }
    
    @Override
    public E peek(int i) {
    	if(this.size() == 0)
    		throw new java.util.NoSuchElementException();
    	else if ( i < 0 || i > this.size())
    		throw new IndexOutOfBoundsException();
    	else {
    		if(read == array.length)
    			read = 0;
    		return array[(read + i) % array.length];
    	}
    }
    
    @Override
    public E next() {
    	if(size == 0)
    		throw new java.util.NoSuchElementException();
    	int temp = read;
    	read++;
    	if(read > array.length) {
    		read = 0;
    	}
    	size--;
    	return array[temp];
    	
    }
    
    @Override
    public void update(int i, E value) {
    	if(size == 0)
    		throw new java.util.NoSuchElementException();
    	if(i < 0 || i > size)
    		throw new IndexOutOfBoundsException();
    	else 
    		array[(read + i) % array.length] = value;
    }
    
    @Override
    public int size() {
    	return size;
        
    }
    
    @Override
    public void clear() {
    	size = 0;
    	write = 0;
    	read = 0;
    	array = null;
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
