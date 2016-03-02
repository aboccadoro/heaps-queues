package structures;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import comparators.StringLengthComparator;

public class AbstractArrayHeapTest {

  private AbstractArrayHeap<String, Integer> arrayHeap;

  @Before
  public void setup() {
    arrayHeap = new StudentArrayHeap<String, Integer>(new StringLengthComparator());
  }

  @Test(timeout = 100)
  public void testBubbleUp() {
    // The comparator being used prioritizes by the length of the string
    // passed in as a priority
		
    List<Entry<String, Integer>> view = arrayHeap.asList();
		
    arrayHeap.add(".", 1);
    assertEquals("Peek value should be 1.", new Integer(1),
                 arrayHeap.peek());
		
    /*
     *    Original State       Initial Add               After BubbleUp
     *    (".", 1)     =>         (".", 1)         =>    ("..", 2)            
     *                             /                       /
     *                         ("..", 2)               (".", 1)
     */
		
    arrayHeap.add("..", 2);
    assertEquals("Peek value should be 2.", new Integer(2), arrayHeap.peek());
    assertEquals("The second element in the array should be 1.", new Integer(1), view.get(1).getValue());

    /*
     *    Original State       Initial Add                   After BubbleUp
     *    ("..", 2)     =>      ("..", 2)                =>          ("...", 3)
     *     /                    /        \                           /       \
     *  (".", 1)             (".", 1)    ("...", 3)             (".", 1)     ("..", 2)
     */
		
    arrayHeap.add("...", 3);
    assertEquals("Peek value should be 3.", new Integer(3), arrayHeap.peek());
    assertEquals("The third element in the array should be 2.", new Integer(2), view.get(2).getValue());
		
    /*
     *    Original State             Initial Add                   After BubbleUp
     *    ("...", 3)          =>     ("...", 3)           =>        (".....", 4)
     *    /        \               /             \                  /           \
     * (".", 1)  ("..", 2)     (".", 1)       ("..", 2)        ("...", 3)      ("..", 2)
     *                        /                                 /
     *                     (".....", 4)                    (".", 1)
     */
		
    arrayHeap.add(".....", 4);
    assertEquals("Peek value should be 4.", new Integer(4), arrayHeap.peek());
    assertEquals("The fourth element in the array should be 1.", new Integer(1), view.get(3).getValue());
    assertEquals("The second element in the array should be 3.", new Integer(3), view.get(1).getValue());
		
    /*
     *    Original State           =>       Initial Add            =>      After BubbleUp
     *       (".....", 4)                   (".....", 4)                     (".....", 4)
     *      /           \                   /         \                       /        \
     *  ("...", 3)      ("..", 2)       ("...", 3)    ("..", 2)         ("....", 5)   ("..", 2)
     *    /                              /      \                       /        \
     * (".", 1)                    (".", 1)    ("....", 5)          (".", 1)    ("...", 3)
     */
		
    arrayHeap.add("....", 5);
    assertEquals("Peek value should be 4.", new Integer(4), arrayHeap.peek());
    assertEquals("The fifth element in the array should be 3.", new Integer(3), view.get(4).getValue());
    assertEquals("The second element in the array should be 5.", new Integer(5), view.get(1).getValue());
  }
	
