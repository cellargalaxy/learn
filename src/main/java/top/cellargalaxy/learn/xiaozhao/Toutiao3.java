package top.cellargalaxy.learn. xiaozhao;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by cellargalaxy on 18-9-9.
 */
public class Toutiao3 {
	static int max = 255;
	public static void main(String[] args) {
		t3();
	}

	public static void t5() {
		Scanner scanner = new Scanner(System.in);
		int userCount = Integer.valueOf(scanner.nextLine());
		int guanzhuCount = Integer.valueOf(scanner.nextLine());
		User[] users = new User[userCount];
		for (int i = 0; i < users.length; i++) {
			users[i] = new User(i);
		}
		String[] strings = scanner.nextLine().split(" ");
		for (int i = 0; i < guanzhuCount; i++) {
			int a = Integer.valueOf(strings[i * 2]) - 1;
			int b = Integer.valueOf(strings[(i * 2) + 1]) - 1;
			users[b].guanzhuUsers.add(users[a]);
		}


		int hongrenCount = 0;
		for (User beiguanzhuUser : users) {
			Set<User> set = t55(beiguanzhuUser);
			beiguanzhuUser.guanzhuUsers.addAll(set);
			if (users.length == 1 && set.size() == 1) {
				hongrenCount++;
				continue;
			}
			if (set.contains(beiguanzhuUser)) {
				if (set.size() == users.length) {
					hongrenCount++;
				}
			} else {
				if (set.size() == users.length - 1) {
					hongrenCount++;
				}
			}
		}


		System.out.println(hongrenCount);
	}

	private static Set<User> t55(User user) {
		Set<User> set = new HashSet<>();
		for (User guanzhuUser : user.guanzhuUsers) {
			t55(guanzhuUser, set);
		}
		return set;
	}

	private static void t55(User user, Set<User> set) {
		set.add(user);
		for (User guanzhuUser : user.guanzhuUsers) {
			if (!set.contains(guanzhuUser)) {
				t55(guanzhuUser, set);
			}
		}
	}

	static class User {
		final int i;
		final Set<User> guanzhuUsers = new HashSet<>();

		public User(int i) {
			this.i = i;
		}

		@Override
		public String toString() {
			return "{" + (i + 1) + "}";
		}
	}


	public static void t1() {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		byte[] bytes = string.getBytes();
		int maxLen = -1;

		byte[] map = new byte[Byte.MAX_VALUE - Byte.MIN_VALUE];
		int len = 0;
		for (int i = 0; i < bytes.length; i++) {
			if (map[bytes[i] - Byte.MIN_VALUE] == 0) {
				map[bytes[i] - Byte.MIN_VALUE] = 1;
				len++;
			} else {
				if (len > maxLen) {
					maxLen = len;
				}
				map = new byte[Byte.MAX_VALUE - Byte.MIN_VALUE];
				len = 0;
			}
		}
		if (len > maxLen) {
			maxLen = len;
		}

		System.out.println(maxLen);
	}


	public static void t3() {
		Scanner scanner = new Scanner(System.in);
		String ip = scanner.nextLine();
		System.out.println(fen4fa(ip));
	}

	private static int fen4fa(String string) {
		int fenfa = 0;
		if (string.length() >= 2) {
			fenfa += fen3fa(string.substring(1, string.length()));
		}
		if (string.length() >= 3) {
			fenfa += fen3fa(string.substring(2, string.length()));
		}
		if (string.length() >= 4) {
			int i1 = Integer.valueOf(string.substring(0, 3));
			if (i1 < max) {
				fenfa += fen3fa(string.substring(3, string.length()));
			}
		}
		return fenfa;
	}

	private static int fen3fa(String string) {
		int fenfa = 0;
		if (string.length() >= 2) {
			fenfa += fen2fa(string.substring(1, string.length()));
		}
		if (string.length() >= 3) {
			fenfa += fen2fa(string.substring(2, string.length()));
		}
		if (string.length() >= 4) {
			int i1 = Integer.valueOf(string.substring(0, 3));
			if (i1 < max) {
				fenfa += fen2fa(string.substring(3, string.length()));
			}
		}
		return fenfa;
	}

	private static int fen2fa(String string) {
		if (string.length() == 2) {
			return 1;
		}
		if (string.length() == 3) {
			return 2;
		}
		if (string.length() == 4) {
			int fenfa = 1;
			int i1 = Integer.valueOf(string.substring(0, 3));
			if (i1 < max) {
				fenfa++;
			}
			i1 = Integer.valueOf(string.substring(string.length() - 3, string.length()));
			if (i1 < max) {
				fenfa++;
			}
			return fenfa;
		}
		if (string.length() == 5) {
			int fenfa = 0;
			int i1 = Integer.valueOf(string.substring(0, 3));
			int i2 = Integer.valueOf(string.substring(3, string.length()));
			if (i1 < max && i2 < max) {
				fenfa++;
			}
			i1 = Integer.valueOf(string.substring(0, 2));
			i2 = Integer.valueOf(string.substring(2, string.length()));
			if (i1 < max && i2 < max) {
				fenfa++;
			}
			return fenfa;
		}
		if (string.length() == 6) {
			int i1 = Integer.valueOf(string.substring(0, 3));
			int i2 = Integer.valueOf(string.substring(3, string.length()));
			if (i1 < max && i2 < max) {
				return 1;
			} else {
				return 0;
			}
		}
		return 0;
	}

	public static void t2() {
		Scanner scanner = new Scanner(System.in);
		int m = Integer.valueOf(scanner.nextLine());
		Zhu[][] zhuss = new Zhu[m][m];
		for (int i = 0; i < zhuss.length; i++) {
			String[] strings = scanner.nextLine().split(" ");
			for (int j = 0; j < strings.length; j++) {
				int ex = Integer.valueOf(strings[j]);
				if (ex == 1) {
					zhuss[i][j] = new Zhu(i, j);
				}
			}
		}

		int bumen = 0;
		for (int i = 0; i < zhuss.length; i++) {
			for (int j = 0; j < zhuss[i].length; j++) {
				if (zhuss[i][j] != null && zhuss[i][j].bumen == 0) {
					bumen++;
					LinkedList<Zhu> list = new LinkedList<>();
					list.add(zhuss[i][j]);

					Zhu zhu;
					while ((zhu = list.poll()) != null) {
						t22(zhu, bumen, zhuss, list);
					}

				}
			}
		}

		System.out.println(bumen);

	}

	private static void t22(Zhu zhu, int bumen, Zhu[][] zhuss, LinkedList<Zhu> list) {
		zhu.bumen = bumen;
		t222(zhu.i - 1, zhu.j, zhuss, list);
		t222(zhu.i + 1, zhu.j, zhuss, list);
		t222(zhu.i, zhu.j - 1, zhuss, list);
		t222(zhu.i, zhu.j + 1, zhuss, list);
	}

	private static void t222(int i, int j, Zhu[][] zhuss, LinkedList<Zhu> list) {
		if (i > -1 && i < zhuss.length) {
			if (j > -1 && j < zhuss[i].length) {
				if (zhuss[i][j] != null && zhuss[i][j].bumen == 0) {
					list.add(zhuss[i][j]);
				}
			}
		}
	}

	static class Zhu {
		final int i;
		final int j;
		int bumen;

		public Zhu(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
