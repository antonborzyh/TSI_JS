package com.tsieducation.services.impl;

import com.tsieducation.dto.UserDto;
import com.tsieducation.entities.RolesEntity;
import com.tsieducation.entities.UsersEntity;
import com.tsieducation.entities.constants.Role;
import com.tsieducation.repositories.RoleRepository;
import com.tsieducation.repositories.UserRepository;
import com.tsieducation.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UsersEntity createUser(UsersEntity usersEntity) {
        return userRepository.save(usersEntity);
    }

    @Override
    public void createUser(UserDto userDto) {
        RolesEntity role = roleRepository.findByName(Role.CLIENT.getSpringSecValue());

        if (role == null)
            role = roleRepository.save(new RolesEntity(Role.CLIENT.getSpringSecValue()));

        UsersEntity user = new UsersEntity(userDto.getUserName(), userDto.getFirstName(), userDto.getSecondName(),
                Date.valueOf(LocalDate.parse(userDto.getDateOfBirth())), passwordEncoder.encode(userDto.getPassword()),
                List.of(role));
        userRepository.save(user);
    }

    @Override
    public UsersEntity getUserById(Integer userId) {
        Optional<UsersEntity> optionalUser = userRepository.findById(userId);
        return optionalUser
                .orElseThrow(() -> new IllegalArgumentException(String.format("No user found with id - %d.", userId)));
    }

    @Override
    public List<UsersEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UsersEntity updateUser(UsersEntity usersEntity) {
        UsersEntity existingUsersEntity = getUserById(usersEntity.getUserId());
        existingUsersEntity.setUserName(usersEntity.getUserName());
        existingUsersEntity.setFirstName(usersEntity.getFirstName());
        existingUsersEntity.setLastName(usersEntity.getLastName());
        existingUsersEntity.setDateOfBirth(usersEntity.getDateOfBirth());

        return userRepository.save(existingUsersEntity);
    }

    @Override
    public UsersEntity getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }


    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }
}
