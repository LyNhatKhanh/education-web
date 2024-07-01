package com.lynhatkhanh.educationweb.educationweb.service.implement;

import com.lynhatkhanh.educationweb.educationweb.dao.UserAccountRepository;
import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
import com.lynhatkhanh.educationweb.educationweb.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount findByUserName(String userName) {
        return userAccountRepository.findByUserName(userName);
    }
}
