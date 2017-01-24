# Project 1 (Zip) Feedback #
## CSE 332 Winter 2017 ##

**Team:** Alia Paddock (akp42) and Anupam Gupta (anupamg) <br />
**Graded By:** Royden Luckey (rluckey@cs.washington.edu)
<br>

## Unit Tests ##

**ArrayStack**  `(9/9)`
> ✓ Passed *testHasWork* <br>
> ✓ Passed *testHasWorkAfterAdd* <br>
> ✓ Passed *testHasWorkAfterAddRemove* <br>
> ✓ Passed *testPeekHasException* <br>
> ✓ Passed *testNextHasException* <br>
> ✓ Passed *testClear* <br>
> ✓ Passed *checkStructure* <br>
> ✓ Passed *stressTest* <br>
> ✓ Passed *fuzzyStressTest* <br>

**CircularArrayFIFOQueue**  `(14/14)`
> ✓ Passed *testHasWork* <br>
> ✓ Passed *testHasWorkAfterAdd* <br>
> ✓ Passed *testHasWorkAfterAddRemove* <br>
> ✓ Passed *testPeekHasException* <br>
> ✓ Passed *testNextHasException* <br>
> ✓ Passed *testClear* <br>
> ✓ Passed *checkStructure* <br>
> ✓ Passed *testPeekAndUpdateEmpty* <br>
> ✓ Passed *testPeekAndUpdateOutOfBounds* <br>
> ✓ Passed *testUpdatingOutOfBounds* <br>
> ✓ Passed *testUpdate* <br>
> ✓ Passed *testCycle* <br>
> ✓ Passed *stressTest* <br>
> ✓ Passed *fuzzyStressTest* <br>

**ListFIFOQueue**  `(9/9)`
> ✓ Passed *testHasWork* <br>
> ✓ Passed *testHasWorkAfterAdd* <br>
> ✓ Passed *testHasWorkAfterAddRemove* <br>
> ✓ Passed *testPeekHasException* <br>
> ✓ Passed *testNextHasException* <br>
> ✓ Passed *testClear* <br>
> ✓ Passed *checkStructure* <br>
> ✓ Passed *stressTest* <br>
> ✓ Passed *fuzzyStressTest* <br>

**MinFourHeap**  `(14/18)`
> ✓ Passed *testHasWork* <br>
> ✓ Passed *testHasWorkAfterAdd* <br>
> ✓ Passed *testHasWorkAfterAddRemove* <br>
> ✓ Passed *testPeekHasException* <br>
> ✓ Passed *testNextHasException* <br>
> ✓ Passed *testClear* <br>
> ✓ Passed *checkStructure* <br>
> ✓ Passed *testHeapWith5Items* <br>
> ✓ Passed *testHugeHeap* <br>
> ✓ Passed *testOrderingDoesNotMatter* <br>
> ✓ Passed *testWithCustomComparable* <br>
> ✓ Passed *testStructureInorderInput* <br>
> ✓ Passed *testStructureReverseOrderInput* <br>
> ✓ Passed *testStructureInterleavedInput* <br>
> ✓ Passed *testStructureRandomInput* <br>
> ✓ Passed *testStructureWithDups* <br>
> ✓ Passed *stressTest* <br>
> ✓ Passed *fuzzyStressTest* <br>

**HashTrieMap**  `(19/19)`
> ✓ Passed *testBasic* <br>
> ✓ Passed *testBasicDelete* <br>
> ✓ Passed *testFindPrefixes* <br>
> ✓ Passed *testFindNonexistentDoesNotCrash* <br>
> ✓ Passed *testFindingNullEntriesCausesError* <br>
> ✓ Passed *testInsertReplacesOldValue* <br>
> ✓ Passed *testInsertingNullEntriesCausesError* <br>
> ✓ Passed *testDeleteAll* <br>
> ✓ Passed *testDeleteNothing* <br>
> ✓ Passed *testDeleteAndInsertSingleChars* <br>
> ✓ Passed *testDeleteWorksWhenTrieHasNoBranches* <br>
> ✓ Passed *testDeletingAtRoot* <br>
> ✓ Passed *testDeletingEmptyString* <br>
> ✓ Passed *testDeletingNullEntriesCausesError* <br>
> ✓ Passed *testClear* <br>
> ✓ Passed *checkUnderlyingStructure* <br>
> ✓ Passed *stressTest* <br>
> ✓ Passed *testSize* <br>
> ✓ Passed *testSizeWorksWithMissing* <br>

**SuffixTrie**  `(10/10)`
> ✓ Passed *testExampleFromSpec* <br>
> ✓ Passed *testExampleFromSpecUsingSmallBufferSize* <br>
> ✓ Passed *testExampleFromSpecUsingDifferentBufferSizes* <br>
> ✓ Passed *testAllUnique* <br>
> ✓ Passed *testAllSame* <br>
> ✓ Passed *testParagraph* <br>
> ✓ Passed *testRepetitive* <br>
> ✓ Passed *testDna* <br>
> ✓ Passed *testFakePaper* <br>
> ✓ Passed *testCourseWebsite* <br>

**SuffixTrieStudent** ✝ Exception `(0/0)`

## Miscellaneous ##

`(-0/0)`


You should wait to resize MinFourHeap until attempting to insert and the array is already full. You should not allocate additional space (and perform a copy of all current data) before it is actually required, as it may *never* be needed.

--------

## Write-Up ##

**Partnership**
`(1/1)`
Glad your partnership went well!

**How was the project?**
`(1/1)`
Good to hear that you enjoyed the project. Debugging can be frustrating but is a great skill to have.

### WorkLists, Tries, and Zip ###

**peek(i)**
`(1/2)`
Why does peek(i) *not* make sense for the WorkList ADT in general?


**TrieMap vs. (HashMap and TreeMap)**
`(1/2)`
In general, Tries are O(d), where d is the length of the key.  HashMaps are O(1), but that "1" could very easily be "d".
Even if we wanted to, there are Key and Value types in which we *can't* use a trie because they can't be decomposed into letters.

**Applications of TrieMap**
`(1/3)`
We need to construct a dictionary (here, it's english words) for Tries to be useful here.
Since we only if care if a word exists or not, a TrieSet would be a better choice than a TrieMap.
One of the major advantages of using the Trie is that we can stop if a prefix isn't in the dictionary

**Running Zip**
`(1/1)`

**Zip Experiment**
`(6/6)`

NOTE: In the future, you should include all of your raw data used in
calculations (as a CSV file, table, etc.) in the writeup folder.

Great job noticing how different image types compress. Unless you use a raw
bitmap (such as BMP), most image formats are already compressed very
efficiently, so you end up adding additional header data without getting a
significant increase in compression. PDFs often contain compressed material
also.  It would also have been worth talking about why HTML has a much higher
compression ratio than text (think about the HTML tags).

### Above and Beyond ###

**Above and Beyond**
`(EX: 0)`
