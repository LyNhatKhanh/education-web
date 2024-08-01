package com.lynhatkhanh.educationweb.educationweb.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface GenericService<T> {

    T save (T entity);

    List<T> findAll();

    T findById (int theId);

    void deleteById(int theId);

    Page<T> findAll(Integer pageNo);

    List<T> searchByKeyword(String keyword);

    Page<T> searchByKeyword(String keyword, Integer pageNo);

}
