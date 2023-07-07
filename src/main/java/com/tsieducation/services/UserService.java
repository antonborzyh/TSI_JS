package com.tsieducation.services;

import com.tsieducation.dto.UserDto;
import com.tsieducation.entities.UsersEntity;

import java.util.List;


public interface UserService {

    UsersEntity createUser(UsersEntity usersEntity);
    void createUser(UserDto userDto);

    UsersEntity getUserById(Integer userId);

    List<UsersEntity> getAllUsers();

    UsersEntity updateUser(UsersEntity usersEntity);

    UsersEntity getUserByUserName(String userName);

    void deleteUser(int userId);

}
