import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  /** Private Node class */
  private class Node {
    
    public Node left, right;
    
    private Item _item;

    public Node(Item item) {
      if (item == null) {
        throw new NullPointerException();
      }
      
      _item = item;
    }

    public void append(Node next) {
      right = next;
      next.left = this;
    }

    public Item getItem() {
      return _item;
    }
  }

  private Node _head;
  
  private Node _tail;
  
  private int _size;

  /** private iterator class */
  private class DqIterator implements Iterator<Item> {
    private Node current = _head;

    public boolean hasNext() {
      return current != null;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      
      Item key = current.getItem();
      current = current.right;
      return key;
    }

  }

  public Deque() {
    // construct an empty deque// function number 1
    _size = 0;
    _head = null;
    _tail = null;
  }

  public boolean isEmpty() {
    // is the deque empty?// function number 2
    return _size == 0;
  }

  public int size() {
    // return the number of items on the deque// function number 3
    return _size;
  }

  public void addFirst(Item item) {
    // insert the item at the front// function number 4

    Node oldHead = _head;
    Node newHead = new Node(item);
    if (oldHead != null) {
      newHead.append(oldHead);
    } else {
      _tail = newHead;
    }

    _head = newHead;
    _size++;

  }

  public void addLast(Item item) {
    // insert the item at the end// function number 5

    Node newTail = new Node(item);
    Node oldTail = _tail;
    if (oldTail != null) {
      oldTail.append(newTail);
    } else {
      _head = newTail;
    }
    
    _tail = newTail;
    _size++;
  }

  public Item removeFirst() {
    // delete and return the item at the front// function number 6
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    
    _size--;
    Node oldHead = _head;
    _head = oldHead.right;

    if (_head != null) {
      _head.left = null;
    } else {
      _tail = null;
    }
    
    return oldHead.getItem();
  }

  public Item removeLast() {
    // delete and return the item at the end// function number 7

    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    
    _size--;
    Node oldTail = _tail;
    _tail = oldTail.left;
    
    if (_tail != null) {
      _tail.right = null;
    } else {
      _head = null;
    }
    
    return oldTail.getItem();

  }

  public void printAll() {
    // print all the Item objects in the Deque using the iterator// function
    // number 8

    Iterator<Item> iter = iterator();
    StringBuilder sb = new StringBuilder();

    while (iter.hasNext()) {
      sb.append(iter.next()).append(' ');
    }
    sb.setLength(sb.length() - 1);

    StdOut.println(sb);
  }

  public Iterator<Item> iterator() {
    // return an iterator over items in order from front to end
    return new DqIterator();
  }

  public static void main(String[] args) {
    // unit testing

  }
}
