import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Slowsort {
	public static final int LENGTH = 1000;
	public static final int MAX = 1000;

	public static void main(String[] args) {
		Random rand = new Random();
		List<Integer> toSort;
//		print(toSort);
		double ratio = 0;
		double buff = 1;
		for (int j = 50; j < LENGTH; j+=50) {
			toSort = createList(rand, j);
			long t = System.currentTimeMillis();
			slowsort(toSort, 0, toSort.size() - 1);
			double elapsed = (System.currentTimeMillis() - t) / 1000.0;
			double ops = elapsed*1000;
			String unit = "Seconds";
			if (j != 2 && buff != 0) {
				buff = buff /1.0;
				ratio = ops / buff;
			}
			if (elapsed > 60) {
				elapsed = elapsed / 60;
				unit = "Minutes";
			}
			if (elapsed > 60) {
				elapsed = elapsed / 60;
				unit = "Hours";
			}
//			print(toSort);
//			if (j % 50 == 0) {
//				System.out.printf("%f %s for %d numbers\n", elapsed / 1.0, unit, j);
//			}
			System.out.printf("%f %s for %d numbers; Ratio = %f\n", elapsed / 1.0, unit, j, ratio);
//			toSort = addOne(rand, toSort);
			if (ops == 0) {
				buff = 1;
			} else {
				buff = ops;
			}
		}
	}

	public static List<Integer> createList(Random rand, int length) {
		List<Integer> numList = new ArrayList<Integer>();
		for (int i = 0; i < length; i++) {
			numList.add(rand.nextInt(MAX));
		}
		return numList;
	}

	public static List<Integer> addOne(Random rand, List<Integer> list) {
		list.add(rand.nextInt(MAX));
		Collections.shuffle(list);
		return list;
	}

	public static void slowsort(List<Integer> list, int i, int j) {
		if (i >= j) {
			return;
		}
		int m = (i + j) / 2;
		slowsort(list, i, m);
		slowsort(list, m + 1, j);
		if (list.get(j) < list.get(m)) {
			int temp = list.get(j);
			list.set(j, list.get(m));
			list.set(m, temp);
		}
		slowsort(list, i, j - 1);
	}

	private static void print(List<Integer> numList) {
		for (int x : numList) {
			System.out.printf("%d ", x);
		}
		System.out.println();
	}

}
