package top.cellargalaxy.learn. neuro;

/**
 * Created by cellargalaxy on 18-8-19.
 */
public class ThNeuro {
	public final int inputLen;
	public final int hideLen;
	public final int outputLen;
	public final int floorLen;
	public final double[][][] weightsss;
	public final double[][] css;
	public final double[][] inputHideWeightss;
	public final double[][] hideOutputWeightss;
	public final double[] inputHideCs;
	public final double[] hideOutputCs;

	public ThNeuro(int inputLen, int hideLen, int outputLen) {
		this.inputLen = inputLen;
		this.hideLen = hideLen;
		this.outputLen = outputLen;

		floorLen = 3;
		weightsss = new double[floorLen][][];
		css = new double[floorLen][];

		weightsss[1] = new double[hideLen][inputLen];
		for (int i = 0; i < weightsss[1].length; i++) {
			for (int j = 0; j < weightsss[1][i].length; j++) {
				weightsss[1][i][j] = Math.random();
			}
		}
		for (int floor = 2; floor < floorLen - 1; floor++) {
			weightsss[floor] = new double[hideLen][hideLen];
			for (int i = 0; i < weightsss[floor].length; i++) {
				for (int j = 0; j < weightsss[floor][i].length; j++) {
					weightsss[floor][i][j] = Math.random();
				}
			}
		}
		weightsss[weightsss.length - 1] = new double[outputLen][hideLen];
		for (int i = 0; i < weightsss[weightsss.length - 1].length; i++) {
			for (int j = 0; j < weightsss[weightsss.length - 1][i].length; j++) {
				weightsss[weightsss.length - 1][i][j] = Math.random();
			}
		}
		for (int floor = 1; floor < floorLen - 1; floor++) {
			css[floor] = new double[hideLen];
			for (int i = 0; i < css[floor].length; i++) {
				css[floor][i] = Math.random();
			}
		}
		css[css.length - 1] = new double[outputLen];
		for (int i = 0; i < css[css.length - 1].length; i++) {
			css[css.length - 1][i] = Math.random();
		}


		//      in1 in2
		//hide1 w11 w12
		//hide2 w21 w22
		inputHideWeightss = new double[hideLen][inputLen];
		//     hide1 hide2
		//out1  w11   w12
		//out2  w21   w22
		hideOutputWeightss = new double[outputLen][hideLen];
		inputHideCs = new double[hideLen];
		hideOutputCs = new double[outputLen];

		for (int i = 0; i < inputHideWeightss.length; i++) {
			for (int j = 0; j < inputHideWeightss[i].length; j++) {
				inputHideWeightss[i][j] = Math.random();
			}
		}
		for (int i = 0; i < hideOutputWeightss.length; i++) {
			for (int j = 0; j < hideOutputWeightss[i].length; j++) {
				hideOutputWeightss[i][j] = Math.random();
			}
		}
		for (int i = 0; i < inputHideCs.length; i++) {
			inputHideCs[i] = Math.random();
		}
		for (int i = 0; i < hideOutputCs.length; i++) {
			hideOutputCs[i] = Math.random();
		}
	}

