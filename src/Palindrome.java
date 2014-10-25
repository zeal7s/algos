public class Palindrome {

  public static void main(String[] args) {
    Deque<Character> deque = new Deque<Character>();
    while (!StdIn.isEmpty()) {
      deque.addLast(StdIn.readChar());

    }

    if (deque.size() % 2 != 0) {
      StdOut.println("false");
      return;
    }
    
    while (!deque.isEmpty()) {
      char left = deque.removeFirst();
      char right = deque.removeLast();
      
      if (!match(left, right)) {
        StdOut.println("false");
        return;
      }
    }

    StdOut.println("true");
  }

  private static boolean match(char left, char right) {
    switch (left) {
    case 'A':
      return right == 'T';
    case 'T':
      return right == 'A';
    case 'C':
      return right == 'G';
    case 'G':
      return right == 'C';
    default:
      return false;
    }
  }
}