package com.lynhatkhanh.educationweb.educationweb.dao.exclude;

import com.lynhatkhanh.educationweb.educationweb.model.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {

//    @Modifying
//    @Query(value = "INSERT INTO UserRole (role, userAccount) VALUES (:roleId, :userId)", nativeQuery = true)
//    @Transactional
//    void saveWithStudentRole(int roleId, int userId);

    @Query("SELECT ur FROM UserRole ur JOIN FETCH ur.role WHERE ur.id=:userRoleId")
    UserRole findUserRoleAndRoleWithUserRoleId(@Param("userRoleId") int userRoleId);
}
