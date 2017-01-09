package tests.gitlab.ckpt1;

import cse332.interfaces.worklists.FixedSizeFIFOWorkList;
import datastructures.worklists.CircularArrayFIFOQueue;

public class CircularArrayFIFOQueueTests extends WorklistGradingTests {
   public static final int DEFAULT_CAPACITY = 1000;

   public static void main(String[] args) {
       new CircularArrayFIFOQueueTests().run();
   }

   @Override
   protected void run() {
       super.run();
       test("testUpdate");
       test("testCycle");
       finish();
   }

   public static void init() {
       STUDENT_STR = new CircularArrayFIFOQueue<>(DEFAULT_CAPACITY);
       STUDENT_DOUBLE = new CircularArrayFIFOQueue<>(DEFAULT_CAPACITY);
       STUDENT_INT = new CircularArrayFIFOQueue<>(100000);
   }

   public static int testClear() {
       FixedSizeFIFOWorkList<String> queue = new CircularArrayFIFOQueue<>(5);
       addAll(queue, new String[] {"Beware", "the", "Jabberwock", "my", "son!"});

       if (!queue.hasWork() || (queue.size() != 5) || !queue.isFull() || queue.capacity() != 5) {
           return 0;
       }

       queue.clear();

       boolean result = !queue.hasWork() &&
               queue.size() == 0 &&
               !queue.isFull() &&
               queue.capacity() == 5 &&
               doesPeekThrowException(queue) &&
               doesNextThrowException(queue);

       return result ? 1 : 0;
   }

   public static int testUpdate() {
       FixedSizeFIFOWorkList<Integer> queue = new CircularArrayFIFOQueue<>(10);

       for (int i = 0; i < 8; i++) {
           queue.add(i);
           if (queue.size() != i + 1) {
               return 0;
           }
       }

       for (int i = 0; i < 8; i++) {
           if (queue.peek(i) != i) {
               return 0;
           }
       }
       if (queue.size() != 8 && !queue.isFull()) {
           return 0;
       }

       for (int i = 0; i < 8; i += 2) {
           queue.update(i, -i);
       }

       for (int i = 0; i < 8; i++) {
           int expected = i * ((i % 2 == 0) ? -1 : 1);
           if (queue.peek(i) != expected) {
               return 0;
           }
       }
       if (queue.size() != 8) {
           return 0;
       }

       for (int i = 0; i < 8; i++) {
           int expected = i * ((i % 2 == 0) ? -1 : 1);
           if (queue.peek() != expected || queue.next() != expected) {
               return 0;
           }
       }

       return queue.size() == 0 ? 1 : 0;
   }

   public static int testCycle() {
       return (testCycle(1) && testCycle(2) && testCycle(9) && testCycle(10)) ? 1 : 0;
   }

   private static boolean testCycle(int capacity) {
       FixedSizeFIFOWorkList<Integer> queue = new CircularArrayFIFOQueue<>(capacity);

       for (int i = 0; i < capacity; i++) {
           queue.add(i);
           if (queue.size() != i + 1) {
               return false;
           }
       }

       if (!queue.hasWork() || !queue.isFull()) {
           return false;
       }

       for (int i = capacity; i < 100000; i++) {
           boolean peekWorks = queue.peek() == (i - capacity);
           boolean nextWorks = queue.next() == (i - capacity);
           boolean isNotFullAfterRemove = !queue.isFull();
           queue.add(i);
           boolean sizeCorrect = queue.size() == capacity;
           boolean isFull = queue.isFull();
           if (!peekWorks || !nextWorks || !isNotFullAfterRemove || !sizeCorrect || !isFull) {
               return false;
           }
       }

       return true;
   }

   public static int checkStructure() {
       FixedSizeFIFOWorkList<Integer> queue = new CircularArrayFIFOQueue<>(1000);

       // Fill
       for (int i = 0; i < 1000; i++) {
           boolean isFullWorks = !queue.isFull();
           queue.add(i);
           boolean peekWorks = (queue.peek() == 0 && queue.peek(0) == 0);
           boolean specialPeekWorks = queue.peek(i) == i;
           boolean hasWork = queue.hasWork();
           boolean sizeIsCorrect = (queue.size() == (i + 1));
           if (!isFullWorks || !peekWorks || !specialPeekWorks || !hasWork || !sizeIsCorrect) {
               return 0;
           }
       }

       if (queue.size() != 1000 || !queue.isFull()) {
           return 0;
       }

       try {
           queue.add(2000);
           return 0;
       } catch (IllegalStateException ex) {
           // Queue throws correct exception when full; move on
       }

       // Check peek
       for (int i = 0; i < 1000; i++) {
           if (queue.peek(i) != i) {
               return 0;
           }
       }

       // Empty
       for (int i = 0; i < 999; i++) {
           boolean hasWorks = queue.hasWork();
           boolean peekWorks = (queue.peek() == i && queue.peek(0) == i);
           boolean nextWorks = (queue.next() == i);
           boolean sizeIsCorrect = (queue.size() == 999 - i);
           boolean isFullWorks = !queue.isFull();
           if (!hasWorks || !peekWorks || !nextWorks || !sizeIsCorrect || !isFullWorks) {
               return 0;
           }
       }

       return 1;
   }
}