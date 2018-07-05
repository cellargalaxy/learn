package neuro;

/**
 * @author cellargalaxy
 * @time 2018/7/5
 */
public class BpNeuro {
	private final int inputLen;
	private final int hideLen;
	private final int outputLen;
	private final double step;
	private final double[][] inputHideWeightss;
	private final double[][] hideOutputWeightss;

	public BpNeuro(int inputLen, int outputLen, double step) {
		this(inputLen, inputLen, outputLen, step);
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
	}

	public void train(double[] inputValues, double[] targerValues) {
		double[] hideValues = predictHideValues(inputValues);
		double[] outputValues = predictOutputValues(hideValues);

		double[] errors = new double[outputValues.length];
		for (int i = 0; i < outputValues.length; i++) {
			errors[i] = Math.pow((targerValues[i] - outputValues[i]), 2);
		}

		double[] factors = new double[outputLen];
		for (int outputIndex = 0; outputIndex < outputLen; outputIndex++) {
			factors[outputIndex] = (targerValues[outputIndex] - outputValues[outputIndex]) * outputValues[outputIndex] * (1 - outputValues[outputIndex]);
			for (int hideIndex = 0; hideIndex < hideLen; hideIndex++) {
				double error = factors[outputIndex] * hideValues[hideIndex];
				hideOutputWeightss[outputIndex][hideIndex] += step * error;
			}
		}

		for (int hideIndex = 0; hideIndex < hideLen; hideIndex++) {
			for (int inputIndex = 0; inputIndex < inputLen; inputIndex++) {
				double error = 0;
				for (int outputIndex = 0; outputIndex < outputLen; outputIndex++) {
					error += factors[outputIndex] * hideOutputWeightss[outputIndex][hideIndex];
				}
				error *= hideValues[hideIndex] * (1 - hideValues[hideIndex]) * inputValues[inputIndex];
				inputHideWeightss[hideIndex][inputIndex] += step * error;
			}
		}
	}

	public double[] predict(double[] inputValues) {
		return predictOutputValues(predictHideValues(inputValues));
	}

	private double[] predictHideValues(double[] inputValues) {
		double[] hideValues = new double[hideLen];
		for (int i = 0; i < hideValues.length; i++) {
			hideValues[i] = predict(inputValues, inputHideWeightss[i]);
		}
		return hideValues;
	}

	private double[] predictOutputValues(double[] hideValues) {
		double[] outputValues = new double[outputLen];
		for (int i = 0; i < outputValues.length; i++) {
			outputValues[i] = predict(hideValues, hideOutputWeightss[i]);
		}
		return outputValues;
	}

	private double predict(double[] inputValues, double[] weights) {
		double sum = 0;
		for (int i = 0; i < inputValues.length; i++) {
			sum += inputValues[i] * weights[i];
		}
		return activation(sum);
	}

	private double activation(double d) {
		return 1 / (1 + Math.pow(Math.E, -d));
	}
}
