/**
 * Subset.java that takes a command-line integer k; reads in a sequence of 
 * strings from standard input using StdIn.readString(); and prints out exactly 
 * k of them, uniformly at random. Each item from the sequence can be printed 
 * out at most once.
 * @author Li Shen
 */
public class Subset {

	public static void main(String[] args) {
		if (args.length != 1) {
			StdOut.print("args is invalid!");
			return;
		}
		
		int k = Integer.parseInt(args[0]);
		if (k < 0) {
			StdOut.print("args is invalid!");
			return;
		}
		
		RandomizedQueue<String> queue = new RandomizedQueue<String>();
		while (!StdIn.isEmpty()) {
			String str = StdIn.readString();
			queue.enqueue(str);
		}

		if (k > queue.size()) k = queue.size();
		for (String str: queue) {
			if (k == 0) break;
			StdOut.println(str);
			--k;
		}
	}
}
