package top.cellargalaxy.learn.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by cellargalaxy on 18-10-20.
 */
public class HdfsTest {
	public static void main(String[] args) throws IOException {
		uploadFile();
	}

	/**
	 * 加载配置文件
	 */
	static Configuration configuration = new Configuration();

	static {
		configuration.addResource(new Path("/usr/local/hadoop/hadoop-2.9.1/etc/hadoop/core-site.xml"));
		configuration.addResource(new Path("/usr/local/hadoop/hadoop-2.9.1/etc/hadoop/hdfs-site.xml"));
	}

	/**
	 * 在HDFS上创建一个文件夹
	 *
	 * @throws Exception
	 */
	public static void createDirectoryOnHDFS() throws IOException {
		FileSystem fileSystem = FileSystem.get(configuration);
		Path path = new Path("hdfs://localhost:9000/user/hadoop/myfile");
		fileSystem.mkdirs(path);
		fileSystem.close();//释放资源
		System.out.println("创建文件夹成功");
	}

	/**
	 * 在HDFS上创建一个文件
	 *
	 * @throws Exception
	 */
	public static void createFileOnHDFS() throws IOException {
		FileSystem fileSystem = FileSystem.get(configuration);
		Path path = new Path("hdfs://localhost:9000/user/hadoop/myfile/abc.txt");
		fileSystem.createNewFile(path);
		fileSystem.close();//释放资源
		System.out.println("创建文件成功.....");
	}

	/**
	 * 上传本地文件到HDFS上
	 *
	 * @throws Exception
	 */
	public static void uploadFile() throws IOException {
		FileSystem fileSystem = FileSystem.get(configuration);
		//本地文件
		Path src = new Path("/home/cellargalaxy/无标题文档.txt");
		//HDFS位置
		Path dst = new Path("hdfs://localhost:9000/user/hadoop/myfile");
		fileSystem.copyFromLocalFile(src, dst);
		fileSystem.close();//释放资源
		System.out.println("上传成功........");
	}

	/**
	 * 读取HDFS某个文件夹的所有文件，并打印
	 *
	 * @throws Exception
	 */
	public static void readHDFSListAll() throws IOException {
		FileSystem fileSystem = FileSystem.get(configuration);
		Path path = new Path("hdfs://localhost:9000/user/hadoop/myfile");
		FileStatus fileStatuses[] = fileSystem.listStatus(path);
		Arrays.stream(fileStatuses).forEach(fileStatus -> {
			System.out.println("文件：" + fileStatus.getPath().toString());
			try (InputStream inputStream = fileSystem.open(fileStatus.getPath())) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				String string;
				while ((string = bufferedReader.readLine()) != null) {
					System.out.println(string);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		fileSystem.close();
	}

	/**
	 * 从HDFS上下载文件或文件夹到本地
	 *
	 * @throws Exception
	 */
	public static void downloadFileorDirectoryOnHDFS() throws IOException {
		FileSystem fileSystem = FileSystem.get(configuration);
		Path src = new Path("hdfs://localhost:9000/user/hadoop/myfile/无标题文档.txt");
		Path dst = new Path("/home/cellargalaxy/test.txt");
		fileSystem.copyToLocalFile(src, dst);
		fileSystem.close();//释放资源
		System.out.println("下载文件夹或文件成功.....");
	}

	/**
	 * 重名名一个文件夹或者文件
	 *
	 * @throws IOException
	 */
	public static void renameFileOrDirectoryOnHDFS() throws IOException {
		FileSystem fileSystem = FileSystem.get(configuration);
		Path p1 = new Path("hdfs://localhost:9000/user/hadoop/myfile/abc.txt");
		Path p2 = new Path("hdfs://localhost:9000/user/hadoop/myfile/efg.txt");
		fileSystem.rename(p1, p2);
		fileSystem.close();//释放资源
		System.out.println("重命名文件夹或文件成功.....");
	}

	/**
	 * 在HDFS上删除一个文件
	 *
	 * @throws Exception
	 */
	public static void deleteFileOnHDFS() throws IOException {
		FileSystem fileSystem = FileSystem.get(configuration);
		Path path = new Path("hdfs://localhost:9000/user/hadoop/myfile/efg.txt");
		fileSystem.deleteOnExit(path);
		fileSystem.close();//释放资源
		System.out.println("删除成功.....");
	}

	/**
	 * 在HDFS上删除一个文件夹
	 *
	 * @throws Exception
	 */
	public static void deleteDirectoryOnHDFS() throws IOException {
		FileSystem fileSystem = FileSystem.get(configuration);
		Path path = new Path("hdfs://localhost:9000/user/hadoop/myfile");
		fileSystem.deleteOnExit(path);
		fileSystem.close();//释放资源
		System.out.println("删除文件夹成功.....");
	}
}
