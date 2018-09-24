package com.scms.pojo;

import java.io.Serializable;

public class QueryVO implements Serializable {
    //查询条件。封装查询条件及分页数据。
    private String findkey;

    //当前页 点击的页码
    private Integer page;
    //每页数
    private Integer size = 10;
    //开始行
    private Integer startRow = 0;

    public String getFindkey() {
        return findkey;
    }

    public void setFindkey(String findkey) {
        this.findkey = findkey;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public QueryVO(String findkey, Integer page, Integer size, Integer startRow) {
        this.findkey = findkey;
        this.page = page;
        this.size = size;
        this.startRow = startRow;
    }

    public QueryVO() {
    }
}
