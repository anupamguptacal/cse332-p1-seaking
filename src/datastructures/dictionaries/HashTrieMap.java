package datastructures.dictionaries;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import cse332.datastructures.containers.Item;
import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.misc.BString;
import cse332.interfaces.trie.TrieMap;
import cse332.types.AlphabeticString;

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
       /* if (key == null || value == null) {
        	throw new IllegalArgumentException();
        }
        HashTrieNode oldValue = null;
        HashTrieNode current = (HashTrieNode)this.root;
        for (A letter : key) {
        	current = current.pointers.get(letter);
        }
        if (current != null) {
        	oldValue = current;
        } */
    	throw new NotYetImplementedException();
    }

    @Override
    public V find(K key) {
        if (key == null) {
        	throw new IllegalArgumentException();
        }
        Iterator<A> keyIterator = key.iterator();
        HashTrieNode current = (HashTrieNode)this.root;
        while (keyIterator.hasNext()) {
        	current = current.pointers.get(keyIterator.next());
        	if (current == null) {
        		return null;
        	}
        }
        return current.value;
    }

    @Override
    public boolean findPrefix(K key) {
        if (key == null) {
        	throw new IllegalArgumentException();
        }
        Iterator<A> keyIterator = key.iterator();
        HashTrieNode current = (HashTrieNode)this.root;
        while (keyIterator.hasNext()) {
        	current = current.pointers.get(keyIterator.next());
        	if (current == null) {
        		return false;
        	}
        }
        return true;
    }

    @Override
    public void delete(K key) {
        throw new NotYetImplementedException();
    }

    @Override
    public void clear() {
        throw new NotYetImplementedException();
    }
}
