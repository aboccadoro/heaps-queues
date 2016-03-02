package comparators;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class IntegerComparatorTest {

  IntegerComparator comparator;
	
  @Before
  public void setup() {
    comparator = new IntegerComparator();
  }
  
  @Test (timeout = 100, expected = NullPointerException.class)
  public void testException() {
	  comparator.compare(null, null);
  }

  @Test (timeout = 100)
  public void testCompare() {
	  assertTrue(comparator.compare(new Integer(4), new Integer(5)) < 0);
	  assertTrue(comparator.compare(new Integer(2), new Integer(8)) < 0);
	  assertTrue(comparator.compare(new Integer(4), new Integer(2)) > 0);
	  assertTrue(comparator.compare(new Integer(15), new Integer(10)) > 0);
	  assertTrue(comparator.compare(new Integer(5), new Integer(5)) == 0);
  }
}
