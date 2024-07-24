package com.lynhatkhanh.educationweb.educationweb.service;

import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserAccountService extends GenericService<UserAccount> {

    UserAccount findUserAndUserRoleByUsername(String userName);

    Page<UserAccount> findUsersOfRole(Integer pageNo, int roleId);

    List<UserAccount> findUsersOfRole(int roleId);

    Page<UserAccount> searchUsersOfRole(String keyword, Integer pageNo, int roleId);

    Page<UserAccount> getStudentOfCourse(Integer pageNo, int courseId);



}
