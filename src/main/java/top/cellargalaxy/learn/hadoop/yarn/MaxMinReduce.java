package top.cellargalaxy.learn.hadoop.yarn;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by cellargalaxy on 18-10-21.
 */
public class MaxMinReduce extends Reducer<Text, LongWritable, Text, LongWritable> {
	@Override
	protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
		long max = Long.MIN_VALUE;
		long min = Long.MAX_VALUE;
		for (LongWritable value : values) {
			long l = value.get();
			if (l > max) {
				max = l;
			}
			if (l < min) {
				min = l;
			}
		}
		context.write(new Text("max"), new LongWritable(max));
		context.write(new Text("min"), new LongWritable(min));
	}
}
