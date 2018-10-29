package top.cellargalaxy.learn;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearnApplicationTests {

	@Autowired
	FileSystem fileSystem;

	@Test
	public void contextLoads() throws IOException {
		Path path = new Path("hdfs://localhost:9000/user/hadoop/myfile");
		FileStatus fileStatuses[] = fileSystem.listStatus(path);
		Arrays.stream(fileStatuses).forEach(fileStatus -> {
			System.out.println("文件：" + fileStatus.getPath().toString());
			if (fileStatus.isFile()) {
				try (InputStream inputStream = fileSystem.open(fileStatus.getPath())) {
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
					String string;
					while ((string = bufferedReader.readLine()) != null) {
						System.out.println(string);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		fileSystem.close();
	}

}
