package datastructures.dictionaries;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.misc.BString;
import cse332.interfaces.trie.TrieMap;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {
    public class HashTrieNode extends TrieNode<Map<A, HashTrieNode>, HashTrieNode> {
        public HashTrieNode() {
            this(null);
        }

        public HashTrieNode(V value) {
            this.pointers = new HashMap<A, HashTrieNode>();
            this.value = value;
        }

        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            return pointers.entrySet().iterator();
            
        }
    }

    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
    }

    @Override
    public V insert(K key, V value) {
    	if(key == null || value == null) 
    		throw new IllegalArgumentException();
    	HashTrieNode current = (HashTrieNode)this.root;
    	for(A part : key) {
    		if(!current.pointers.containsKey(part)) {
    			HashTrieNode presentNode = new HashTrieNode();
    			current.pointers.put(part, presentNode);
    			
    		 }
    		current = current.pointers.get(part); 		  		
    	}
    	V returnValue = current.value;
    	current.value = value;
    	return returnValue;
    }

    @Override
    public V find(K key) {
    	if(key == null) {
    		throw new IllegalArgumentException();
    	}
    	HashTrieNode current = (HashTrieNode)this.root;
    	for(A part: key) {
    		 if(current == null) {
    			 return null;
    		 }
    		 current = current.pointers.get(part);
    	} 
    	if(current == null) {
    		return null;
    	}
    	return current.value;
    }

    @Override
    public boolean findPrefix(K key) {
    	if(key == null) {
    		throw new IllegalArgumentException();
    	}
    	HashTrieNode current = (HashTrieNode)this.root;
    	for(A part: key) {
    		current = current.pointers.get(part);
    		if(current == null) {
    			return false;
    		} 
  
    	} 
    	return true;	
    }

    @Override
    public void delete(K key) {
    	if(key == null) {
    		throw new IllegalArgumentException();
    	} 
    	HashTrieNode lastDelete = (HashTrieNode)this.root;
    	A lastDeletepart = null;
    	HashTrieNode current = (HashTrieNode)this.root;
    	for( A part: key) {
    		if(current == null) {
    			return;
    		}
    		if(current.value != null || current.pointers.size() > 1) {
    			lastDelete = current;
    			lastDeletepart = part;
    		} 
    		current = current.pointers.get(part);
    	}
    	if(current.value != null) {
    		if (!current.pointers.isEmpty()) {
    			current.value = null;
    		} else {
    			lastDelete.pointers.remove(lastDeletepart);
    		}
    	}
    }

    @Override
    public void clear() {
    	this.root = null;
    }
}
