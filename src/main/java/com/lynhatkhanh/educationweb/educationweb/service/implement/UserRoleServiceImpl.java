package com.lynhatkhanh.educationweb.educationweb.service.implement;

import com.lynhatkhanh.educationweb.educationweb.dao.UserRoleRepository;
import com.lynhatkhanh.educationweb.educationweb.model.UserRole;
import com.lynhatkhanh.educationweb.educationweb.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<UserRole> findAll() {
        return (List<UserRole>) userRoleRepository.findAll();
    }

    @Override
    public UserRole findById(int id) {
        Optional<UserRole> result = userRoleRepository.findById(id);
        UserRole userRole = null;

        if (result.isPresent())
            userRole = result.get();
        else
            throw new RuntimeException("UserRole of user_id: " + id + " is not found!");

        return userRole;
    }

    @Override
    public UserRole findUserRoleAndRoleByUserRoleId(int theId) {
        return userRoleRepository.findUserRoleAndRoleWithUserRoleId(theId);
    }

    @Override
    public void save(UserRole userRole) {
        userRoleRepository.save(userRole);
    }
}
