package top.cellargalaxy.learn. netty;

import java.util.Date;

/**
 * Created by cellargalaxy on 18-5-9.
 */
public class Time {
	private final long time;

	public Time(long time) {
		this.time = time;
	}

	public Date createDate() {
		return new Date(time);
	}

	public long getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "Time{" +
				"time=" + time +
				'}';
	}
}
