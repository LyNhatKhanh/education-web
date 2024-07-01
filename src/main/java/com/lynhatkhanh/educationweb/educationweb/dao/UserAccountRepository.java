package com.lynhatkhanh.educationweb.educationweb.dao;

import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Integer> {


    UserAccount findByUserName(String userName);
}
