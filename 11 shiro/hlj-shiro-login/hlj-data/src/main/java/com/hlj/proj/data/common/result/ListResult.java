package com.hlj.proj.data.common.result;

import java.util.List;

/***
 * 返回集合值结果
 * 
 * @author YuYue
 *
 * @param <T>
 */
public class ListResult<T> {

	private static final long serialVersionUID = 1641284062873197036L;

	public ListResult() {}

	public static <E> ListResult<E> newListResult() {
		return new ListResult<E>();
	}

	/**
	 * 集合值结果
	 */
	private List<T> values;

	public List<T> getValues() {
		return values;
	}

	public ListResult<T> setValues(List<T> values) {
		this.values = values;
		return this;
	}
}
