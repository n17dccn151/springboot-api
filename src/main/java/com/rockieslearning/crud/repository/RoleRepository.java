package com.rockieslearning.crud.repository;
import com.rockieslearning.crud.entity.Role;
import com.rockieslearning.crud.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
