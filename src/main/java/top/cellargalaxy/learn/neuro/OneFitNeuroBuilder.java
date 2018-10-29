package top.cellargalaxy.learn. neuro;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by cellargalaxy on 18-7-7.
 */
public class OneFitNeuroBuilder implements OneFitBuilder<OneFitNeuro> {
	public static final int EVOLUTION_TOTAL_TIMES = 100;

	@Override
	public OneFitNeuro createOneFit(double[][][] datas) {
		LinkedList<BpNeuroEvolution> bpNeuroEvolutions = new LinkedList<>();
		for (int hideLen = 5; hideLen < 100; hideLen += 5) {
			bpNeuroEvolutions.add(new BpNeuroEvolution(new BpNeuro(datas[0][0].length, hideLen, 1)));
		}
		int evolutionTimes = EVOLUTION_TOTAL_TIMES / bpNeuroEvolutions.size();

		int doCount = 1;
		do {
			for (BpNeuroEvolution bpNeuroEvolution : bpNeuroEvolutions) {
				for (int i = 0; i < evolutionTimes * doCount; i++) {
					bpNeuroEvolution.trainOne(datas);
				}
			}
			doCount *= 2;
			//降序
			bpNeuroEvolutions.sort(new Comparator<BpNeuroEvolution>() {
				@Override
				public int compare(BpNeuroEvolution o1, BpNeuroEvolution o2) {
					double d = o1.errorTotal - o2.errorTotal;
					if (d > 0) {
						return -1;
					} else if (d < 0) {
						return 1;
					} else {
						return 0;
					}
				}
			});
			int removeCount = bpNeuroEvolutions.size() / 2;
			Iterator<BpNeuroEvolution> iterator = bpNeuroEvolutions.iterator();
			for (int i = 0; i < removeCount; i++) {
				iterator.next();
				iterator.remove();
			}
		} while (bpNeuroEvolutions.size() > 1);
		BpNeuroEvolution bpNeuroEvolution = bpNeuroEvolutions.getFirst();
		return bpNeuroEvolution;
	}

	class BpNeuroEvolution extends OneFitNeuro {
		double errorTotal;

		public BpNeuroEvolution(BpNeuro bpNeuro) {
			super(bpNeuro);
		}

		@Override
		public double trainOne(double[][][] datas) {
			errorTotal = super.trainOne(datas);
			return errorTotal;
		}
	}
}
