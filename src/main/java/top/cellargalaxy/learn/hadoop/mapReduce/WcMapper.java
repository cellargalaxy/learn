package top.cellargalaxy.learn.hadoop.mapReduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by cellargalaxy on 18-10-21.
 */
public class WcMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String string = value.toString();
		String[] strings = string.split(" ");
		for (String s : strings) {
			context.write(new Text(s), new LongWritable(1));
		}
	}
}
