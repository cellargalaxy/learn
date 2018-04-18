import java.util.*;

/**
 * Created by cellargalaxy on 18-3-2.
 */
public class Test {
	public static void main(String[] args) {
		for (char c : "ABCZabcz".toCharArray()) {
			System.out.println((int) c);
		}
	}
	
	public int TreeDepth(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.add(root);
		int deep=-1;
		int d;
		while ((d = TreeDepth1(stack)) == -1&&(d = TreeDepth2(stack)) == -1) {
		
		}
		if (d==0) {
			return deep;
		}
		if (d>deep) {
			deep=d;
		}
		return deep;
	}
	
	public int TreeDepth1(Stack<TreeNode> stack) {
		TreeNode node = stack.peek();
		if (node == null) {
			return 0;
		}
		TreeNode left = node.left;
		TreeNode right = node.right;
		if (left == null && right == null) {
			return stack.size() + 1;
		}
		stack.add(node);
		stack.add(left);
		return -1;
	}
	
	public int TreeDepth2(Stack<TreeNode> stack) {
		TreeNode node = stack.peek();
		if (node == null) {
			return 0;
		}
		TreeNode left = node.left;
		TreeNode right = node.right;
		if (left == null && right == null) {
			return stack.size() + 1;
		}
		stack.add(node);
		stack.add(right);
		return -1;
	}
	
	public int GetNumberOfK(int[] array, int k) {
		int count = 0;
		boolean b = false;
		for (int i : array) {
			if (i == k) {
				count++;
			}
			if (i != k && b) {
				break;
			}
		}
		return count;
	}
	
