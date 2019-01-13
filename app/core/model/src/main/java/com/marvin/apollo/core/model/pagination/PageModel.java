package com.marvin.apollo.core.model.pagination;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

/**
 * @author hufeng
 * @version PageModel.java, v 0.1 2019-01-13 23:03 Exp $
 */
@Setter
@Getter
@ToString
public class PageModel<E> implements Serializable {

    private List<E> list;

    private long total;

    private int pageNo;

    private int pageSize;

    public int getTotalPages() {
        return ((int) total + pageSize - 1) / pageSize;
    }

    public <T> PageModel<T> to(Function<List<E>, List<T>> convert) {
        List<T> targetList = convert.apply(this.list);
        PageModel<T> pageModel = new PageModel<>();
        pageModel.setList(targetList);
        pageModel.setPageSize(this.pageSize);
        pageModel.setPageNo(this.pageNo);
        pageModel.setTotal(this.total);
        return pageModel;
    }
}
