package com.lynhatkhanh.educationweb.educationweb.dao.exclude;

import com.lynhatkhanh.educationweb.educationweb.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
