package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.LIFOWorkList;

/**
 * See cse332/interfaces/worklists/LIFOWorkList.java
 * for method specifications.
 */
public class ArrayStack<E> extends LIFOWorkList<E> {
	public E[] array;
	int top;
    public ArrayStack() {
    	array = (E[])new Object[10];
    	top = -1;
    }

    @Override
    public void add(E work) {
    	if(top + 1 == array.length) {
    		E[] arrayCopy = (E[])new Object[array.length * 2];
    		for(int i = 0; i < array.length; i ++) {
    			arrayCopy[i] = array[i];
    		}
    		array = arrayCopy;
    	}
    	array[++top] = work;
    }

    @Override
    public E peek() {
    	if(top == -1) {
    		throw new java.util.NoSuchElementException();
    	} else {
    		return array[top];
    	}
    }

    @Override
    public E next() {
    	if(top == -1) {
    		throw new java.util.NoSuchElementException();
    	} else {
    		top--;
    		return array[top + 1];
    	}
    }

    @Override
    public int size() {
    	return top + 1;
    }

    @Override
    public void clear() {
        top =  -1;
        array = (E[])new Object[10];
    }
}
