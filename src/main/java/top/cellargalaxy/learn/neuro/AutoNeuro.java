package top.cellargalaxy.learn. neuro;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by cellargalaxy on 18-7-8.
 */
public class AutoNeuro implements Neuro {
	public static final int TRAIN_TIMES = 10000;
	public static final int CENG = 10;
	//	private final int inputLen;
	private final int hideLen;
	//	private final int outputLen;
	private final double step;
	private final LinkedList<double[][]> weightList;
	private final LinkedList<double[]> cList;
	private double[][] hideOutputWeightss;
	private double[] hideOutputCs;

	public AutoNeuro(int inputLen, int hideLen, int outputLen, double step) {
//		this.inputLen = inputLen;
		this.hideLen = hideLen;
//		this.outputLen = outputLen;
		this.step = step;
		weightList = new LinkedList<>();
		cList = new LinkedList<>();
	}

	public double train(double[][][] datas) {
		double errorTotal = -1;

		for (int i = 0; i < CENG; i++) {
			double[][] inputHideWeightss = new double[hideLen][datas[0][0].length];
			hideOutputWeightss = new double[datas[0][1].length][hideLen];
			hideOutputCs = new double[datas[0][1].length];
			double[] inputHideCs = new double[hideLen];
			for (int j = 0; j < hideOutputWeightss.length; j++) {
				for (int k = 0; k < hideOutputWeightss[j].length; k++) {
					hideOutputWeightss[j][k] = Math.random();
				}
			}
			for (int j = 0; j < inputHideWeightss.length; j++) {
				for (int k = 0; k < inputHideWeightss[j].length; k++) {
					inputHideWeightss[j][k] = Math.random();
				}
			}
			for (int j = 0; j < hideOutputCs.length; j++) {
				hideOutputCs[j] = Math.random();
			}
			for (int j = 0; j < inputHideCs.length; j++) {
				inputHideCs[j] = Math.random();
			}
			synchronized (weightList) {
				weightList.add(inputHideWeightss);
				cList.add(inputHideCs);
				System.out.println("eng:" + weightList.size());
			}
			for (int j = 0; j < TRAIN_TIMES; j++) {
				for (int dataIndex = 0; dataIndex < datas.length; dataIndex++) {
					double[] inputValues = datas[dataIndex][0];
					double[] targetValues = datas[dataIndex][1];
					errorTotal = Util.trainOne(inputValues, targetValues, inputHideWeightss, inputHideCs, hideOutputWeightss, hideOutputCs, step) / datas[0][1].length;
				}
			}
			for (int dataIndex = 0; dataIndex < datas.length; dataIndex++) {
				datas[dataIndex][0] = Util.predictValues(datas[dataIndex][0], inputHideWeightss, inputHideCs);
			}
			DisplayNeuro.dn.repaint();//////////
		}
		return errorTotal;
	}


	@Override
	public double trainOne(double[] inputValues, double[] targetValues) {
		return 0;
	}

	@Override
	public double[] countInputValueError(double[] inputValues, double[] targetValues) {
		return new double[0];
	}

	@Override
	public double[] predict(double[] inputValues) {
		synchronized (weightList) {
			if (weightList.size() == 0) {
				throw new RuntimeException("网络还没训练");
			}
			Iterator<double[][]> weightIterator = weightList.iterator();
			Iterator<double[]> cIterator = cList.iterator();
			while (weightIterator.hasNext() && cIterator.hasNext()) {
				double[][] weigths = weightIterator.next();
				double[] cs = cIterator.next();
				inputValues = Util.predictValues(inputValues, weigths, cs);
			}
		}
		return Util.predictValues(inputValues, hideOutputWeightss, hideOutputCs);
	}


}
/*
weigths: 5
cs: 5
weigths: 1
cs: 1
weigths: 5
cs: 5
 */
