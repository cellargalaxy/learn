package top.cellargalaxy.learn. neuro;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by cellargalaxy on 18-7-8.
 */
public class AutoNeuro2 {
	public static final int TRAIN_TIMES = 2000;
	public static final int CENG_COUTN = 100;
	private final int hideLen;
	private final int outputLen;
	private BpNeuro bpNeuro;
	private final LinkedList<double[][]> weightList;
	private final LinkedList<double[]> cList;

	public AutoNeuro2(int inputLen, int hideLen, int outputLen) {
		this.hideLen = hideLen;
		this.outputLen = outputLen;
		bpNeuro = new BpNeuro(inputLen, hideLen, outputLen);
		weightList = new LinkedList<>();
		cList = new LinkedList<>();
	}

	public void train(double[][][] datas) {
		for (int ceng = 0; ceng < CENG_COUTN; ceng++) {
			for (int trainTime = 0; trainTime < TRAIN_TIMES; trainTime++) {
				for (int dataIndex = 0; dataIndex < datas.length; dataIndex++) {
					double[] inputValues = datas[dataIndex][0];
					double[] targetValues = datas[dataIndex][1];
					bpNeuro.trainOne(inputValues, targetValues);
				}
				DisplayNeuro.dn.repaint();
			}
			weightList.add(bpNeuro.inputHideWeightss);
			cList.add(bpNeuro.inputHideCs);
			for (int dataIndex = 0; dataIndex < datas.length; dataIndex++) {
				datas[dataIndex][0] = Util.predictValues(datas[dataIndex][0], bpNeuro.inputHideWeightss, bpNeuro.inputHideCs);
//				for (int inputIndex = 0; inputIndex < datas[dataIndex][0].length; inputIndex++) {
//					datas[dataIndex][0][inputIndex] = Util.unactivation(datas[dataIndex][0][inputIndex]);
//					if (Double.isNaN(datas[dataIndex][0][inputIndex])) {
//						throw new RuntimeException("NaN啦");
//					}
//				}
			}
			bpNeuro = new BpNeuro(datas[0][0].length, hideLen, outputLen);
			System.out.println("第" + (ceng + 1) + "层");
		}
	}

	public double[] predict(double[] inputValues) {
		Iterator<double[][]> weightIterator = weightList.iterator();
		Iterator<double[]> cIterator = cList.iterator();
		while (weightIterator.hasNext() && cIterator.hasNext()) {
			double[][] weigths = weightIterator.next();
			double[] cs = cIterator.next();
			inputValues = Util.predictValues(inputValues, weigths, cs);
		}
		return bpNeuro.predict(inputValues);
	}


}
