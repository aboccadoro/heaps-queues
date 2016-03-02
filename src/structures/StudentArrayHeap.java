package structures;

import java.util.Comparator;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {

	protected StudentArrayHeap(Comparator<P> comparator) {
		super(comparator);
	}

	@Override
	protected int getLeftChildOf(int index) {
		if (index < 0)
			throw new IndexOutOfBoundsException();
		return 2*index + 1;
	}

	@Override
	protected int getRightChildOf(int index) {
		if (index < 0)
			throw new IndexOutOfBoundsException();
		return 2*index + 2;
	}

	@Override
	protected int getParentOf(int index) {
		if (index < 1)
			throw new IndexOutOfBoundsException();
		return (index - 1)/2;
	}

	@Override
	protected void bubbleUp(int index) {
		if (index <= 0)
			return;
		if (comparator.compare(heap.get(index).getPriority(), heap.get((index - 1)/2).getPriority()) > 0) {
			swap(index, (index - 1)/2);
			bubbleUp((index - 1)/2);
		}
	}

	@Override
	protected void bubbleDown(int index) {
		int left = 2*index + 1;
		int right = 2*index + 2;
		if (left >= size())
			return;
		int biggest = left;
		if (left >= size() && comparator.compare(heap.get(right).getPriority(), heap.get(left).getPriority()) > 0)
			biggest = right;
		if (comparator.compare(heap.get(index).getPriority(), heap.get(biggest).getPriority()) < 0) {
			swap(biggest, index);
			bubbleDown(biggest);
		}
	}
}