	public void trainOne(double[] inputValues, double[] targetValues, double step) {
		double[][] valuess = new double[floorLen][];
		valuess[0] = inputValues;
		for (int floor = 1; floor < floorLen; floor++) {
			valuess[floor] = predictValues(valuess[floor - 1], weightsss[floor], css[floor]);
		}

		double[] outputValues = valuess[valuess.length - 1];
		double[] dtOutputs = new double[outputLen];
		for (int outputIndex = 0; outputIndex < outputLen; outputIndex++) {
			dtOutputs[outputIndex] = (outputValues[outputIndex] - targetValues[outputIndex]) * outputValues[outputIndex] * (1 - outputValues[outputIndex]);
		}

		double[][] dtss = new double[floorLen][];
		dtss[dtss.length - 1] = dtOutputs;
		for (int floor = floorLen - 2; floor > 0; floor--) {
			double[] dtHides = new double[hideLen];
			for (int hideIndex = 0; hideIndex < hideLen; hideIndex++) {
				for (int i = 0; i < dtss[floor + 1].length; i++) {
					dtHides[hideIndex] += weightsss[floor + 1][i][hideIndex] * dtss[floor + 1][i];
				}
				dtHides[hideIndex] *= valuess[floor][hideIndex] * (1 - valuess[floor][hideIndex]);
			}
			dtss[floor] = dtHides;
		}

		for (int floor = 1; floor < floorLen; floor++) {
			for (int i = 0; i < css[floor].length; i++) {
				css[floor][i] -= step * dtss[floor][i];
			}
		}
		for (int floor = 1; floor < weightsss.length; floor++) {
			for (int i = 0; i < weightsss[floor].length; i++) {
				for (int j = 0; j < weightsss[floor][i].length; j++) {
					weightsss[floor][i][j] -= step * (valuess[floor - 1][j] * dtss[floor][i]);
				}
			}
		}
	}

//	public void trainOne(double[] inputValues, double[] targetValues, double step) {
//		double[] hideValues = predictHideValues(inputValues);
//		double[] outputValues = predictOutputValues(hideValues);
//
//		double[] dtOutputs = new double[outputLen];
//		for (int outputIndex = 0; outputIndex < dtOutputs.length; outputIndex++) {
//			dtOutputs[outputIndex] = (outputValues[outputIndex] - targetValues[outputIndex]) * outputValues[outputIndex] * (1 - outputValues[outputIndex]);
//		}
//
//		double[] dtHides = new double[hideLen];
//		for (int hideIndex = 0; hideIndex < dtHides.length; hideIndex++) {
//			for (int outputIndex = 0; outputIndex < outputLen; outputIndex++) {
//				dtHides[hideIndex] += hideOutputWeightss[outputIndex][hideIndex] * dtOutputs[outputIndex];
//			}
//			dtHides[hideIndex] *= hideValues[hideIndex] * (1 - hideValues[hideIndex]);
//		}
//
//		double[] dtOutputCs = dtOutputs;
//		double[] dtHideCs = dtHides;
//		double[][] dtInputHideWeightss = new double[hideLen][inputLen];
//		double[][] dtHideOutputWeightss = new double[outputLen][hideLen];
//		for (int hideIndex = 0; hideIndex < dtInputHideWeightss.length; hideIndex++) {
//			for (int inputIndex = 0; inputIndex < dtInputHideWeightss[hideIndex].length; inputIndex++) {
//				dtInputHideWeightss[hideIndex][inputIndex] = inputValues[inputIndex] * dtHides[hideIndex];
//			}
//		}
//		for (int outputIndex = 0; outputIndex < dtHideOutputWeightss.length; outputIndex++) {
//			for (int hideIndex = 0; hideIndex < dtHideOutputWeightss[outputIndex].length; hideIndex++) {
//				dtHideOutputWeightss[outputIndex][hideIndex] = hideValues[hideIndex] * dtOutputs[outputIndex];
//			}
//		}
//
//		for (int hideIndex = 0; hideIndex < inputHideCs.length; hideIndex++) {
//			inputHideCs[hideIndex] -= step * dtHideCs[hideIndex];
//		}
//		for (int outputIndex = 0; outputIndex < hideOutputCs.length; outputIndex++) {
//			hideOutputCs[outputIndex] -= step * dtOutputCs[outputIndex];
//		}
//		for (int i = 0; i < inputHideWeightss.length; i++) {
//			for (int j = 0; j < inputHideWeightss[i].length; j++) {
//				inputHideWeightss[i][j] -= step * dtInputHideWeightss[i][j];
//			}
//		}
//		for (int i = 0; i < hideOutputWeightss.length; i++) {
//			for (int j = 0; j < hideOutputWeightss[i].length; j++) {
//				hideOutputWeightss[i][j] -= step * dtHideOutputWeightss[i][j];
//			}
//		}
//	}

	public double[] predict(double[] inputValues) {
		return predictOutputValues(predictHideValues(inputValues));
	}

	private double[] predictHideValues(double[] inputValues) {
		return predictValues(inputValues, inputHideWeightss, inputHideCs);
	}

	private double[] predictOutputValues(double[] hideValues) {
		return predictValues(hideValues, hideOutputWeightss, hideOutputCs);
	}

	private double[] predictValues(double[] values, double[][] weightss, double[] Cs) {
		double[] newValues = new double[weightss.length];
		for (int newValueIndex = 0; newValueIndex < newValues.length; newValueIndex++) {
			newValues[newValueIndex] = predictValue(values, weightss[newValueIndex], Cs[newValueIndex]);
		}
		return newValues;
	}

	private double predictValue(double[] values, double[] weights, double c) {
		double sum = 0;
		for (int i = 0; i < weights.length; i++) {
			sum += values[i] * weights[i];
		}
		return activation(sum + c);
	}

	private double activation(double d) {
		return 1 / (1 + Math.pow(Math.E, -d));
	}
}
