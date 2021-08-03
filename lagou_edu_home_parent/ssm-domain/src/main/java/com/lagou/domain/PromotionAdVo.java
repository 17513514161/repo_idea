package com.lagou.domain;

public class PromotionAdVo {

    // 当前页数
    private Integer currentPage;

    // 每页显示条数
    private Integer PageSize;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PromotionAdVo{" +
                "currentPage=" + currentPage +
                ", PageSize=" + PageSize +
                '}';
    }
}
