/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * @author  Aiyun Tang
 * @mail aiyun.tang@gmail.com
 */

package com.tay.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

public class EasySorter {
	/**
	 * 
	 * @param source 等待排序的队列
	 * @param sortString 排序表达式，属性名称以逗号分隔，前带减号"-"表示逆序
	 * @param isNullFirst 如果属性值为null是否排最前面，要么排最后面
	 * @return
	 */
	public static <T> List<T> sort(List<T> source, String sortString, boolean isNullFirst) {
		if (source == null || source.size() == 0) {
			return source;
		}
		if (sortString == null || sortString.length() == 0) {
			return source;
		} else {
			String[] sortFields = sortString.split(",");
			List<Comparator<T>> comparatorList = new ArrayList<>();
			for (String field : sortFields) {
				String fieldRemovedSpace = field.replaceAll(" ", "");
				boolean isStartWithMinus = fieldRemovedSpace.startsWith("-");
				String realField = fieldRemovedSpace;
				if (isStartWithMinus) {
					realField = fieldRemovedSpace.substring(1);
				}
				Comparator<T> comparator = new FieldComparator<T>(realField, isStartWithMinus, isNullFirst);
				comparatorList.add(comparator);
			}

			Comparator<T> first = comparatorList.get(0);
			for (int i = 1; i < comparatorList.size(); i++) {
				first = first.thenComparing(comparatorList.get(i));
			}
			List<T> result = new ArrayList<T>(source);
			result.sort(first);
			return result;
		}
	}

	/**
	 * 根据属性名称获取对象属性值
	 * 
	 * @param object 属性目标对象
	 * @param fieldName 属性名
	 * @return 属性值
	 * @throws Exception
	 */
	private static Object getByGetMethod(Object object, String fieldName) {
		String methodName = getMethodNameByFieldName(fieldName);
		try {
			return object.getClass().getMethod(methodName, null).invoke(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String getMethodNameByFieldName(String field) {
		String firstLetter = field.substring(0, 1);
		String left = field.substring(1);
		return "get" + firstLetter.toUpperCase() + left;
	}

	@Data
	@AllArgsConstructor
	static class FieldComparator<T> implements Comparator<T> {
		private String field;
		private boolean isDesc;
		private boolean isNullFirst;

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public int compare(Object o1, Object o2) {
			Comparable first = (Comparable) getByGetMethod(o1, field);
			Comparable second = (Comparable) getByGetMethod(o2, field);
			if (isNullFirst) {
				if (first == null && second == null) {
					return 0;
				} else if (first == null && second != null) {
					return -1;
				} else if (first != null && second == null) {
					return 1;
				}
			}
			else {
				if (first == null && second == null) {
					return 0;
				} else if (first == null && second != null) {
					return 1;
				} else if (first != null && second == null) {
					return -1;
				}
			}
			if (isDesc) {
				return second.compareTo(first);
			} else {
				return first.compareTo(second);
			}
		}
	}

	}
