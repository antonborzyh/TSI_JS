package com.tsieducation.repositories;

import com.tsieducation.entities.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RolesEntity, Integer> {
    RolesEntity findByName(String name);
}