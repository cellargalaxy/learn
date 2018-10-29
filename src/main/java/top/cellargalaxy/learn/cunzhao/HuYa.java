package top.cellargalaxy.learn.cunzhao;

import java.util.*;

/**
 * Created by cellargalaxy on 18-4-21.
 */
public class HuYa {
	
	public static void main(String[] args) {
		t1();
	}
	
	//中文分词正向最大匹配算法
	public static final LinkedList<String> cis;
	
	static {
		cis = new LinkedList<String>();
		cis.add("互动直播平台");
		cis.add("综艺娱乐");
		cis.add("游戏直播");
		cis.add("中国");
		cis.add("直播");
		cis.add("游戏");
		
	}
	
	// {"互动直播平台", "综艺娱乐", "游戏直播", "中国", "直播", "游戏"};
	
	public static final void t1() {
		Scanner scanner = new Scanner(System.in);
//		String string = "虎牙直播中国最大最好的互动直播平台。众多热门高清的游戏直播；上千款游戏，游戏大神齐聚虎牙。星光闪耀，顶尖赛事，综艺娱乐，美女秀场……不一样的精彩直播";//scanner.next();
		String string=scanner.next();
		Set<String> list = new HashSet<String>();
		
		while (string.length() > 0) {
			String s;
			if (string.length() < 6) {
				s = string;
			} else {
				s = string.substring(0, 6);
			}
			
			while (s.length() > 0) {
				if (s.length()==1) {
//					if (!s.equals("。")&&!s.equals("，")&&!s.equals("；")&&!s.equals("…")) {
//						list.add(s);
//					}
					break;
				}
				if (!cis.contains(s)) {
					s=s.substring(0,s.length()-1);
				}else {
					list.add(s);
					break;
				}
			}
			
			string=string.substring(s.length());
		}
		
		
		Iterator<String> iterator = list.iterator();
		if (iterator.hasNext()) {
			System.out.print(iterator.next());
		}
		while (iterator.hasNext()) {
			System.out.print("," + iterator.next());
		}
		System.out.println();
	}
	
	//                                 0  1  2  3  4  5
	public static final int[] jiami = {0, 2, 4, 1, 5, 3};
	
	/**
	 * 1变成2，2变成4，3变成1，4变成5，5变成3
	 */
	public static final void t2() {
		Scanner scanner = new Scanner(System.in);
		String[] strings = scanner.next().split(",");
		System.out.print(jiami[Integer.valueOf(strings[0])]);
		for (int i = 1; i < strings.length; i++) {
			System.out.print("," + jiami[Integer.valueOf(strings[i])]);
		}
		System.out.println();
	}
}
