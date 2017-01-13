package tests.gitlab.duedate;
import java.io.*;
import cse332.types.AlphabeticString;
import datastructures.dictionaries.HashTrieMap;
import tests.TestsUtility;

import java.util.HashMap;
import java.util.Map;

public class HashTrieMapTests extends TestsUtility {
    protected static HashTrieMap<Character, AlphabeticString, String> STUDENT;
    static PrintWriter printer; 
    public static void main(String[] args) {
    printer = new PrintWriter("HashTrieNotworking.txt","UTF-8");
        new HashTrieMapTests().run();
    }

    public static void init() {
        STUDENT = new HashTrieMap<>(AlphabeticString.class);
    }

    @Override
    protected void run() {
        SHOW_TESTS = true;
        PRINT_TESTERR = true;
        DEBUG = true;

        test("testBasic");
        test("testBasicDelete");

        test("testFindPrefixes");
        test("testFindNonexistentDoesNotCrash");
        test("testFindingNullEntriesCausesError");

        test("testInsertReplacesOldValue");
        test("testInsertingNullEntriesCausesError");

        test("testDeleteAll");
        test("testDeleteNothing");
        test("testDeleteAndInsertSingleChars");
        test("testDeleteWorksWhenTrieHasNoBranches");
        test("testDeletingAtRoot");
        test("testDeletingEmptyString");
        test("testDeletingNullEntriesCausesError");

        test("testClear");
        test("checkUnderlyingStructure");
        test("stressTest");

        finish();
    }

    /**
     * Tests if insert, find, and findPrefix work in general.
     */
    public static int testBasic() {
        String[] words = {"dog", "doggy", "doge", "dragon", "cat", "draggin"};
        String[] invalid = {"d", "cataract", "", "do"};
        addAll(STUDENT, words);
        return (containsAllPaths(STUDENT, words) && doesNotContainAll(STUDENT, invalid)) ? 1 : 0;
    }

    /**
     * Checks to see if basic delete functionality works.
     */
    public static int testBasicDelete() {
        String[] words = {"dog", "doggy", "dreamer", "cat"};
        addAll(STUDENT, words);
        if (!containsAllPaths(STUDENT, words)) {
            return 0;
        }

        STUDENT.delete(a("I don't exist"));
        STUDENT.delete(a("dreamer"));

        if (!containsAllPaths(STUDENT, "dog", "doggy", "cat") &&
                !containsAllPrefixes(STUDENT, "dreamer", "dreame", "dream", "drea", "dre", "dr") &&
                !STUDENT.findPrefix(a("d"))) {
            return 0;
        }

        STUDENT.delete(a("dog"));
        if (!containsAllPaths(STUDENT, "doggy", "cat")) {
            return 0;
        }

        STUDENT.delete(a("doggy"));
        return containsAllPaths(STUDENT, "cat") ? 1 : 0;
    }

    /**
     * Test findPrefix more rigorously.
     */
    public static int testFindPrefixes() {
        String[] words = {"dog", "doggy", "doge", "dragon", "cat", "draggin"};
        addAll(STUDENT, words);

        boolean allPrefixesFound = containsAllPrefixes(STUDENT, "d", "", "do");
        boolean invalidPrefixesIgnored = doesNotContainAllPrefixes(STUDENT, "batarang", "dogee", "dragging");

        return allPrefixesFound && invalidPrefixesIgnored ? 1 : 0;
    }

    /**
     * Tests that trying to find a non-existent entity does the correct thing
     */
    public static int testFindNonexistentDoesNotCrash() {
        addAll(STUDENT, "foo", "bar", "baz");
        return STUDENT.find(a("orangutan")) == null && STUDENT.find(a("z")) == null &&
                STUDENT.find(a("ba")) == null && STUDENT.find(a("bazz")) == null &&
                !STUDENT.findPrefix(a("boor")) && !STUDENT.findPrefix(a("z")) ? 1 : 0;
    }

    public static int testFindingNullEntriesCausesError() {
        try {
            STUDENT.find(null);
            return 0;
        } catch (IllegalArgumentException ex) {
            // Do nothing
        }
        try {
            STUDENT.findPrefix(null);
            return 0;
        } catch (IllegalArgumentException ex) {
            // Do nothing
        }
        return 1;
    }

    /**
     * Tests that inserts correctly wipe out old values.
     */
    public static int testInsertReplacesOldValue() {
        AlphabeticString key = a("myKey");
        boolean initialValueIsNull = STUDENT.insert(key, "foo") == null;
        boolean originalValueReturned = STUDENT.insert(key, "bar").equals("foo");
        boolean replacementValueReturned = STUDENT.insert(key, "baz").equals("bar");

        return initialValueIsNull && originalValueReturned && replacementValueReturned ? 1 : 0;
    }

