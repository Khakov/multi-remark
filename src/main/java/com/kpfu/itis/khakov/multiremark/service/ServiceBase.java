package com.kpfu.itis.khakov.multiremark.service;

/**
 * @author Rustam Khakov
 */
public interface ServiceBase<T, L> {
	T save(T t);

	T get(L id);

	void delete(T t);
}
