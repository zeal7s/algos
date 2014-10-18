import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A randomized queue is similar to a stack or queue, except that the item 
 * removed is chosen uniformly at random from items in the data structure.
 * @author Li Shen
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private static final int MIN_CAPACITY = 4;

	private Item[] items;
	
	/**
	 * number of items on the queue
	 */
	private int num;
	
	/**
	 * construct an empty randomized queue
	 */
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		items = (Item[]) new Object[MIN_CAPACITY];
		num = 0;
	}
	
	/**
	 * is the queue empty?
	 * @return true if the queue has no items and false otherwise
	 */
	public boolean isEmpty() {
		return num == 0;
	}
	
	/**
	 * return the number of items on the queue
	 * @return the number of items on the queue
	 */
  public int size() {
  	return num;
  }
  
  /**
   * add the item
   * @param the item to add
   */
  public void enqueue(Item item) {
  	if (item == null) throw new NullPointerException("The item to add is null!");
  	if (num == items.length) resize(num << 1);
  	
  	items[num++] = item;
  }
  
  /**
   * delete and return a random item
   * @return
   */
  public Item dequeue() {
  	if (num == 0) throw new NoSuchElementException("The randomized queue is empty!");
  	
  	int random = StdRandom.uniform(num);
  	swap(--num, random);
  	
  	Item ret = items[num];
  	items[num] = null;
  	if (num < (items.length >> 2)) resize(items.length >> 2);
  	
  	return ret;
  }
  
  /**
   * change the capacity of the queue
   * @param capacity new capacity of the queue
   */
  private void resize(int capacity) {
  	if (capacity < MIN_CAPACITY) return;
  	
  	@SuppressWarnings("unchecked")
		Item[] copy = (Item[])new Object[capacity];
  	System.arraycopy(items, 0, copy, 0, num);
  	items = copy;
  }
  
  /**
   * swap two items in the queue
   * @param i the index of one item
   * @param j the index of the other item
   */
  private void swap(int i, int j) {
  	if (i == j) return;
  	
  	Item temp = items[i];
  	items[i] = items[j];
  	items[j] = temp;
  }
  
  /**
   * return (but do not delete) a random item
   * @return
   */
  public Item sample() {
  	int random = StdRandom.uniform(num);
  	return items[random];
  }
  
  /**
   * print all the Item objects in the Deque using the iterator
   */
  public void printAll() {
  	Iterator<Item> iter = iterator();
  	StringBuilder sb = new StringBuilder();
  	
  	while(iter.hasNext()) {
  		sb.append(iter.next()).append(' ');
  	}
  	sb.setLength(sb.length() - 1);
  	
  	StdOut.println(sb);
  }
	
	@Override
	public Iterator<Item> iterator() {
		return new RandomIterator();
	}
	
	private class RandomIterator implements Iterator<Item> {
		
		private int count;
		
		private int[] randoms;
		
		public RandomIterator() {
			count = num;
			
			randoms = new int[count];
			for (int i = 0; i < count; ++i) randoms[i] = i;
			StdRandom.shuffle(randoms);
		}
		
		@Override
		public boolean hasNext() {
			return count > 0;
		}

		@Override
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			return items[randoms[--count]];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("The method remove() has not been implemented!");
		}
	}
	
	public static void main(String[] args) {
		/* the first line of input is always 1 */
		RandomizedQueue<String> queue = new RandomizedQueue<String>();
		
		while(!StdIn.isEmpty()) {
			switch (StdIn.readInt()) {
			case 2:
				queue.isEmpty();
				break;
			case 3:
				queue.size();
				break;
			case 4:
				queue.enqueue(StdIn.readString());
				break;
			case 5:
				queue.dequeue();
				break;
			case 6:
				queue.sample();
				break;
			default:
				break;
			}
		}
		
		/* the last line is "7" */
		queue.printAll();
	}
}
