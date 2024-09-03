package com.lynhatkhanh.educationweb.educationweb.service.exclude.implement;

import com.lynhatkhanh.educationweb.educationweb.constant.SystemConstant;
import com.lynhatkhanh.educationweb.educationweb.dao.exclude.UserAccountRepository;
import com.lynhatkhanh.educationweb.educationweb.exception.DuplicateUsernameException;
import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
import com.lynhatkhanh.educationweb.educationweb.service.exclude.UserAccountService;
import com.lynhatkhanh.educationweb.educationweb.utils.PageableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount findUserAndUserRoleByUsername(String userName) {
        return userAccountRepository.findUserAccountAndRoleByUserName(userName);
    }

    @Override
    public List<UserAccount> findAll() {
        return (List<UserAccount>) userAccountRepository.findAll();
    }

    @Override
    public UserAccount findById(int userId) {
        return userAccountRepository.findById(userId).orElseThrow(() -> new RuntimeException("User is not found - " + userId));
    }

    @Override
    @Transactional
    public UserAccount save(UserAccount userAccount) throws DuplicateUsernameException {
        try {
            return userAccountRepository.save(userAccount);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateUsernameException("Username already exist!");
        }
    }

    @Override
    public Page<UserAccount> findUsersOfRole(Integer pageNo, int roleId) {
        if (roleId == 0) {
            Pageable pageable = PageRequest.of(pageNo - 1, SystemConstant.PAGE_SIZE);
            return userAccountRepository.findAll(pageable);
        } else {
            List<UserAccount> studentList = userAccountRepository.findUserAccountOfRole(roleId);

            PageableUtil<UserAccount> pageableUtil = new PageableUtil<>(studentList, pageNo);
            List<UserAccount> showList = pageableUtil.getShowList();

            return new PageImpl<>(showList, pageableUtil.getPageable(), studentList.size());
        }
    }

    @Override
    public List<UserAccount> findUsersOfRole(int roleId) {
        return userAccountRepository.findUserAccountOfRole(roleId);
    }

    @Override
    public Page<UserAccount> searchUsersOfRole(String keyword, Integer pageNo, int roleId) {
        List<UserAccount> userList;
        if (roleId == 0)
            userList = userAccountRepository.searchUserAccount(keyword);
        else
            userList = userAccountRepository.searchUserAccountOfRole(keyword, roleId);

        PageableUtil<UserAccount> pageableUtil = new PageableUtil<>(userList, pageNo);
        List<UserAccount> showList = pageableUtil.getShowList();

        return new PageImpl<>(showList, pageableUtil.getPageable(), userList.size());
    }

    @Override
    public Page<UserAccount> searchStudentsOfCourse(String keyword, Integer pageNo, int courseId) {
        List<UserAccount> studentOfCourseWithKeyword = userAccountRepository.searchUserAccountOfCourse(keyword, courseId);

        PageableUtil<UserAccount> pageableUtil = new PageableUtil<>(studentOfCourseWithKeyword, pageNo);
        List<UserAccount> showList = pageableUtil.getShowList();

        return new PageImpl<>(showList, pageableUtil.getPageable(), studentOfCourseWithKeyword.size());
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        userAccountRepository.deleteById(theId);
    }

    @Override
    public Page<UserAccount> findStudentOfCourse(Integer pageNo, int courseId) {
        List<UserAccount> studentOfCourse = userAccountRepository.findUserAccountOfCourse(courseId);

        PageableUtil<UserAccount> pageableUtil = new PageableUtil<>(studentOfCourse, pageNo);
        List<UserAccount> showList = pageableUtil.getShowList();

        return new PageImpl<>(showList, pageableUtil.getPageable(), studentOfCourse.size());
    }

    @Override
    public Page<UserAccount> findStudentWithoutCourse(Integer pageNo) {
        List<UserAccount> allStudents = userAccountRepository.findStudentWithoutCourse();
        List<UserAccount> studentWithoutCourse = new ArrayList<>();

        for (UserAccount student : allStudents) {
            if (student.getUserRole().size() == 1)
                studentWithoutCourse.add(student);
        }

        PageableUtil<UserAccount> pageableUtil = new PageableUtil<>(studentWithoutCourse, pageNo);
        List<UserAccount> showList = pageableUtil.getShowList();

        return new PageImpl<>(showList, pageableUtil.getPageable(), studentWithoutCourse.size());
    }

    @Override
    public Page<UserAccount> searchStudentWithoutCourse(String keyword, Integer pageNo) {
        List<UserAccount> allStudents = userAccountRepository.searchStudentWithoutCourseByKeyword(keyword);
        List<UserAccount> studentWithoutCourse = new ArrayList<>();

        for (UserAccount student : allStudents) {
            if (student.getUserRole().size() == 1)
                studentWithoutCourse.add(student);
        }

        PageableUtil<UserAccount> pageableUtil = new PageableUtil<>(studentWithoutCourse, pageNo);
        List<UserAccount> showList = pageableUtil.getShowList();

        return new PageImpl<>(showList, pageableUtil.getPageable(), studentWithoutCourse.size());
    }

    @Override
    public Page<UserAccount> findAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, SystemConstant.PAGE_SIZE);
        return userAccountRepository.findAll(pageable);
    }

    @Override
    public List<UserAccount> searchByKeyword(String keyword) {
        return userAccountRepository.searchUserAccount(keyword);
    }

    @Override
    public Page<UserAccount> searchByKeyword(String keyword, Integer pageNo) {
        List<UserAccount> userAccounts = userAccountRepository.searchUserAccount(keyword);

        PageableUtil<UserAccount> pageableUtil = new PageableUtil<>(userAccounts, pageNo);
        List<UserAccount> showList = pageableUtil.getShowList();

        return new PageImpl<>(showList, pageableUtil.getPageable(), userAccounts.size());
    }
}
