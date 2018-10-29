package top.cellargalaxy.learn. neuro;

public interface Neuro {
	double trainOne(double[] inputValues, double[] targetValues);

	double[] countInputValueError(double[] inputValues, double[] targetValues);

	double[] predict(double[] inputValues);
}
