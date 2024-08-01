package com.lynhatkhanh.educationweb.educationweb.dao;

import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Integer>,
        PagingAndSortingRepository<UserAccount, Integer> {

    UserAccount findUserAccountByUserName(String userName);

    @Query("SELECT u FROM UserAccount u " +
            "JOIN FETCH u.userRole " +
            "WHERE u.userName=:userName")
    UserAccount findUserAccountAndRoleByUserName(@Param("userName") String userName);

    @Query("SELECT u FROM UserAccount u WHERE u.userName LIKE %?1%")
        // %?1% - RequestParam (1 param) - %: any-word before and after
    List<UserAccount> searchUserAccount(String keyword);

    @Query("SELECT u FROM UserAccount AS u " +
            "INNER JOIN UserRole AS ur ON u.id = ur.userAccount.id " +
            "WHERE ur.role.id = :roleId")
    List<UserAccount> findUserAccountOfRole(@Param("roleId") int roleId);

    @Query("SELECT u FROM UserAccount AS u " +
            "INNER JOIN UserRole AS ur ON u.id = ur.userAccount.id " +
            "WHERE ur.role.id = :roleId AND u.userName LIKE %:keyword%")
    List<UserAccount> searchUserAccountOfRole(@Param("keyword") String keyword, @Param("roleId") int roleId);

    @Query("SELECT u FROM UserAccount AS u " +
            "INNER JOIN CourseUser AS cs ON u.id = cs.userAccount.id " +
            "WHERE cs.course.id = :courseId")
    List<UserAccount> findUserAccountOfCourse(@Param("courseId") int courseId);

    @Query("SELECT s FROM UserAccount AS s " +
            "INNER JOIN UserRole AS ur ON s.id = ur.userAccount.id " +
            "WHERE ur.role.id = 2 AND s.enrolledCourses IS EMPTY")
    List<UserAccount> findStudentWithoutCourse();

}
