package com.hbsis.http.repository;

import java.util.Optional;

import com.hbsis.http.model.Role;
import com.hbsis.http.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
