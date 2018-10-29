package top.cellargalaxy.learn. neuro;

import javax.swing.*;
import java.awt.*;

/**
 * @author cellargalaxy
 * @time 2018/7/5
 */
public class DisplayNeuro extends javax.swing.JPanel {
	private static final int SIDE_LENGTH = 200;
	private static final double start = 0.1;
	private static final double step = 0.001;
	private static final double end = 1;

	public static DisplayNeuro dn;

	ThNeuro thNeuro;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		dn = new DisplayNeuro();

		double[][][] datas = new double[10000][2][];
		for (int i = 0; i < datas.length; i++) {
			datas[i][0] = new double[]{Math.random() * (end - start) + start};
			datas[i][1] = new double[]{fun(datas[i][0][0])};
		}
		dn.thNeuro = new ThNeuro(1, 5, 1);

		JFrame jFrame = new JFrame();
		jFrame.setBounds(100, 100, 600, 600);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.add(dn);
		jFrame.setVisible(true);

		n3();
	}


	public static void n3() {
		for (int i = 0; i < 100000; i++) {
			for (int i1 = 0; i1 < 100000; i1++) {
				double[] inputValues = new double[]{Math.random() * (end - start) + start};
				double[] targetValue = new double[]{fun(inputValues[0])};
				dn.thNeuro.trainOne(inputValues, targetValue, 0.1);
			}
			System.out.println("训练" + i + "批");
			dn.repaint();
		}
	}

	static double fun(double d) {
		return (Math.sin(3.14 * (d - 0.25) * 2 * 4) + 1) / 8 * 3 + 0.125;
	}

	@Override
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paint(arg0);

		for (double x = start; x < end; x += step) {
			double target = fun(x);
			double output = thNeuro.predict(new double[]{x})[0];
			arg0.fillRect((int) (x * SIDE_LENGTH), (int) (target * SIDE_LENGTH * 1.0) + 100, 1, 1);
			arg0.fillRect((int) (x * SIDE_LENGTH), (int) (output * SIDE_LENGTH * 1.0) + 100, 1, 1);
		}
	}

}

