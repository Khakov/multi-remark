package com.kpfu.itis.khakov.multiremark.common;

import com.kpfu.itis.khakov.multiremark.entity.states.WorkMark;

/**
 * @author Rustam Khakov
 */
public class WorkResult {
	private String result;
	private WorkMark mark;
	private double percent;

	public WorkResult() {
		setMark(WorkMark.BAD);
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public WorkMark getMark() {
		return mark;
	}

	public void setMark(WorkMark mark) {
		this.mark = mark;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	@Override
	public String toString() {
		return "{" +
				"result: " + result + '\'' +
				", mark: " + mark +
				", percent: "  + percent +
				'}';
	}
}
