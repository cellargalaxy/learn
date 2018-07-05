package neuro;

import javax.swing.*;
import java.awt.*;

/**
 * @author cellargalaxy
 * @time 2018/7/5
 */
public class DisplayNeuro extends javax.swing.JPanel {

	public static final int SIDE_LENGTH = 200;

	TestNeuro testNeuro;
	BpNeuro bpNeuro;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DisplayNeuro dn = new DisplayNeuro();
		JFrame jFrame = new JFrame();
		jFrame.setBounds(100, 100, 300, 300);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.add(dn);
		jFrame.setVisible(true);

		dn.n1();
	}

	public void n1() {
		TestNeuro testNeuro = new TestNeuro(1, 20);
		this.testNeuro = testNeuro;
		this.repaint();
		for (int i = 0; i < 100000000; i++) {
			double[] input = new double[1];
			input[0] = Math.random() / 2 + 0.25;
			double expectedOutput = (Math.sin(3.14 * (input[0] - 0.25) * 2 * 4) + 1) / 8 * 3 + 0.125;
			testNeuro.trainOnce(input, expectedOutput);

			if (i % 100000 == 0) {
				System.out.println("input please ");
				this.repaint();
			}
		}
	}

	public void n2() {
		BpNeuro bpNeuro = new BpNeuro(1, 1, 0.05);
		this.bpNeuro = bpNeuro;
		this.repaint();
		for (int i = 0; i < 100000000; i++) {
			double[] input = new double[1];
			input[0] = Math.random() / 2 + 0.25;
			double expectedOutput = (Math.sin(3.14 * (input[0] - 0.25) * 2 * 4) + 1) / 8 * 3 + 0.125;
			bpNeuro.train(input, new double[]{expectedOutput});

			if (i % 100000 == 0) {
				System.out.println("input please ");
				this.repaint();
			}
		}
	}

	@Override
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paint(arg0);

		for (double x = 0.25; x < 0.75; x += 0.005) {
			double[] input = new double[1];
			input[0] = x;
			double y;
			if (testNeuro != null) {
				y = testNeuro.predict(input);
			} else {
				y = bpNeuro.predict(input)[0];
			}
			arg0.fillRect((int) (x * SIDE_LENGTH), (int) ((1 - y) * SIDE_LENGTH), 1, 1);
		}
	}
}

