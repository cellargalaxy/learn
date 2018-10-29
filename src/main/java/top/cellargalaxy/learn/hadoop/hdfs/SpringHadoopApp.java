package top.cellargalaxy.learn.hadoop.hdfs;

import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Created by cellargalaxy on 18-10-25.
 */
@Configuration
public class SpringHadoopApp {


	@Bean
	public FileSystem createFileSystem() throws IOException {
		org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
		configuration.set("fs.defaultFS", "hdfs://localhost:9000");
		FileSystem fileSystem = FileSystem.get(configuration);
		return fileSystem;
	}


}