    public static int testInsertingNullEntriesCausesError() {
        try {
            STUDENT.insert(null, "foo");
            return 0;
        } catch (IllegalArgumentException ex) {
            // Do nothing
        }

        try {
            STUDENT.insert(a("foo"), null);
            return 0;
        } catch (IllegalArgumentException ex) {
            // Do nothing
        }

        return 1;
    }

    /**
     * Checks to see the trie correctly handles the case where you delete
     * absolutely everything.
     */
    public static int testDeleteAll() {
        AlphabeticString keyA = a("keyboard");
        AlphabeticString keyB = a("keyesian");
        AlphabeticString keyC = a("bayesian");
        
        
        if (STUDENT.size() != 0 || !STUDENT.isEmpty()) {
            return 0;
        }
        
        printer.println("In testDeleteAll and inserting");
        STUDENT.insert(keyA, "KEYBOARD");
        
        STUDENT.insert(keyB, "KEYESIAN");
        STUDENT.insert(keyC, "BAYESIAN");

        if (!containsAllPaths(STUDENT, "keyboard", "keyesian", "bayesian")) {
            return 0;
        }
        if (STUDENT.size() != 3 || STUDENT.isEmpty()) {
            return 0;
        }

        STUDENT.delete(keyA);
        STUDENT.delete(keyB);
        STUDENT.delete(keyC);

        if (STUDENT.size() != 0 || !STUDENT.isEmpty()) {
            return 0;
        }

        return doesNotContainAll(STUDENT, "keyboard", "keyesian", "bayesian") ? 1 : 0;
    }

    /**
     * Tests what happens if you attempt deleting something that doesn't exist
     * in the trie (but _does_ partially overlap).
     */
    public static int testDeleteNothing() {
        STUDENT.insert(a("aaaa"), "foo");

        if (!containsPath(STUDENT, "aaaa", "foo") || STUDENT.size() != 1 || STUDENT.isEmpty()) {
            return 0;
        }

        // Should not change the trie
        STUDENT.delete(a("aa"));
        STUDENT.delete(a("a"));
        STUDENT.delete(a("abc"));
        STUDENT.delete(a("aaaaa"));
        STUDENT.delete(a(""));
        STUDENT.delete(a("foobar"));

        if (!containsPath(STUDENT, "aaaa", "foo") || STUDENT.size() != 1 || STUDENT.isEmpty()) {
            return 0;
        }

        return 1;
    }

    /**
     * Tests what happens if you try deleting and inserting single characters
     */
    public static int testDeleteAndInsertSingleChars() {
        STUDENT.insert(a("a"), "A");
        STUDENT.insert(a("b"), "B");

        STUDENT.delete(a("a"));
        STUDENT.insert(a("b"), "BB");

        MockNode expected = node()
                .branch('b', node("BB"));

        return equals(expected, getField(STUDENT, "root")) ? 1 : 0;
    }

    /**
     * Tests to see if HashTrieMap correctly handles a trie where everything is in a straight
     * line/the trie has no branching.
     */
    public static int testDeleteWorksWhenTrieHasNoBranches() {
        AlphabeticString keyA = a("ghost");
        AlphabeticString keyB = a("gh");
        STUDENT.insert(keyA, "A");
        STUDENT.insert(keyB, "B");

        // Trie should still contain "ghost -> A"
        STUDENT.delete(keyB);
        if (STUDENT.find(keyB) != null || !STUDENT.findPrefix(keyB) || !STUDENT.find(keyA).equals("A")) {
            return 0;
        }

        // Trie should now contain "gh -> C", but not "ghost -> A"
        STUDENT.insert(keyB, "C");
        STUDENT.delete(keyA);

        return (STUDENT.find(keyB).equals("C") && STUDENT.find(keyA) == null && !STUDENT.findPrefix(a("gho"))) ? 1 : 0;
    }

    /**
     * A slight variation of the previous test.
     */
    public static int testDeletingAtRoot() {
        STUDENT.insert(a(""), "foo");
        STUDENT.insert(a("a"), "bar");
        STUDENT.delete(a("a"));
        STUDENT.insert(a("b"), "baz");
        if (STUDENT.find(a("a")) != null) {
            return 0;
        }
        if (!"foo".equals(STUDENT.find(a("")))) {
            return 0;
        }
        if (!"baz".equals(STUDENT.find(a("b")))) {
            return 0;
        }
        MockNode expected = new MockNode("foo")
                .branch('b', new MockNode("baz"));
        return equals(expected, getField(STUDENT, "root")) ? 1 : 0;
    }