	public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
		if (pHead1 == null || pHead2 == null) {
			return null;
		}
		int len1 = 1;
		int len2 = 1;
		ListNode node = pHead1;
		while ((node = node.next) != null) {
			len1++;
		}
		node = pHead2;
		while ((node = node.next) != null) {
			len2++;
		}
		if (len1 > len2) {
			for (int i = 0; i < len1 - len2; i++) {
				pHead1 = pHead1.next;
			}
		} else if (len2 > len1) {
			for (int i = 0; i < len2 - len1; i++) {
				pHead2 = pHead2.next;
			}
		}
		ListNode node1 = pHead1;
		ListNode node2 = pHead2;
		while (node1 != null && node1 != node2) {
			node1 = node1.next;
			node2 = node2.next;
		}
		return node1;
	}
	
	public int InversePairs(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] > array[j]) {
					count++;
				}
			}
		}
		return count % 1000000007;
	}
	
	public int FirstNotRepeatingChar(String str) {
		if (str == null || str.length() == 0) {
			return -1;
		}
		int[] indexs = new int[52 + 7];
		for (int i = 0; i < indexs.length; i++) {
			indexs[i] = -1;
		}
		int[] counts = new int[52 + 7];
		char[] chars = str.toCharArray();
		char c = 'A';
		for (int i = 0; i < chars.length; i++) {
			counts[chars[i] - c]++;
			if (indexs[chars[i] - c] == -1) {
				indexs[chars[i] - c] = i;
			}
		}
		int index = Integer.MAX_VALUE;
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] == 1) {
				if (index > indexs[i]) {
					index = indexs[i];
				}
			}
		}
		return index;
	}
	
	public String PrintMinNumber(int[] numbers) {
		if (numbers == null || numbers.length == 0) {
			return "";
		}
		if (numbers.length == 1) {
			return numbers[0] + "";
		}
		String[] strings = new String[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			strings[i] = String.valueOf(numbers[i]);
		}
		Arrays.sort(strings, new Comparator<String>() {
			public int compare(String o1, String o2) {
				String s1 = o1 + o2;
				String s2 = o2 + o1;
				if (Integer.valueOf(s1) > Integer.valueOf(s2)) {
					return 1;
				} else if (Integer.valueOf(s1) < Integer.valueOf(s2)) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		StringBuilder stringBuilder = new StringBuilder();
		for (String string : strings) {
			stringBuilder.append(string);
		}
		return stringBuilder.toString();
	}
	
	public static int NumberOf1Between1AndN_Solution(int n) {
		int count = 0;
		for (int i = 1; i <= n; i++) {
			int in = i;
			while (in > 0) {
				int shi = NumberOf1Between1AndN_Solution1(in);
				if (in < shi * 2) {
					count++;
				}
				in = in % shi;
			}
		}
		return count;
	}
	
	public static int NumberOf1Between1AndN_Solution1(int n) {
		int shi = 1;
		while (shi * 10 <= n) {
			shi *= 10;
		}
		return shi;
	}
	
	public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
		ArrayList<Integer> answer = new ArrayList<Integer>();
		if (k == 0 || k > input.length) {
			return answer;
		}
		int min = Integer.MAX_VALUE;
		for (int i : input) {
			if (i < min) {
				min = i;
			}
		}
		answer.add(min);
		if (k == 1) {
			return answer;
		}
		int point;
		for (int i = 0; i < k - 1; i++) {
			point = Integer.MAX_VALUE;
			for (int j : input) {
				if (j > min && j < point) {
					point = j;
				}
			}
			answer.add(point);
			min = point;
		}
		return answer;
	}
	
	public int MoreThanHalfNum_Solution(int[] array) {
		Map<Integer, Integer> count = new HashMap<Integer, Integer>();
		for (int i : array) {
			Integer integer = count.get(i);
			if (integer == null) {
				count.put(i, 1);
			} else {
				count.put(i, integer + 1);
			}
		}
		for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
			if (entry.getValue() > array.length / 2) {
				return entry.getKey();
			}
		}
		return 0;
	}
	
	public static ArrayList<String> Permutation(String str) {
		if (str == null || str.length() == 0) {
			return new ArrayList<String>();
		}
		char[] chars = str.toCharArray();
		int count = 1;
		for (int i = 0; i < chars.length; i++) {
			count *= (i + 1);
		}
		Set<String> set = new HashSet<String>(count);
		int[][] ints = new int[count][];
		int[] is = new int[chars.length];
		for (int i = 0; i < is.length; i++) {
			is[i] = 1;
		}
		ints[0] = is;
		for (int i = 1; i < ints.length; i++) {
			ints[i] = Permutation(ints[i - 1]);
		}
		for (int[] anInt : ints) {
			LinkedList<Character> linkedList = new LinkedList<Character>();
			for (char aChar : chars) {
				linkedList.add(aChar);
			}
			String string = "";
			System.out.println(Arrays.toString(anInt));
			for (int i = anInt.length - 1; i > -1; i--) {
				string += linkedList.get(anInt[i] - 1);
				linkedList.remove(anInt[i] - 1);
			}
			set.add(string);
		}
		ArrayList<String> answer = new ArrayList<String>();
		for (String string : set) {
			answer.add(string);
		}
		return answer;
	}
	
	public static int[] Permutation(int[] ints) {
		int[] ints1 = ints.clone();
		Permutation(ints1, ints1.length - 1);
		return ints1;
	}
	
	public static void Permutation(int[] ints, int index) {
		ints[index]++;
		if (ints[index] > index + 1) {
			ints[index] = 1;
			Permutation(ints, index - 1);
		}
	}
	
	public static TreeNode Convert(TreeNode pRootOfTree) {
		if (pRootOfTree == null || (pRootOfTree.left == null && pRootOfTree.right == null)) {
			return pRootOfTree;
		}
		TreeNode left = Convert(pRootOfTree.left);
		while (left != null && left.right != null) {
			left = left.right;
		}
		TreeNode rigth = Convert(pRootOfTree.right);
		while (rigth != null && rigth.left != null) {
			rigth = rigth.left;
		}
		left.right = pRootOfTree;
		pRootOfTree.right = rigth;
		rigth.left = pRootOfTree;
		pRootOfTree.left = left;
		while (left != null && left.left != null) {
			left = left.left;
		}
		return left != null ? left : pRootOfTree;
	}
	
	public static ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
		final TreeNode mark = new TreeNode(-1);
		ArrayList<ArrayList<Integer>> answer = new ArrayList<ArrayList<Integer>>();
		if (root == null) {
			return answer;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.add(root);
		while (stack.size() > 0) {
			while (true) {
				TreeNode node = stack.peek();
				if (node.left != null && node.left != mark) {
					stack.add(node.left);
				} else if (node.right != null && node.right != mark) {
					stack.add(node.right);
				} else {
					break;
				}
			}
			if (stack.peek().left == null && stack.peek().right == null) {
				int count = 0;
				for (TreeNode treeNode : stack) {
					count += treeNode.val;
				}
				if (count == target) {
					ArrayList<Integer> arrayList = new ArrayList<Integer>(stack.size());
					for (TreeNode treeNode : stack) {
						arrayList.add(treeNode.val);
					}
					answer.add(arrayList);
				}
			}
			stack.pop();
			if (stack.size() == 0) {
				break;
			}
			TreeNode node = stack.peek();
			if (node.left != null && node.left != mark) {
				node.left = mark;
			} else if (node.right != null && node.right != mark) {
				node.right = mark;
			}
		}
		return answer;
	}
	
	public static boolean VerifySquenceOfBST(int[] sequence) {
		if (sequence == null || sequence.length == 0) {
			return false;
		}
		if (sequence.length < 3) {
			return true;
		}
		return VerifySquenceOfBST1(sequence, 0, sequence.length);
	}
	
	public static boolean VerifySquenceOfBST1(int[] sequence, int start, int end) {
		if (start >= end - 2) {
			return true;
		}
		int index = VerifySquenceOfBST2(sequence, start, end);
		if (index == end - 1) {
			return true;
		}
		if (index == -1) {
			return false;
		}
		return VerifySquenceOfBST1(sequence, start, index) && VerifySquenceOfBST1(sequence, index, end - 1);
	}
	
	public static int VerifySquenceOfBST2(int[] sequence, int start, int end) {
		int root = sequence[end - 1];
		int index = -1;
		for (int i = start; i < end; i++) {
			if (index == -1 && sequence[i] >= root) {
				index = i;
			}
			if (index != -1 && sequence[i] < root) {
				return -1;
			}
		}
		return index;
	}
	
	public static ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
		ArrayList<Integer> print = new ArrayList<Integer>();
		if (root == null) {
			return print;
		}
		LinkedList<TreeNode> tmps = new LinkedList<TreeNode>();
		tmps.add(root);
		while (tmps.size() > 0) {
			tmps = PrintFromTopToBottom(print, tmps);
		}
		return print;
	}
	
	public static LinkedList<TreeNode> PrintFromTopToBottom(ArrayList<Integer> print, LinkedList<TreeNode> tmps) {
		LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
		for (TreeNode tmp : tmps) {
			print.add(tmp.val);
			if (tmp.left != null) nodes.add(tmp.left);
			if (tmp.right != null) nodes.add(tmp.right);
		}
		return nodes;
	}
	
	public static boolean IsPopOrder(int[] pushA, int[] popA) {
		Stack<Integer> stack = new Stack<Integer>();
		int j = 0;
		for (int i = 0; i < pushA.length; i++) {
			if (pushA[i] == popA[j]) {
				j++;
				int pop;
				while (stack.size() > 0) {
					if ((pop = stack.pop()) == popA[j]) {
						j++;
					} else {
						stack.push(pop);
						break;
					}
				}
			} else {
				stack.push(pushA[i]);
			}
		}
		System.out.println(j + 1);
		System.out.println(popA.length);
		return j == popA.length;
	}
	
	public static ArrayList<Integer> printMatrix(int[][] matrix) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>(matrix.length * matrix.length);
		if (matrix.length == 1) {
			for (int i : matrix[0]) {
				arrayList.add(i);
			}
			return arrayList;
		}
		if (matrix[0].length == 1) {
			for (int[] ints : matrix) {
				arrayList.add(ints[0]);
			}
			return arrayList;
		}
		int count;
		if (matrix.length > matrix[0].length) {
			count = matrix.length % 2 == 0 ? matrix.length / 2 : (matrix.length / 2) + 1;
		} else {
			count = matrix[0].length % 2 == 0 ? matrix[0].length / 2 : (matrix[0].length / 2) + 1;
		}
		for (int i = 0; i < count; i++) {
			printMatrixRow(arrayList, matrix, i);
			printMatrixCol(arrayList, matrix, matrix[0].length - i - 1);
			printMatrixRow(arrayList, matrix, matrix.length - i - 1);
			printMatrixCol(arrayList, matrix, i);
		}
		return arrayList;
	}
	
	private static void printMatrixRow(ArrayList<Integer> arrayList, int[][] matrix, int row) {
		int pian = matrix.length - row;
		if (row < pian) {
			pian = row;
			for (int i = pian; i < matrix[row].length - pian - 1; i++) {
				arrayList.add(matrix[row][i]);
			}
		} else {
			for (int i = matrix[row].length - pian - 1; i > pian; i--) {
				arrayList.add(matrix[row][i]);
			}
		}
	}
	
	private static void printMatrixCol(ArrayList<Integer> arrayList, int[][] matrix, int col) {
		int pian = matrix[col].length - col;
		if (col < pian) {
			pian = col;
			for (int i = matrix.length - pian - 1; i > pian; i--) {
				arrayList.add(matrix[i][col]);
			}
		} else {
			for (int i = pian; i < matrix.length - pian - 1; i++) {
				arrayList.add(matrix[i][col]);
			}
		}
	}
	
	public static void Mirror(TreeNode root) {
		if (root == null) {
			return;
		}
		TreeNode left = root.left;
		TreeNode rigth = root.right;
		root.left = rigth;
		root.right = left;
		Mirror(left);
		Mirror(rigth);
	}
	
	public static boolean HasSubtree(TreeNode root1, TreeNode root2) {
		if (root1 == null || root2 == null) {
			return false;
		}
		return (root1.val == root2.val && isSubTree(root1, root2)) ||
				HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
	}
	
	private static boolean isSubTree(TreeNode root1, TreeNode root2) {
		return root1 == root2 || (root1.val == root2.val && isSubTree(root1.left, root2.left) && isSubTree(root1.right, root2.right));
	}
	
	public static ListNode Merge(ListNode list1, ListNode list2) {
		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}
		ListNode head;
		if (list1.val <= list2.val) {
			head = list1;
			list1 = list1.next;
		} else {
			head = list2;
			list2 = list2.next;
		}
		ListNode node = head;
		while (true) {
			if (list1 != null && list2 != null) {
				if (list1.val <= list2.val) {
					node.next = list1;
					node = node.next;
					list1 = list1.next;
				} else {
					node.next = list2;
					node = node.next;
					list2 = list2.next;
				}
			} else if (list1 != null && list2 == null) {
				node.next = list1;
				node = node.next;
				list1 = list1.next;
			} else if (list1 == null && list2 != null) {
				node.next = list2;
				node = node.next;
				list2 = list2.next;
			} else {
				break;
			}
		}
		return head;
	}
	
	public static ListNode ReverseList(ListNode head) {
		if (head == null) {
			return null;
		}
		if (head.next == null) {
			return head;
		}
		ListNode node1 = head;
		ListNode node2 = node1.next;
		node1.next = null;
		while (node2.next != null) {
			ListNode node = node2.next;
			node2.next = node1;
			node1 = node2;
			node2 = node;
		}
		node2.next = node1;
		return node2;
	}
	
	public static ListNode FindKthToTail(ListNode head, int k) {
		if (head == null || k == 0) {
			return null;
		}
		int len1 = 1;
		ListNode indexNode = head;
		while (indexNode.next != null) {
			if (len1 >= k) {
				break;
			}
			indexNode = indexNode.next;
			len1++;
		}
		if (len1 < k) {
			return null;
		}
		ListNode kNode = head;
		while (indexNode.next != null) {
			indexNode = indexNode.next;
			kNode = kNode.next;
		}
		return kNode;
	}
	
	public static void reOrderArray(int[] array) {
		for (int i = 1; i < array.length; i++) {
			if (array[i] % 2 == 1) {
				for (int j = i; j > 0; j--) {
					if (array[j - 1] % 2 == 0) {
						int tmp = array[j - 1];
						array[j - 1] = array[j];
						array[j] = tmp;
					} else {
						break;
					}
				}
			}
		}
//		System.out.println(Arrays.toString(array));
//		int[] ints = new int[array.length];
//		int i = 0;
//		for (int a : array) {
//			if (a % 2 == 1) {
//				ints[i] = a;
//				i++;
//			}
//		}
//		for (int a : array) {
//			if (a % 2 == 0) {
//				ints[i] = a;
//				i++;
//			}
//		}
//		for (int i1 = 0; i1 < array.length; i1++) {
//			array[i1] = ints[i1];
//		}
	}
	
	public double Power(double base, int exponent) {
		if (exponent == 0) {
			return 1;
		}
		double d = 1;
		for (int i = 0; i < Math.abs(exponent); i++) {
			d *= base;
		}
		if (exponent < 0) {
			return 1 / d;
		}
		return d;
	}
	
	public int NumberOf1(int n) {
		String string = Integer.toBinaryString(n);
		int count = 0;
		for (char c : string.toCharArray()) {
			if (c == '1') {
				count++;
			}
		}
		return count;
	}
	
	public int RectCover(int target) {
		if (target == 0) {
			return 0;
		}
		if (target == 1) {
			return 1;
		}
		if (target == 2) {
			return 2;
		}
		return RectCover(target - 1) + RectCover(target - 2);
	}
	
	public int JumpFloorII(int target) {
		if (target == 0) {
			return 0;
		}
		if (target == 1) {
			return 1;
		}
		return 2 * JumpFloorII(target - 1);
	}
	
	public int Fibonacci(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1 || n == 2) {
			return 1;
		}
		int n1 = 1;
		int n2 = 1;
		for (int i = 0; i < n - 2; i++) {
			int sum = n1 + n2;
			n1 = n2;
			n2 = sum;
		}
		return n2;
	}
	
	public int minNumberInRotateArray(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int pre = array.length - 2;
		int i = array.length - 1;
		while (i > 0) {
			if (array[pre] > array[i]) {
				return array[i];
			}
			pre--;
			i--;
		}
		return array[0];
	}
	
	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();
	
	public void push(int node) {
		stack2.clear();
		for (Integer integer : stack1) {
			stack2.push(integer);
		}
		stack1.clear();
		stack1.push(node);
		for (Integer integer : stack2) {
			stack1.push(integer);
		}
		stack2.clear();
	}
	
	public int pop() {
		return stack1.pop();
	}
	
	public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		return reConstructBinaryTree(pre, 0, pre.length, in, 0, in.length);
	}
	
	private static TreeNode reConstructBinaryTree(int[] pre, int start1, int end1, int[] in, int start2, int end2) {
		if (start1 >= pre.length || start1 > end1 || start2 > end2) {
			return null;
		}
		int inIndex = start2;
		for (; inIndex < end2; inIndex++) {
			if (in[inIndex] == pre[start1]) {
				break;
			}
		}
		TreeNode root = new TreeNode(pre[start1]);
		root.left = reConstructBinaryTree(pre, start1 + 1, start1 + inIndex - start2 + 1, in, start2, inIndex);
		root.right = reConstructBinaryTree(pre, start1 + inIndex - start2 + 1, end1, in, inIndex + 1, end2);
		return root;
	}
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int x) {
			val = x;
		}
		
		@Override
		public String toString() {
			return "TreeNode{" +
					"val=" + val +
					'}';
		}
	}
	
	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		while (listNode != null) {
			linkedList.addFirst(listNode.val);
			listNode = listNode.next;
		}
		ArrayList<Integer> arrayList = new ArrayList<Integer>(linkedList.size());
		for (Integer integer : linkedList) {
			arrayList.add(integer);
		}
		return arrayList;
	}
	
	static class ListNode {
		int val;
		ListNode next = null;
		
		ListNode(int val) {
			this.val = val;
		}
		
		@Override
		public String toString() {
			return "ListNode{" +
					"val=" + val +
					'}';
		}
	}
	
	public String replaceSpace(StringBuffer str) {
		if (str == null) {
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ' ') {
				stringBuilder.append(str.charAt(i));
			} else {
				stringBuilder.append("%20");
			}
		}
		return stringBuilder.toString();
	}
	
	public boolean find(int target, int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = array[i].length - 1; j > -1; j--) {
				if (array[i][j] == target) {
					return true;
				} else if (array[i][j] < target) {
					break;
				}
			}
		}
		return false;
	}
}
