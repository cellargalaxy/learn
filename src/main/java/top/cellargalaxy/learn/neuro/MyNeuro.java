package top.cellargalaxy.learn. neuro;

/**
 * Created by cellargalaxy on 18-7-7.
 */
public class MyNeuro implements Neuro {
	private final int inputLen;
	private final int hideLen;
	private final int outputLen;
	private final OneFit[] intputHideOneFits;
	private final OneFit[] hideOutputOneFits;

	public MyNeuro(double[][][] datas, int inputLen, int hideLen, int outputLen) {
		this.inputLen = inputLen;
		this.hideLen = hideLen;
		this.outputLen = outputLen;
		intputHideOneFits = new OneFit[hideLen];
		hideOutputOneFits = new OneFit[outputLen];
		//       h1   h2
		//data1  h11  h12
		//data2  h21  h22
		double[][] hideValues = new double[datas.length][hideLen];

		for (int dataIndex = 0; dataIndex < hideValues.length; dataIndex++) {
			for (int hideIndex = 0; hideIndex < hideValues[dataIndex].length; hideIndex++) {
				hideValues[dataIndex][hideIndex] = Math.random();
			}
		}

		OneFitNeuroBuilder oneFitNeuroBuilder = new OneFitNeuroBuilder();

		for (int hideIndex = 0; hideIndex < intputHideOneFits.length; hideIndex++) {
			double[][][] dss = new double[datas.length][2][];
			for (int dataIndex = 0; dataIndex < dss.length; dataIndex++) {
				dss[dataIndex][0] = datas[dataIndex][0];
				dss[dataIndex][1] = new double[]{hideValues[dataIndex][hideIndex]};
			}
			intputHideOneFits[hideIndex] = oneFitNeuroBuilder.createOneFit(dss);

			System.out.println("选出一个intputHideOneFits");

		}
		for (int outputIndex = 0; outputIndex < hideOutputOneFits.length; outputIndex++) {
			double[][][] dss = new double[datas.length][2][];
			for (int dataIndex = 0; dataIndex < dss.length; dataIndex++) {
				dss[dataIndex][0] = hideValues[dataIndex];
				dss[dataIndex][1] = new double[]{datas[dataIndex][1][outputIndex]};
			}
			hideOutputOneFits[outputIndex] = oneFitNeuroBuilder.createOneFit(dss);

			System.out.println("选出一个hideOutputOneFits");

		}
	}

	public double trainOne(double[] inputValues, double[] targetValues) {
		double[] hideValues = predictHideValues(inputValues);
		double[] outputValues = predictOutputValues(hideValues);

		//     h1 h2
		//out1
		//out2
		double[][] hideValueErrors = new double[outputLen][];
		for (int outputIndex = 0; outputIndex < outputLen; outputIndex++) {
			hideValueErrors[outputIndex] = hideOutputOneFits[outputIndex].countInputValueError(hideValues, targetValues[outputIndex]);
		}
		for (int hideIndex = 0; hideIndex < hideValues.length; hideIndex++) {

//			System.out.println(hideValues[hideIndex]);

			for (int outputIndex = 0; outputIndex < hideValueErrors.length; outputIndex++) {
				hideValues[hideIndex] += hideValueErrors[outputIndex][hideIndex] * 1000;
			}

//			System.out.println(hideValues[hideIndex]);
//			System.out.println();

		}

		trainIntputHideOneFits(inputValues, hideValues);
		trainHideOutputOneFits(hideValues, targetValues);

		double errorTotal = 0;
		for (int outputIndex = 0; outputIndex < outputLen; outputIndex++) {
			errorTotal += Math.abs(targetValues[outputIndex] - outputValues[outputIndex]);
		}
		return errorTotal;
	}

	@Override
	public double[] countInputValueError(double[] inputValues, double[] targetValues) {
		throw new RuntimeException("MyNeuro不能countInputValueError");
	}

	private void trainIntputHideOneFits(double[] inputValues, double[] hideValues) {
		trainOneFits(intputHideOneFits, inputValues, hideValues);
	}

	private void trainHideOutputOneFits(double[] hideValues, double[] targetValues) {
		trainOneFits(hideOutputOneFits, hideValues, targetValues);
	}

	private void trainOneFits(OneFit[] oneFits, double[] inputValues, double[] outputValues) {
		for (int i = 0; i < oneFits.length; i++) {
			oneFits[i].trainOne(inputValues, outputValues[i]);
		}
	}

	public double[] predict(double[] inputValues) {
		return predictOutputValues(predictHideValues(inputValues));
	}

	private double[] predictHideValues(double[] inputValues) {
		return predictValues(inputValues, intputHideOneFits);
	}

	private double[] predictOutputValues(double[] hideValues) {
		return predictValues(hideValues, hideOutputOneFits);
	}

	private double[] predictValues(double[] values, OneFit[] oneFits) {
		double[] newValues = new double[oneFits.length];
		for (int outputIndex = 0; outputIndex < newValues.length; outputIndex++) {
			newValues[outputIndex] = oneFits[outputIndex].predict(values);
		}
		return newValues;
	}
}