  @Test (timeout = 100)
  public void testBubbleUpAndBubbleDown(){
    // The comparator being used prioritizes by the length of the string
    // passed in as a priority
				
    List<Entry<String, Integer>> view = arrayHeap.asList();
				
    arrayHeap.add(".", 1).add("..", 2).add("...", 3).add(".....", 4).add("....", 5);
				
    /*
     *    Starting State          =>   Initial removal        =>     After Bubble Down
     *    (".....", 4)                   ("...", 3)                  ("....", 5)
     *     /        \                    /        \                  /         \
     * ("....", 5)   ("..", 2)       ("....", 5)  ("..", 2)      ("...", 3)    ("..", 2)
     *   /        \                   /                           /
     *(".", 1)    ("...", 3)        (".", 1)                    (".", 1)
     */
    assertEquals("The highest priority is 4.", new Integer(4), arrayHeap.remove());
    assertEquals("The second Element should be 3", new Integer(3), view.get(1).getValue());
		
    /*
     *    Starting State          => Initial removal        => After Bubble Down
     *    ("....", 5)                  (".", 1)                      ("...", 3)
     *     /         \                  /     \                      /        \                 
     * ("...", 3)    ("..", 2)    ("...", 3)  ("..", 2)          (".", 1)     ("..", 2)
     *   /
     * (".", 1)
     */
    assertEquals("The highest priority is 5.", new Integer(5), arrayHeap.remove());
    assertEquals("The second element is 1.", new Integer(1), view.get(1).getValue());
		
    /*
     *    Starting State              => Initial removal     => After Bubble Down
     *    ("...", 3)                     ("..", 2)               ("..", 2)
     *     /        \                    /                       /
     * (".", 1)     ("..", 2)         (".", 1)                 (".", 1)
     *   
     */
    assertEquals("The highest priority is 3.", new Integer(3), arrayHeap.remove());
    assertEquals("The second element is 1.", new Integer(1), view.get(1).getValue());
		
    /*
     *    After Bubble Down          => Initial removal        => After Bubble Down
     *    ("..", 2)                      (".", 1)                    (".", 1)
     *     /
     * (".", 1)
     *   
     */
    assertEquals("The highest priority is 2.", new Integer(2), arrayHeap.remove());
    assertEquals("The last element is 1.", new Integer(1), view.get(0).getValue());
		
		
  }
	
  @Test (timeout = 100)
  public void testIndiceFunctions(){
    assertEquals(1, arrayHeap.getLeftChildOf(0));
    assertEquals(2, arrayHeap.getRightChildOf(0));
    assertEquals(3, arrayHeap.getLeftChildOf(1));
    assertEquals(4, arrayHeap.getRightChildOf(1));
    assertEquals(5, arrayHeap.getLeftChildOf(2));
    assertEquals(6, arrayHeap.getRightChildOf(2));
    assertEquals(0, arrayHeap.getParentOf(1));
    assertEquals(0, arrayHeap.getParentOf(2));
    assertEquals(1, arrayHeap.getParentOf(3));
    assertEquals(1, arrayHeap.getParentOf(4));
    assertEquals(2, arrayHeap.getParentOf(5));
    assertEquals(2, arrayHeap.getParentOf(6));
  }
	
  @Test (timeout = 100, expected = IndexOutOfBoundsException.class)
  public void testIndexOutOfBoundsException1(){
    arrayHeap.getLeftChildOf(-1);
  }
	
  @Test (timeout = 100, expected = IndexOutOfBoundsException.class)
  public void testIndexOutOfBoundsException2(){
    arrayHeap.getRightChildOf(-1);
  }
	
  @Test (timeout = 100, expected = IndexOutOfBoundsException.class)
  public void testIndexOutOfBoundsException3(){
    arrayHeap.getParentOf(0);
  }
  
  @Test (timeout = 100)
  public void testGetLeftChildOf() {
	  arrayHeap.add("xx", 1);
	  arrayHeap.add("xxx", 2);
	  assertEquals(1, arrayHeap.getLeftChildOf(0));//value: 1
	  arrayHeap.add("xxxx", 3);
	  assertEquals(3, arrayHeap.getLeftChildOf(1));//value: 1
	  arrayHeap.add("xxxxx", 4);
	  arrayHeap.add("x", 5);
	  arrayHeap.add("x", 6);
	  assertEquals(5, arrayHeap.getLeftChildOf(2));//value: 6
	  arrayHeap.add("x", 7);
	  arrayHeap.add("x", 8);
	  assertEquals(7, arrayHeap.getLeftChildOf(3));//value: 8
  }
  