    /**
     * Tests that just working with empty strings does the correct thing.
     */
    public static int testDeletingEmptyString() {
        STUDENT.insert(a(""), "Foo");
        if (!"Foo".equals(STUDENT.find(a(""))) || STUDENT.size() != 1 || STUDENT.isEmpty()) {
            return 0;
        }

        STUDENT.delete(a(""));

        if (STUDENT.find(a("")) != null || STUDENT.size() > 0 && !STUDENT.isEmpty()) {
            return 0;
        }

        STUDENT.insert(a(""), "Bar");
        return "Bar".equals(STUDENT.find(a(""))) && STUDENT.size() == 1 && !STUDENT.isEmpty() ? 1 : 0;
    }

    public static int testDeletingNullEntriesCausesError() {
        try {
            STUDENT.delete((AlphabeticString) null);
        } catch (IllegalArgumentException ex) {
            return 1;
        }
        return 0;
    }

    public static int testClear() {
        addAll(STUDENT, "keyboard", "keyesian", "bayesian");
        STUDENT.clear();

        return (STUDENT.size() == 0 &&
                STUDENT.isEmpty() &&
                doesNotContainAll(STUDENT, "keyboard", "keyesian", "bayesian")) ? 1 : 0;
    }

    public static int checkUnderlyingStructure() {
        STUDENT.insert(a(""), "A");
        STUDENT.insert(a("foo"), "B");
        STUDENT.insert(a("fez"), "C");
        STUDENT.insert(a("fezzy"), "D");
        STUDENT.insert(a("jazz"), "E");
        STUDENT.insert(a("jazzy"), "F");

        MockNode fullExpected = node("A")
            .branch('f', node()
                .branch('o', node()
                    .branch('o', node("B")))
                .branch('e', node()
                    .branch('z', node("C")
                        .branch('z', node()
                            .branch('y', node("D"))))))
            .branch('j', node()
                .branch('a', node()
                    .branch('z', node()
                        .branch('z', node("E")
                            .branch('y', node("F"))))));

        if (!equals(fullExpected, getField(STUDENT, "root"))) {
            return 0;
        }

        STUDENT.delete(a("fezzy"));
        STUDENT.delete(a("jazz"));

        MockNode delete1 = node("A")
            .branch('f', node()
                .branch('o', node()
                    .branch('o', node("B")))
                .branch('e', node()
                    .branch('z', node("C"))))
            .branch('j', node()
                .branch('a', node()
                    .branch('z', node()
                        .branch('z', node()
                            .branch('y', node("F"))))));

        if (!equals(delete1, getField(STUDENT, "root"))) {
            return 0;
        }

        STUDENT.delete(a(""));
        STUDENT.delete(a("foo"));
        STUDENT.delete(a("jazz")); // should do nothing

        MockNode delete2 = node()
            .branch('f', node()
                .branch('e', node()
                    .branch('z', node("C"))))
            .branch('j', node()
                .branch('a', node()
                    .branch('z', node()
                        .branch('z', node()
                            .branch('y', node("F"))))));

        if (!equals(delete2, getField(STUDENT, "root"))) {
            return 0;
        }

        STUDENT.insert(a("f"), "Z");
        STUDENT.delete(a("jazzy"));
        STUDENT.delete(a("fez"));

        MockNode delete3 = node().branch('f', node("Z"));

        if (!equals(delete3, getField(STUDENT, "root"))) {
            return 0;
        }

        STUDENT.delete(a("f"));

        boolean rootIsSingleNode = equals(node(), getField(STUDENT, "root"));
        boolean rootIsNull = equals(null, getField(STUDENT, "root"));
        if (!(rootIsSingleNode || rootIsNull)) {
            return 0;
        }

        return 1;

    }

    protected static boolean equals(MockNode expected, HashTrieMap<Character, AlphabeticString, String>.HashTrieNode student) {
        if (expected == null && student == null) {
            return true;
        } else if (expected == null || student == null) {
            // If only one of the two is null
            return false;
        } else if (expected.value != null && !expected.value.equals(student.value)) {
            // If values don't match
            return false;
        } else if (expected.value == null && student.value != null) {
            // If only one of the values are null
            return false;
        } else if (expected.pointers.size() != student.pointers.size()) {
            // If number of pointers is not the same
            return false;
        } else {
            // If student doesn't contain the given char, 'equals' will fail one level down
            // in one of the base cases
            for (char c : expected.pointers.keySet()) {
                boolean result = equals(expected.pointers.get(c), student.pointers.get(c));
                if (!result) {
                    return false;
                }
            }
            return true;
        }
    }

