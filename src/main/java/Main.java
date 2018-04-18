import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by cellargalaxy on 18-3-2.
 */
public class Main {
	public static void main(String[] args) {
		
		List<Integer> order = new ArrayList<Integer>();//a1,a2,...,an
		Map<String, List<Integer>> boms = new HashMap<String, List<Integer>>();
		
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		
		Integer n = Integer.parseInt(line.split(",")[0]);//n,m
		Integer m = Integer.parseInt(line.split(",")[1]);
		
		line = in.nextLine();
		String[] itemCnt = line.split(",");
		for (int i = 0; i < n; i++) {
			order.add(Integer.parseInt(itemCnt[i]));
		}
		
		for (int i = 0; i < m; i++) {
			line = in.nextLine();
			String[] bomInput = line.split(",");
			List<Integer> bomDetail = new ArrayList<Integer>();
			
			for (int j = 1; j <= n; j++) {
				bomDetail.add(Integer.parseInt(bomInput[j]));
			}
			boms.put(bomInput[0], bomDetail);
		}
		in.close();
		
		Map<String, Integer> res = resolve(order, boms);
		
		System.out.println("match result:");
		for (String key : res.keySet()) {
			System.out.println(key + "*" + res.get(key));
		}
	}
	
	// write your code here
	public static Map<String, Integer> resolve(List<Integer> order, Map<String, List<Integer>> boms) {
		int[] results = new int[boms.size()];
		int[] sums=new int[order.size()];
		int[][] sumss = new int[boms.size()][order.size()];
		int i = 0;
		for (Map.Entry<String, List<Integer>> entry : boms.entrySet()) {
			List<Integer> bom = entry.getValue();
			int j = 0;
			for (Integer integer : bom) {
				sumss[i][j] = integer * results[i];
				j++;
			}
			i++;
		}
		for (int j = 0; i < sums.length; i++) {
			for (i = 0; i < sumss.length; i++) {
				sums[j]+=sumss[i][j];
			}
		}
		return null;
	}
}
