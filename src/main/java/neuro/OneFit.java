package neuro;

public interface OneFit {
	double trainOne(double[] inputValues, double targetValue);

	double trainOne(double[][][] datas);

	void train(double[][][] datas);

	double[] countInputValueError(double[] inputValues, double targetValue);

	double predict(double[] inputValues);
}
