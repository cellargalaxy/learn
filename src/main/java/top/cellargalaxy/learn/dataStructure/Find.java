package top.cellargalaxy.learn.dataStructure;

/**
 * Created by cellargalaxy on 18-9-7.
 */
public class Find {
	public static void main(String[] args) {
		System.out.println(zheban(new int[]{1, 2, 3, 4, 5, 6}, 9));
	}

	/**
	 * 折半查找
	 *
	 * @param ints
	 * @param value
	 * @return
	 */
	public static final boolean zheban(int[] ints, int value) {
		int head = 0;
		int tail = ints.length - 1;
		while (head <= tail) {
			int point = (head + tail) / 2;
			if (ints[point] == value) {
				return true;
			} else if (ints[point] < value) {
				head = point + 1;
			} else {
				tail = point - 1;
			}
		}
		return false;
	}
}
