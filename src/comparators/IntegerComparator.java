package comparators;

import java.util.Comparator;

/**
 * An {@link IntegerComparator} compares integers in the natural way.
 *
 */
public class IntegerComparator implements Comparator<Integer> {
  @Override
  public int compare(Integer arg0, Integer arg1) {
	  if (arg0 == null || arg1 == null)
		  throw new NullPointerException();
	  return arg0.compareTo(arg1);
  }
}
