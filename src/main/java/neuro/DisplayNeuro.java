package neuro;

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
	private static final double end = 2;

	OneFit oneFit;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DisplayNeuro dn = new DisplayNeuro();

		double[][][] datas = new double[10000][2][];
		for (int i = 0; i < datas.length; i++) {
			datas[i][0] = new double[]{Math.random() * (end - start) + start};
			datas[i][1] = new double[]{funcA(datas[i][0][0])};
		}
		dn.oneFit = new OneFitNeuro(new MyNeuro(datas, 1, 1, 1));

		JFrame jFrame = new JFrame();
		jFrame.setBounds(100, 100, 600, 600);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.add(dn);
		jFrame.setVisible(true);

		n3(dn);

	}

	public static void n3(DisplayNeuro dn) {
		for (int i = 0; i < 100000; i++) {
			for (int i1 = 0; i1 < 100000; i1++) {
				double[] inputValues = new double[]{Math.random() * (end - start) + start};
				double targetValue = funcA(inputValues[0]);
				dn.oneFit.trainOne(inputValues, targetValue);
			}
			System.out.println("训练一批");
			dn.repaint();
		}
	}

	public static void n2(DisplayNeuro dn) {
		dn.oneFit = new OneFitNeuro(new BpNeuro(1, 5, 1));
		for (int i = 0; i < 100000; i++) {
			for (int i1 = 0; i1 < 10000; i1++) {
				double[] inputValues = new double[]{Math.random() * (end - start) + start};
				double targetValue = funcA(inputValues[0]);
				dn.oneFit.trainOne(inputValues, targetValue);
			}
			dn.repaint();
		}
	}

	public static void n1(DisplayNeuro dn) {
		double[][][] datas = new double[10000][2][];
		for (int i = 0; i < datas.length; i++) {
			datas[i][0] = new double[]{Math.random() * (end - start) + start};
			datas[i][1] = new double[]{funcA(datas[i][0][0])};
		}
		OneFitNeuroBuilder oneFitNeuroBuilder = new OneFitNeuroBuilder();
		dn.oneFit = oneFitNeuroBuilder.createOneFit(datas);
	}

	private static double funcA(double d) {
		return activation(fun(d));
	}

	static double fun(double d) {
		return (Math.sin(4 * 3.14 * (d + 0.5)) - 4);
//		return (Math.sin(4 * 3.14 * (d + 0.5)) - 4) / (Math.cos(d + 0.5) * Math.tan(d + 0.5));
	}

	@Override
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paint(arg0);

		for (double x = start; x < end; x += step) {
			double target = funcA(x);
			double output = oneFit.predict(new double[]{x});
//			System.out.println(target + " : " + output);
			arg0.fillRect((int) (x * SIDE_LENGTH), (int) (target * SIDE_LENGTH * 10) + 100, 1, 1);
			arg0.fillRect((int) (x * SIDE_LENGTH), (int) (output * SIDE_LENGTH * 10) + 100, 1, 1);
		}
	}

	static double activation(double d) {
		return 1 / (1 + Math.pow(Math.E, -d));
	}
}

