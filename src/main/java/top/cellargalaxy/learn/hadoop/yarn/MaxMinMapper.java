package top.cellargalaxy.learn.hadoop.yarn;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by cellargalaxy on 18-10-21.
 */
public class MaxMinMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		context.write(new Text("key"), new LongWritable(Long.valueOf(value.toString())));
	}
}