    protected static MockNode node() {
        return new MockNode();
    }

    protected static MockNode node(String value) {
        return new MockNode(value);
    }

    protected static class MockNode {
        public Map<Character, MockNode> pointers;
        public String value;

        public MockNode() {
            this(null);
        }

        public MockNode(String value) {
            this.pointers = new HashMap<>();
            this.value = value;
        }

        public MockNode branch(char c, MockNode child) {
            this.pointers.put(c, child);
            return this;
        }
    }

    public static int stressTest() {
        // Should contain 30 characters
        char[] symbols = "abcdefghijklmnopqrstuvwxyz!@#$".toCharArray();
        long i = 0;
        for (char a : symbols) {
            for (char b : symbols) {
                for (char c : symbols) {
                    for (char d : symbols) {
                        Character[] word = new Character[]{a, b, c, d};
                        STUDENT.insert(new AlphabeticString(word), "" + i);
                        i += 1;
                    }
                }
            }
        }

        for (char a : symbols) {
            for (char b : symbols) {
                if (!STUDENT.findPrefix(new AlphabeticString(new Character[]{a, b}))) {
                    return 0;
                }
            }
        }

        i = 0;
        for (char a : symbols) {
            for (char b : symbols) {
                for (char c : symbols) {
                    for (char d : symbols) {
                        Character[] word = new Character[]{a, b, c, d};
                        if (!STUDENT.find(new AlphabeticString(word)).equals("" + i)) {
                            return 0;
                        }
                        i += 1;
                    }
                }
            }
        }

        return 1;
    }

    /**
     * Converts a String into an AlphabeticString
     */
    private static AlphabeticString a(String s) {
        return new AlphabeticString(s);
    }

    /**
     * Checks if the trie contains the word and the expected value, and that all prefixes of
     * the word exist in the trie.
     */
    private static boolean containsPath(HashTrieMap<Character, AlphabeticString, String> trie, String word, String expectedValue) {
        AlphabeticString key = a(word);

        boolean valueCorrect = expectedValue.equals(trie.find(key));
        boolean fullWordIsPrefix = trie.findPrefix(key);
        boolean invalidWordDoesNotExist = trie.find(a(word + "$")) == null;

        if (!valueCorrect || !fullWordIsPrefix || !invalidWordDoesNotExist) {
            return false;
        }

        return allPrefixesExist(trie, word);
    }

    /**
     * Checks if the trie contains the word, and that all prefixes of the word exist in the trie.
     *
     * Assumes that the expected value is word.toUpperCase().
     */
    private static boolean containsPath(HashTrieMap<Character, AlphabeticString, String> trie, String word) {
        return containsPath(trie, word, word.toUpperCase());
    }

    /**
     * Returns true if all prefixes of a word exist in the trie.
     *
     * That is, if we do `trie.insert(new AlphabeticString("dog"), "some-value")`, this method
     * would check to see if "dog", "do", "d", and "" are all prefixes of the trie.
     */
    private static boolean allPrefixesExist(HashTrieMap<Character, AlphabeticString, String> trie, String word) {
        String accum = "";
        for (char c : word.toCharArray()) {
            accum += c;
            if (!trie.findPrefix(a(accum))) {
                return false;
            }
        }
        return true;
    }

    private static boolean containsAllPaths(HashTrieMap<Character, AlphabeticString, String> trie, String... words) {
        for (String word : words) {
            if (!containsPath(trie, word)) {
                return false;
            }
        }
        return true;
    }

    private static boolean doesNotContainAll(HashTrieMap<Character, AlphabeticString, String> trie, String... words) {
        for (String word : words) {
            if (trie.find(a(word)) != null) {
                return false;
            }
        }
        return true;
    }

    private static boolean containsAllPrefixes(HashTrieMap<Character, AlphabeticString, String> trie, String... words) {
        for (String word : words) {
            if (!trie.findPrefix(a(word))) {
                return false;
            }
        }
        return true;
    }

    private static boolean doesNotContainAllPrefixes(HashTrieMap<Character, AlphabeticString, String> trie, String... words) {
        for (String word : words) {
            if (trie.findPrefix(a(word))) {
                return false;
            }
        }
        return true;
    }

    private static void addAll(HashTrieMap<Character, AlphabeticString, String> trie, String... words) {
    	printer.println("addAll adds these words: " );
    	printer.println(words);
        for (String word : words) {
        	printer.println("Connecting " + a(word) + " to " + word.toUpperCase());
            trie.insert(a(word), word.toUpperCase());
        }
    }
}
