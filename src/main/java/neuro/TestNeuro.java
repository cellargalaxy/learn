package neuro;

/**
 * @author cellargalaxy
 * @time 2018/7/5
 */
public class TestNeuro {

	private int inputLen = 1;
	private int hideLen = 20;
	private double step = 0.05;
	double[][] inputHideWeightss = new double[inputLen][hideLen];
	double[] hideOutputWeightss = new double[hideLen];
	double[] hidden_thresholds = new double[hideLen];
	double output_threshold;


	public TestNeuro(int input_dimension, int hidden_dimension) {
		this.inputLen = input_dimension;
		this.hideLen = hidden_dimension;
		this.initialize();
	}


	void initialize() {
		for (int i = 0; i < inputLen; i++) {
			for (int j = 0; j < hideLen; j++) {
				inputHideWeightss[i][j] = Math.random();
			}
		}
		for (int i = 0; i < hideLen; i++) {
			hideOutputWeightss[i] = Math.random();
		}
		for (int i = 0; i < hideLen; i++) {
			hidden_thresholds[i] = Math.random();
		}
		output_threshold = Math.random();
	}

	double function(double x) {
		return 1 / (1 + Math.pow(Math.E, -x));
	}

	double predict(double[] input) {
		double[] hiddenValues = new double[hideLen];
		for (int i = 0; i < hiddenValues.length; i++) {
			double sum = 0;
			for (int j = 0; j < input.length; j++) {
				sum += input[j] * inputHideWeightss[j][i];
			}
			sum += hidden_thresholds[i];
			hiddenValues[i] = function(sum);
		}

		double sum = 0;
		for (int i = 0; i < hideLen; i++) {
			sum += hiddenValues[i] * hideOutputWeightss[i];
		}
		sum += output_threshold;
		return function(sum);
	}

	void trainOnce(double[] input, double expectedOutput) {
		double[] hiddenValues = new double[hideLen];
		double[] hiddenParams = new double[hideLen];

		for (int i = 0; i < hiddenValues.length; i++) {
			double sum = 0;
			for (int j = 0; j < input.length; j++) {
				sum += input[j] * inputHideWeightss[j][i];
			}
			sum += hidden_thresholds[i];
			hiddenValues[i] = function(sum);
			hiddenParams[i] = sum;
		}

		double sum = 0;
		for (int i = 0; i < hideLen; i++) {
			sum += hiddenValues[i] * hideOutputWeightss[i];
		}
		sum += output_threshold;//
		double outputValue = function(sum);

		for (int i = 0; i < input.length; i++) {
			double factor = (expectedOutput - outputValue) * outputValue * (1 - outputValue) * step * input[i];
			for (int j = 0; j < hideLen; j++) {
				double delta = factor * hideOutputWeightss[j] * hiddenValues[j] * (1 - hiddenValues[j]);
				inputHideWeightss[i][j] += delta;
			}
		}
		double factor = (expectedOutput - outputValue) * outputValue * (1 - outputValue) * step;
		for (int i = 0; i < hidden_thresholds.length; i++) {
			double delta = factor * hideOutputWeightss[i] * hiddenValues[i] * (1 - hiddenValues[i]);
			hidden_thresholds[i] += delta;
		}

		for (int i = 0; i < hideOutputWeightss.length; i++) {
			double delta = factor * hiddenValues[i];
			hideOutputWeightss[i] += delta;

		}
		double delta = (expectedOutput - outputValue) * outputValue * (1 - outputValue) * step;
		output_threshold += delta;
	}
}