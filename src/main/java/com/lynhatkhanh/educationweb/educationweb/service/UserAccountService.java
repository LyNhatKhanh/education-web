package com.lynhatkhanh.educationweb.educationweb.service;

import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;

public interface UserAccountService {

    UserAccount findByUserName(String userName);
}
