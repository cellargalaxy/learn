package neuro;

/**
 * Created by cellargalaxy on 18-7-6.
 */
public class OneFitNeuro implements OneFit {
	public static final int TRAIN_COUNT = 10000;
	private final Neuro neuro;

	public OneFitNeuro(Neuro neuro) {
		this.neuro = neuro;
	}

	@Override
	public double trainOne(double[] inputValues, double targetValue) {
		return neuro.trainOne(inputValues, new double[]{targetValue});
	}

	@Override
	public double trainOne(double[][][] datas) {
		double count = 0;
		for (double[][] data : datas) {
			count += trainOne(data[0], data[1][0]);
		}
		return count / datas.length;
	}

	@Override
	public void train(double[][][] datas) {
		for (int i = 0; i < TRAIN_COUNT; i++) {
			trainOne(datas);
		}
	}

	@Override
	public double[] countInputValueError(double[] inputValues, double targetValue) {
		return neuro.countInputValueError(inputValues, new double[]{targetValue});
	}

	@Override
	public double predict(double[] inputValues) {
		return neuro.predict(inputValues)[0];
	}

	public Neuro getNeuro() {
		return neuro;
	}
}
