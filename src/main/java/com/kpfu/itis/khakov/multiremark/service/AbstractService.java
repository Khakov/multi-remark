package com.kpfu.itis.khakov.multiremark.service;

/**
 * @author Rustam Khakov
 */
public interface AbstractService<T> {
	T save(T t);

	T get(T t);

	T delete(T t);
}
