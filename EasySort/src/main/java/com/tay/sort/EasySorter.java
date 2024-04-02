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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 改进的简易排序器，支持基于多个字段的复合排序。
 */
public class EasySorter {


    /**
     * 对列表进行排序。
     *
     * @param source     待排序的列表
     * @param sortRules  排序规则列表，每个规则指定一个排序字段和方向
     * @param isNullFirst 是否将null值排在前面
     * @param <T>        列表中元素的类型
     * @return           排序后的列表
     */
    public static <T> List<T> sort(List<T> source, List<SortRule<T>> sortRules, boolean isNullFirst) {
        if (source == null || source.isEmpty() || sortRules == null || sortRules.isEmpty()) {
            return source; // 输入列表为空或排序规则为空时，直接返回原列表
        }

        // 基于第一个排序规则创建比较器
        Comparator<T> comparator = null;

        // 循环构建基于每个排序规则的比较器，并将它们组合起来
        for (SortRule<T> rule : sortRules) {
            Comparator<T> currentComparator = Comparator.comparing(rule.getKeyExtractor(),
                    Comparator.nullsFirst(Comparator.naturalOrder()));
            if (rule.isDesc()) {
                currentComparator = currentComparator.reversed();
            }
            if (!isNullFirst) {
                currentComparator = Comparator.comparing(rule.getKeyExtractor(),
                        Comparator.nullsLast(Comparator.naturalOrder()));
                if (rule.isDesc()) {
                    currentComparator = currentComparator.reversed();
                }
            }

            comparator = (comparator == null) ? currentComparator : comparator.thenComparing(currentComparator);
        }

        // 使用构建的比较器对源列表进行排序，并返回排序后的列表
        return source.stream().sorted(comparator).collect(Collectors.toList());
    }

}
