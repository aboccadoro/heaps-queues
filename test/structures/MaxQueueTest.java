package structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import comparators.IntegerComparator;

public class MaxQueueTest {

  MaxQueue<String> queue;
	
  @Before
  public void setup() {
    queue = new MaxQueue<String>();
  }

  @Test (timeout = 100)
  public void testQueue() {
    queue.enqueue(100, "Highest priority");
    queue.enqueue(50, "High priority");
    queue.enqueue(25, "Medium priority");
    queue.enqueue(0, "Low priority");
    assertEquals("Highest priority", queue.dequeue());
    assertEquals("High priority", queue.dequeue());
    assertEquals("Medium priority", queue.dequeue());
    assertEquals("Low priority", queue.dequeue());
  }

  @Test (timeout = 100, expected = NullPointerException.class)
  public void testEnqueueException() {
	  queue.enqueue(null, null);
  }
  
  @Test (timeout = 100, expected = IllegalStateException.class)
  public void testDequeueException() {
	  queue.dequeue();
  }
  
  @Test (timeout = 100, expected = IllegalStateException.class)
  public void testPeekException() {
	  queue.peek();
  }
  
  @Test (timeout = 100, expected = UnsupportedOperationException.class)
  public void testRemoveException() {
	  Iterator<Entry<Integer, String>> iter = queue.iterator();
	  iter.remove();
  }
  
  @Test (timeout = 100, expected = NoSuchElementException.class)
  public void testNextException() {
	  Iterator<Entry<Integer, String>> iter = queue.iterator();
	  iter.next();
  }
  
  @Test (timeout = 100)
  public void testEnqueueAndPeek() {
	  queue.enqueue(3, "one");
	  assertEquals("one", queue.peek());
	  queue.enqueue(4, "two");
	  assertEquals("two", queue.peek());
	  queue.enqueue(2, "three");
	  assertEquals("two", queue.peek());
	  queue.enqueue(1, "four");
	  assertEquals("two", queue.peek());
	  queue.enqueue(6, "five");
	  assertEquals("five", queue.peek());
	  queue.enqueue(5, "six");
	  assertEquals("five", queue.peek());
	  
	  int x = queue.size();
	  for (int i = 0; i < x; i++)
		  queue.dequeue();
	
	  queue.enqueue(5, "dog");
	  assertEquals("dog", queue.peek());
	  queue.enqueue(4, "cat");
	  assertEquals("dog", queue.peek());
	  queue.enqueue(3, "bird");
	  assertEquals("dog", queue.peek());
	  queue.enqueue(2, "tiger");
	  assertEquals("dog", queue.peek());
	  queue.enqueue(1, "fish");
	  assertEquals("dog", queue.peek());
	  
	  int y = queue.size();
	  for (int i = 0; i < y; i++)
		  queue.dequeue();
	
	  queue.enqueue(1, "dog");
	  assertEquals("dog", queue.peek());
	  queue.enqueue(2, "cat");
	  assertEquals("cat", queue.peek());
	  queue.enqueue(3, "bird");
	  assertEquals("bird", queue.peek());
	  queue.enqueue(4, "tiger");
	  assertEquals("tiger", queue.peek());
	  queue.enqueue(5, "fish");
	  assertEquals("fish", queue.peek());
  }
  
  @Test (timeout = 100)
  public void testDequeue() {
	  queue.enqueue(5, "dog");
	  queue.enqueue(4, "cat");
	  queue.enqueue(3, "bird");
	  queue.enqueue(2, "tiger");
	  queue.enqueue(1, "fish");
	  
	  assertEquals("dog", queue.dequeue());
	  assertEquals("cat", queue.dequeue());
	  assertEquals("tiger", queue.dequeue());
	  assertEquals("bird", queue.dequeue());
	  assertEquals("fish", queue.dequeue());
	  
	  queue.enqueue(1, "dog");
	  queue.enqueue(2, "cat");
	  queue.enqueue(2, "fish");
	  
	  assertEquals("cat", queue.dequeue());
	  assertEquals("fish", queue.dequeue());
	  assertEquals("dog", queue.dequeue());
  }
  
  @Test (timeout = 100)
  public void testSize() {
	  queue.enqueue(5, "dog");
	  assertEquals(1, queue.size());
	  queue.enqueue(4, "cat");
	  assertEquals(2, queue.size());
	  queue.enqueue(3, "bird");
	  assertEquals(3, queue.size());
	  queue.enqueue(2, "tiger");
	  assertEquals(4, queue.size());
	  queue.enqueue(1, "fish");
	  assertEquals(5, queue.size());
  }
  
  @Test (timeout = 100)
  public void testIsEmpty() {
	  assertTrue("queue with 0 elements should be empty", queue.isEmpty());
	  queue.enqueue(3, "dog");
	  assertFalse("queue with 1 element shouldn't be empty", queue.isEmpty());
	  queue.enqueue(2, "cat");
	  queue.enqueue(1, "bird");
	  assertFalse("queue with 3 elements shouldn't be empty", queue.isEmpty());
	  queue.dequeue();
	  assertFalse(queue.isEmpty());
	  queue.dequeue();
	  assertFalse(queue.isEmpty());
	  queue.dequeue();
	  assertTrue(queue.isEmpty());
  }
  
  @Test (timeout = 100)
  public void testGetComparator() {
	  assertEquals((new IntegerComparator()).getClass(), queue.getComparator().getClass());
  }
  
  @Test (timeout = 100)
  public void testIterator() {
	  queue.enqueue(5, "dog");
	  queue.enqueue(4, "cat");
	  queue.enqueue(3, "bird");
	  queue.enqueue(2, "tiger");
	  queue.enqueue(1, "fish");
	  
	  Iterator<Entry<Integer, String>> iter = queue.iterator();
	  
	  assertTrue(iter.hasNext());
	  assertEquals("dog", iter.next().getValue());
	  assertTrue(iter.hasNext());
	  assertEquals("cat", iter.next().getValue());
	  assertTrue(iter.hasNext());
	  assertEquals("bird", iter.next().getValue());
	  assertTrue(iter.hasNext());
	  assertEquals("tiger", iter.next().getValue());
	  assertTrue(iter.hasNext());
	  assertEquals("fish", iter.next().getValue());
  }
}