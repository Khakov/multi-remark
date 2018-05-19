package com.kpfu.itis.khakov.multiremark.common.work;

import com.kpfu.itis.khakov.multiremark.common.WorkResult;
import com.kpfu.itis.khakov.multiremark.entity.work.Work;

/**
 * @author Rustam Khakov
 */
public interface WorkRunner {
	WorkResult runWork(Work work);
}
