package com.tay.sort;

import lombok.Getter;

import java.util.function.Function;

/**
 * 排序规则类，封装了排序的键提取器和排序方向。
 *
 * @param <T> 待排序元素的类型
 */
public class SortRule<T> {
    @Getter
    private Function<T, Comparable> keyExtractor; // 键提取器，用于从元素中提取比较键
    @Getter
    private boolean isDesc; // 排序方向标志，true为降序，false为升序

    /**
     * 构造函数。
     *
     * @param keyExtractor 键提取器，从T类型元素中提取出可比较的键
     * @param isDesc       是否降序排序，true表示降序，false表示升序
     */
    public SortRule(Function<T, Comparable> keyExtractor, boolean isDesc) {
        this.keyExtractor = keyExtractor;
        this.isDesc = isDesc;
    }
}

