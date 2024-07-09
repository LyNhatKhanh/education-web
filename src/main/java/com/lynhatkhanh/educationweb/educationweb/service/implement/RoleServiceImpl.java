package com.lynhatkhanh.educationweb.educationweb.service.implement;

import com.lynhatkhanh.educationweb.educationweb.dao.RoleRepository;
import com.lynhatkhanh.educationweb.educationweb.model.Role;
import com.lynhatkhanh.educationweb.educationweb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        this.roleRepository = repository;
    }

    @Override
    public List<Role> findAll() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public Role findById(int theId) {
        Optional<Role> result = roleRepository.findById(theId);
        Role theRole = null;

        if (result.isPresent())
            theRole = result.get();
        else
            throw new RuntimeException("Role of role_id: " + theId + " is not found!");
        return theRole;
    }
}
