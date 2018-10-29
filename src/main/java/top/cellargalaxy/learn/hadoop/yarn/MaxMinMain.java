package top.cellargalaxy.learn.hadoop.yarn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by cellargalaxy on 18-10-21.
 */
public class MaxMinMain {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Job job=new Job(new Configuration());
		job.setJarByClass(MaxMinMain.class);
		job.setMapperClass(MaxMinMapper.class);
		job.setReducerClass(MaxMinReduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);

		FileInputFormat.setInputPaths(job, new Path("hdfs://localhost:9000/user/hadoop/myfile/无标题文档.txt"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:9000/user/hadoop/myfile/mapReduce"));

		job.waitForCompletion(true);
	}
}
