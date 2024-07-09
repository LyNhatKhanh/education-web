package com.lynhatkhanh.educationweb.educationweb.service.implement;

import com.lynhatkhanh.educationweb.educationweb.dao.UserAccountRepository;
import com.lynhatkhanh.educationweb.educationweb.exception.DuplicateUsernameException;
import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
import com.lynhatkhanh.educationweb.educationweb.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount findByUserName(String userName) {
        return userAccountRepository.findUserAccountByUserName(userName);
    }

    @Override
    public List<UserAccount> findAll() {
        return (List<UserAccount>) userAccountRepository.findAll();
    }

    @Override
    public UserAccount findById(int userId) {
        Optional<UserAccount> result = userAccountRepository.findById(userId);

        UserAccount userAccount = null;
        if (result.isPresent())
            userAccount = result.get();
        else
            throw new RuntimeException("User is not found - " + userId);

        return userAccount;
    }

    @Override
    public void save(UserAccount userAccount) throws DuplicateUsernameException {
        try {
            userAccountRepository.save(userAccount);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateUsernameException("Username already exist!");
        }
    }

    @Override
    public Page<UserAccount> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1, 5);

        return userAccountRepository.findAll(pageable);
    }

    @Override
    public Page<UserAccount> searchUser(String keyword, Integer pageNo) {
        List<UserAccount> listUsers = userAccountRepository.searchUserAccount(keyword);

        Pageable pageable = PageRequest.of(pageNo-1,5);

        // offset: start at [x] index of Page list (page instance) - like PageNo
        // limit: after return list, take [x] results of list - like PageSize

        Integer start = (int) pageable.getOffset();
        Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > listUsers.size() ? listUsers.size() : (pageable.getOffset() + pageable.getPageSize()));

        listUsers = listUsers.subList(start,end);


        return new PageImpl<>(listUsers, pageable, userAccountRepository.searchUserAccount(keyword).size());
    }
}