  @Test (timeout = 100)
  public void testGetRightChildOf() {
	  arrayHeap.add("xx", 1);
	  arrayHeap.add("xxx", 2);
	  arrayHeap.add("xxxx", 3);
	  assertEquals(2, arrayHeap.getRightChildOf(0));//value: 3
	  arrayHeap.add("xxxxx", 4);
	  arrayHeap.add("x", 5);
	  assertEquals(4, arrayHeap.getRightChildOf(1));//value: 5
	  arrayHeap.add("x", 6);
	  arrayHeap.add("x", 7);
	  assertEquals(6, arrayHeap.getRightChildOf(2));//value: 7
	  arrayHeap.add("x", 8);
	  arrayHeap.add("x", 9);
	  assertEquals(8, arrayHeap.getRightChildOf(3));//value: 9
  }
  
  @Test (timeout = 100)
  public void testGetParentOf() {
	  arrayHeap.add("xxxxxxx", 7);
	  arrayHeap.add("xxxxxx", 6);
	  assertEquals(0, arrayHeap.getParentOf(1));
	  arrayHeap.add("xxxxx", 5);
	  assertEquals(0, arrayHeap.getParentOf(2));
	  arrayHeap.add("xxxx", 4);
	  assertEquals(1, arrayHeap.getParentOf(3));
	  arrayHeap.add("xxx", 3);
	  assertEquals(1, arrayHeap.getParentOf(4));
	  arrayHeap.add("xx", 2);
	  assertEquals(2, arrayHeap.getParentOf(5));
	  arrayHeap.add("x", 1);
	  assertEquals(2, arrayHeap.getParentOf(6));
  }
  
  @Test (timeout = 100)
  public void testSizeAndIsEmpty() {
	  assertTrue(arrayHeap.isEmpty());
	  arrayHeap.add("x", 1);
	  assertFalse(arrayHeap.isEmpty());
	  assertEquals(1, arrayHeap.size());
	  arrayHeap.add("xx", 2);
	  arrayHeap.add("xxx", 3);
	  assertEquals(3, arrayHeap.size());
	  arrayHeap.add("xxxx", 4);
	  arrayHeap.add("xxxxx", 5);
	  arrayHeap.add("xxxxxx", 6);
	  assertEquals(6, arrayHeap.size());
  }
  
  @Test (timeout = 100)
  public void testBubble() {
	  arrayHeap.add("xxx", 3);
	  arrayHeap.add("x", 1);
	  assertEquals(new Integer(3), arrayHeap.peek());
	  arrayHeap.add("xx", 2);
	  assertEquals(new Integer(3), arrayHeap.remove());
	  arrayHeap.add("xxxxx", 5);
	  assertEquals(new Integer(5), arrayHeap.remove());
	  arrayHeap.add("xxxx", 4);
	  arrayHeap.add("xxx", 3);
	  assertEquals(new Integer(4), arrayHeap.peek());
	  assertEquals(new Integer(4), arrayHeap.remove());
	  assertEquals(new Integer(3), arrayHeap.remove());
	  assertEquals(new Integer(2), arrayHeap.remove());
	  assertEquals(new Integer(1), arrayHeap.remove());
  }
  
  @Test (timeout = 100)
  public void testSwap() {
	  arrayHeap.add("xxxxx", 5);
	  arrayHeap.add("xxxx", 4);
	  arrayHeap.add("xxx", 3);
	  arrayHeap.add("xx", 2);
	  arrayHeap.add("x", 1);
	  
	  arrayHeap.swap(0, 1);
	  assertEquals(new Integer(4), arrayHeap.remove());
	  arrayHeap.swap(0, 3);
	  assertEquals(new Integer(1), arrayHeap.remove());
	  arrayHeap.swap(0, 2);
	  assertEquals(new Integer(3), arrayHeap.remove());
	  arrayHeap.swap(0, 1);
	  assertEquals(new Integer(2), arrayHeap.remove());
  }
  
  @Test (timeout = 100)
  public void testGetComparator() {
	  assertEquals(new StringLengthComparator().getClass(), arrayHeap.getComparator().getClass());
  }
  
