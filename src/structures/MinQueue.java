package structures;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import comparators.ReverseIntegerComparator;

public class MinQueue<V> implements PriorityQueue<Integer, V> {
	private Comparator<Integer> comparator = new ReverseIntegerComparator();
	private AbstractArrayHeap<Integer, V> heap = new StudentArrayHeap<Integer, V>(comparator);

	@Override
	public PriorityQueue<Integer, V> enqueue(Integer priority, V value) {
		if (priority == null || value == null)
			throw new NullPointerException();
		heap.add(priority, value);
		return this;
	}

	@Override
	public V dequeue() {
		if (isEmpty())
			throw new IllegalStateException();
		return heap.remove();
	}

	@Override
	public V peek() {
		if (isEmpty())
			throw new IllegalStateException();
		return heap.peek();
	}

	@Override
	public Iterator<Entry<Integer, V>> iterator() {
		List<Entry<Integer, V>> list = heap.asList();
		return list.iterator();
	}

	@Override
	public Comparator<Integer> getComparator() {
		return comparator;
	}

	@Override
	public int size() {
		return heap.size();
	}

	@Override
	public boolean isEmpty() {
		return heap.isEmpty();
	}
}

