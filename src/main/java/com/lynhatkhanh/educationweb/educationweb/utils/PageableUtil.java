package com.lynhatkhanh.educationweb.educationweb.utils;

import com.lynhatkhanh.educationweb.educationweb.constant.SystemConstant;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.List;

public class PageableUtil<T> {

    private Pageable pageable;
    private List<T> listT;
    private int pageNo;

    public PageableUtil(List<T> listT, int pageNo) {
        this.pageable = PageRequest.of(pageNo - 1, SystemConstant.PAGE_SIZE);
        this.listT = listT;
        this.pageNo = pageNo;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public List<T> getListT() {
        return listT;
    }

    public void setListT(List<T> listT) {
        this.listT = listT;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<T> getShowList() {
        pageable = PageRequest.of(pageNo - 1, SystemConstant.PAGE_SIZE);

        // offset: start at [x] index of Page list (item instance) - like PageNo [offset = pageNo * pageSize]
        // limit: after return list, take [x] results of list - like PageSize

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), listT.size());
//        Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > listT.size() ? listT.size() : (pageable.getOffset() + pageable.getPageSize()));


        List<T> showList = listT.subList(start, end);

        return showList;
    }
}