  @Test (timeout = 100)
  public void testAdd() {
	  arrayHeap.add("xxxxxxxxxx", 1);
	  arrayHeap.add("xxxxx", 2);
	  assertEquals(new Integer(1), arrayHeap.peek());
	  arrayHeap.add("xxxxxxxxxxxxxx", 3);
	  assertEquals(new Integer(3), arrayHeap.peek());
	  arrayHeap.add("xxxxxxx", 4);
	  assertEquals(new Integer(3), arrayHeap.peek());
	  arrayHeap.add("xxxxxxxxxxx", 5);
	  assertEquals(new Integer(3), arrayHeap.peek());
	  arrayHeap.add("xxxxxxxxxxxxxxx", 6);
	  assertEquals(new Integer(6), arrayHeap.peek());
	  arrayHeap.add("xxxxxxxxxxxxxxxx", 7);
	  assertEquals(new Integer(7), arrayHeap.peek());
	  arrayHeap.add("xxxxxxxxxx", 8);
	  assertEquals(new Integer(7), arrayHeap.peek());
	  arrayHeap.add("xxxxxxxxxxxx", 9);
	  assertEquals(new Integer(7), arrayHeap.peek());
	  arrayHeap.add("xx", 10);
	  assertEquals(new Integer(7), arrayHeap.peek());
  }
  
  @Test (timeout = 100)
  public void testRemove() {
	  arrayHeap.add("xxxxxxxxxx", 1);
	  assertEquals(new Integer(1), arrayHeap.remove());
	  arrayHeap.add("xxxxx", 2);
	  arrayHeap.add("xxxxxxxxxxxxxx", 3);
	  assertEquals(new Integer(3), arrayHeap.remove());
	  arrayHeap.add("xxxxxxx", 4);
	  arrayHeap.add("xxxxxxxxxxx", 5);
	  arrayHeap.add("xxxxxxxxxxxxxxx", 6);
	  arrayHeap.add("xxxxxxxxxxxxxxxx", 7);
	  arrayHeap.add("xxxxxxxxxx", 8);
	  arrayHeap.add("xxxxxxxxxxxx", 9);
	  arrayHeap.add("xx", 10);
	  
	  assertEquals(new Integer(7), arrayHeap.remove());//xxxxxxxxxxxxxxxx
	  assertEquals(new Integer(6), arrayHeap.remove());//xxxxxxxxxxxxxxx
	  assertEquals(new Integer(8), arrayHeap.remove());//xxxxxxxxxx
	  assertEquals(new Integer(4), arrayHeap.remove());//xxxxxxx
	  assertEquals(new Integer(5), arrayHeap.remove());//xxxxxxxxxxx
	  assertEquals(new Integer(2), arrayHeap.remove());//xxxxx
	  assertEquals(new Integer(9), arrayHeap.remove());//xxxxxxxxxxxx
	  assertEquals(new Integer(10), arrayHeap.remove());//xx
  }
  
  @Test (timeout = 100)
  public void testAsList() {
	  arrayHeap.add("x", 1);
	  arrayHeap.add("xx", 2);
	  arrayHeap.add("xxx", 3);
	  arrayHeap.add("xxxx", 4);
	  arrayHeap.add("xxxxx", 5);
	  arrayHeap.add("xxxxxx", 6);
	  arrayHeap.add("xxxxxxx", 7);
	  arrayHeap.add("xxxxxxxx", 8);
	  arrayHeap.add("xxxxxxxxx", 9);
	  arrayHeap.add("xxxxxxxxxx", 10);
	  
	  List<Entry<String, Integer>> list = arrayHeap.asList();
	  
	  assertTrue(list.size() == 10);
	  assertTrue(list.get(0).getValue().equals(new Integer(10)));
	  assertTrue(list.get(1).getValue().equals(new Integer(9)));
	  assertTrue(list.get(2).getValue().equals(new Integer(6)));
	  assertTrue(list.get(3).getValue().equals(new Integer(7)));
	  assertTrue(list.get(4).getValue().equals(new Integer(8)));
	  assertTrue(list.get(5).getValue().equals(new Integer(2)));
	  assertTrue(list.get(6).getValue().equals(new Integer(5)));
	  assertTrue(list.get(7).getValue().equals(new Integer(1)));
	  assertTrue(list.get(8).getValue().equals(new Integer(4)));
	  assertTrue(list.get(9).getValue().equals(new Integer(3)));
  }
}
