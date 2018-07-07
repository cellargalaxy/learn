package neuro;

public interface OneFitBuilder<T extends OneFit> {
	T createOneFit(double[][][] datas);
}
