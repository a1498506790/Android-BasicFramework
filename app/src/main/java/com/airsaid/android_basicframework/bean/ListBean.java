package com.airsaid.android_basicframework.bean;

import java.util.List;

/**
 * 分页加载数据Bean
 *
 */
public class ListBean<T> {

    /**
     * pageAll : 1
     * pageSize : 1
     * pageIndex : 1
     * lists : []
     */

    private int pageAll;
    private int pageSize;
    private int pageIndex;
    private List<T> lists;

    public int getPageAll() {
        return pageAll;
    }

    public void setPageAll(int pageAll) {
        this.pageAll = pageAll;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<T> getLists() {
        return lists;
    }

    public void setLists(List<T> lists) {
        this.lists = lists;
    }

    @Override
    public String toString() {
        return "ListBean{" +
                "pageAll=" + pageAll +
                ", pageSize=" + pageSize +
                ", pageIndex=" + pageIndex +
                ", lists=" + lists +
                '}';
    }
}
