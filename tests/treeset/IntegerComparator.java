package treeset;

import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer>{

	public int compare(Integer o1, Integer o2) {
		return (o1 - o2) * -1;
	}
}
