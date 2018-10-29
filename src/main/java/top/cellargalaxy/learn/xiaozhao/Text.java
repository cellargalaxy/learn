package top.cellargalaxy.learn. xiaozhao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by cellargalaxy on 18-9-8.
 */
public class Text {
	public static void main(String[] args) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader("/media/cellargalaxy/根/data/data"))) {
			for (int i = 0; i < 1000; i++) {
				System.out.println(reader.readLine());
			}
		}
	}
}
