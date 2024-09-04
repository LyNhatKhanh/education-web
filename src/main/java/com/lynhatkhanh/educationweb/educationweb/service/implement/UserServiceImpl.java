package com.lynhatkhanh.educationweb.educationweb.service.implement;

import com.lynhatkhanh.educationweb.educationweb.dao.RoleRepository;
import com.lynhatkhanh.educationweb.educationweb.dao.UserRepository;
import com.lynhatkhanh.educationweb.educationweb.dto.request.UserCreationRequest;
import com.lynhatkhanh.educationweb.educationweb.entity.Role;
import com.lynhatkhanh.educationweb.educationweb.entity.User;
import com.lynhatkhanh.educationweb.educationweb.exception.AppException;
import com.lynhatkhanh.educationweb.educationweb.exception.ErrorCode;
import com.lynhatkhanh.educationweb.educationweb.mapper.UserMapper;
import com.lynhatkhanh.educationweb.educationweb.service.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements IUserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    UserMapper userMapper;
    RoleRepository roleRepository;

    @Override
    public User createUser(UserCreationRequest request) {

        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<Role> roles = new HashSet<>();
        roleRepository.findAllById(request.getRoles()).forEach(role -> roles.add(role));
        user.setRoles(roles);

        /*TODO: set BaseEntity*/

        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
