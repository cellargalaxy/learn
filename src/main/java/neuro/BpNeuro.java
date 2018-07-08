package neuro;


/**
 * @author cellargalaxy
 * @time 2018/7/5
 * 参考：
 * https://blog.csdn.net/woodbean/article/details/7175378
 * https://www.cnblogs.com/charlotte77/p/5629865.html
 */
public class BpNeuro implements Neuro {
	public final int inputLen;
	public final int hideLen;
	public final int outputLen;
	public final double[][] inputHideWeightss;
	public final double[][] hideOutputWeightss;
	public final double[] inputHideCs;
	public final double[] hideOutputCs;
	public final double step;

	public BpNeuro(int inputLen, int hideLen, int outputLen) {
		this(inputLen, hideLen, outputLen, 1.0 / hideLen);
	}

	public BpNeuro(int inputLen, int hideLen, int outputLen, double step) {
		this.inputLen = inputLen;
		this.hideLen = hideLen;
		this.outputLen = outputLen;
		this.step = step;

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

	public double trainOne(double[] inputValues, double[] targetValues, double step) {
		double[] hideValues = predictHideValues(inputValues);
		double[] outputValues = predictOutputValues(hideValues);

		double[] factors = new double[outputLen];
		for (int outputIndex = 0; outputIndex < outputLen; outputIndex++) {
			factors[outputIndex] = (targetValues[outputIndex] - outputValues[outputIndex]) * outputValues[outputIndex] * (1 - outputValues[outputIndex]);
		}

		double[] errors = new double[hideLen];
		for (int hideIndex = 0; hideIndex < hideLen; hideIndex++) {
			for (int outputIndex = 0; outputIndex < outputLen; outputIndex++) {
				errors[hideIndex] += factors[outputIndex] * hideOutputWeightss[outputIndex][hideIndex];
			}
			errors[hideIndex] *= hideValues[hideIndex] * (1 - hideValues[hideIndex]);
		}

		////

		for (int hideIndex = 0; hideIndex < hideLen; hideIndex++) {
			for (int inputIndex = 0; inputIndex < inputLen; inputIndex++) {
				inputHideWeightss[hideIndex][inputIndex] += step * errors[hideIndex] * inputValues[inputIndex];
			}
		}

		for (int hideIndex = 0; hideIndex < inputHideCs.length; hideIndex++) {
			inputHideCs[hideIndex] += step * errors[hideIndex];//*1
		}

		for (int outputIndex = 0; outputIndex < outputLen; outputIndex++) {
			for (int hideIndex = 0; hideIndex < hideLen; hideIndex++) {
				hideOutputWeightss[outputIndex][hideIndex] += step * factors[outputIndex] * hideValues[hideIndex];
			}
		}

		for (int outputIndex = 0; outputIndex < hideOutputCs.length; outputIndex++) {
			hideOutputCs[outputIndex] += step * factors[outputIndex];//*1
		}

		double errorTotal = 0;
		for (int outputIndex = 0; outputIndex < outputLen; outputIndex++) {
			errorTotal += Math.abs(targetValues[outputIndex] - outputValues[outputIndex]);
		}
		return errorTotal;
	}

	public double trainOne(double[] inputValues, double[] targetValues) {
		return trainOne(inputValues, targetValues, step);
	}

	@Override
	public double[] countInputValueError(double[] inputValues, double[] targetValues) {
		double[] hideValues = predictHideValues(inputValues);
		double[] outputValues = predictOutputValues(hideValues);

		double[] factors = new double[outputLen];
		for (int outputIndex = 0; outputIndex < outputLen; outputIndex++) {
			factors[outputIndex] = (targetValues[outputIndex] - outputValues[outputIndex]) * outputValues[outputIndex] * (1 - outputValues[outputIndex]);
		}

		double[] errers = new double[inputValues.length];
		for (int inputIndex = 0; inputIndex < errers.length; inputIndex++) {
			for (int outputIndex = 0; outputIndex < outputLen; outputIndex++) {
				double errorHide = 0;
				for (int hideIndex = 0; hideIndex < hideValues.length; hideIndex++) {
					errorHide += hideOutputWeightss[outputIndex][hideIndex] * hideValues[hideIndex] * (1 - hideValues[hideIndex]) * inputHideWeightss[hideIndex][inputIndex];
				}
				errers[inputIndex] += factors[outputIndex] * errorHide;
			}
		}
		return errers;
	}

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
		for (int i = 0; i < newValues.length; i++) {
			newValues[i] = predictValue(values, weightss[i], Cs[i]);
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
