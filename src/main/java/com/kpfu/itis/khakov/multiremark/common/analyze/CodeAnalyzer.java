package com.kpfu.itis.khakov.multiremark.common.analyze;

import com.kpfu.itis.khakov.multiremark.common.AnalyzeResult;

/**
 * @author Rustam Khakov
 */
public interface CodeAnalyzer {
	AnalyzeResult analyze(String code);
}
