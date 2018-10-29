package top.cellargalaxy.learn. neuro;

/**
 * Created by cellargalaxy on 18-7-8.
 */
public class Util {
	public static final double trainOne(
			double[] inputValues, double[] targetValues,
			double[][] inputHideWeightss, double[] inputHideCs,
			double[][] hideOutputWeightss, double[] hideOutputCs,
			double step) {

		int inputLen = inputValues.length;
		int hideLen = inputHideCs.length;
		int outputLen = hideOutputCs.length;

		double[] hideValues = predictValues(inputValues, inputHideWeightss, inputHideCs);
		double[] outputValues = predictValues(hideValues, hideOutputWeightss, hideOutputCs);

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

	public static double[] predictValues(double[] values, double[][] weightss, double[] cs) {
		double[] newValues = new double[weightss.length];
		for (int i = 0; i < newValues.length; i++) {
			newValues[i] = predictValue(values, weightss[i], cs[i]);
		}
		return newValues;
	}

	public static double predictValue(double[] values, double[] weights, double c) {
		double sum = 0;
		for (int i = 0; i < weights.length; i++) {
			sum += values[i] * weights[i];
		}
		return activation(sum + c);
	}

	public static double activation(double d) {
		return 1 / (1 + Math.pow(Math.E, -d));
	}

	public static double unactivation(double d) {
		return -(Math.log((1 / d) - 1)) / (Math.log(Math.E));
	}
}
