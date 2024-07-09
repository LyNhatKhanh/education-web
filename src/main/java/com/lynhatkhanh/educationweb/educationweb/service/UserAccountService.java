package com.lynhatkhanh.educationweb.educationweb.service;

import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserAccountService {

    UserAccount findByUserName(String userName);

    List<UserAccount> findAll();

    UserAccount findById(int userId);

    void save(UserAccount userAccount);

    Page<UserAccount> getAll(Integer pageNo);

    Page<UserAccount> searchUser(String keyword, Integer pageNo);
}
