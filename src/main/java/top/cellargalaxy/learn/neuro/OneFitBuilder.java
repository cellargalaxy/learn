package top.cellargalaxy.learn. neuro;

public interface OneFitBuilder<T extends OneFit> {
	T createOneFit(double[][][] datas);
}
