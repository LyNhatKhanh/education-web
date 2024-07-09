package com.lynhatkhanh.educationweb.educationweb.dao;

import com.lynhatkhanh.educationweb.educationweb.model.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {

//    @Modifying
//    @Query(value = "INSERT INTO UserRole (role, userAccount) VALUES (:roleId, :userId)", nativeQuery = true)
//    @Transactional
//    void saveWithStudentRole(int roleId, int userId);
}
