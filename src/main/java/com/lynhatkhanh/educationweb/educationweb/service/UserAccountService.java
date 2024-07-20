package com.lynhatkhanh.educationweb.educationweb.service;

import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserAccountService {

    UserAccount findByUserName(String userName);

    UserAccount findUserAndUserRoleByUserId(String userName);

    List<UserAccount> findAll();

    UserAccount findById(int userId);

    void save(UserAccount userAccount);

    Page<UserAccount> getUsersOfRole(Integer pageNo, int roleId);

    List<UserAccount> getUsersOfRole(int roleId);

    Page<UserAccount> searchUsersOfRole(String keyword, Integer pageNo, int roleId);

    void deleteById(int theId);

    Page<UserAccount> getStudentOfCourse(Integer pageNo, int courseId);



}
