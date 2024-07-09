package com.lynhatkhanh.educationweb.educationweb.dao;

import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
import org.h2.engine.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Integer>,
        PagingAndSortingRepository<UserAccount, Integer> {

    UserAccount findUserAccountByUserName(String userName);

    @Query("SELECT u FROM UserAccount u WHERE u.userName LIKE %?1%")
        // %?1% - RequestParam (1 param) - %: any-word before and after
    List<UserAccount> searchUserAccount(String keyword);

}
