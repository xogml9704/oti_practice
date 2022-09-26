package practice;

import java.util.Comparator;

public class MainComparator implements Comparator<Main> {
	@Override
	public int compare(Main o1, Main o2) {
		if(o1.price < o2.price) return -1;
		else if (o1.price == o2.price) return 0;
		else return 1;
	}
}
