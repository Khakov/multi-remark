package com.kpfu.itis.khakov.multiremark.common.work;

import com.kpfu.itis.khakov.multiremark.common.WorkResult;
import com.kpfu.itis.khakov.multiremark.entity.states.WorkMark;
import com.kpfu.itis.khakov.multiremark.entity.work.Work;

import java.util.Random;

/**
 * @author Rustam Khakov
 */
public class MockWorkRunner implements WorkRunner {
	private static final int MAX = 100;
	private static final int EXELLENT = 86;
	private static final int GOOD = 72;
	private static final int BAD = 40;


	@Override
	public WorkResult runWork(Work work) {
		WorkResult result = new WorkResult();
		Random r = new Random();
		if (work.getWorkAnswer() != null) {
			if (work.getWorkAnswer().getText() != null && work.getWorkAnswer().getText().length() > 200) {
				result.setMark(WorkMark.EXCELLENT);
				result.setPercent(EXELLENT + (MAX - EXELLENT) * r.nextDouble());
			} else if (work.getWorkAnswer().getText() != null && work.getWorkAnswer().getText().length() > 50) {
				result.setMark(WorkMark.GOOD);
				result.setPercent(GOOD + (EXELLENT - GOOD) * r.nextDouble());
			} else {
				result.setPercent(BAD + (GOOD - BAD) * r.nextDouble());
			}
		}
		return result;
	}
}
