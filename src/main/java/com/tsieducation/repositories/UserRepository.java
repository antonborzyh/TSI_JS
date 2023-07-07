package com.tsieducation.repositories;

import com.tsieducation.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UsersEntity, Integer> {

    UsersEntity findByUserName(String userName);

}
