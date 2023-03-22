package com.example.service;

import com.example.config.PasswordConfig;
import com.example.entity.RoleEntity;
import com.example.entity.UserEntity;
import com.example.repository.RoleEntityRepository;
import com.example.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityService {
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private PasswordConfig passwordConfig;
    @Autowired
    private RoleEntityRepository roleEntityRepository;

    public Optional<UserEntity> getByUsername(String username) {
        return userEntityRepository.getByUsername(username);
    }

    public boolean saveUser(UserEntity userEntity) {
        RoleEntity userRole = roleEntityRepository.getByRole("ROLE_USER");
        userEntity.setRole(userRole);
        userEntity.setPassword(passwordConfig.passwordEncoder().encode(userEntity.getPassword()));
        userEntityRepository.save(userEntity);
        return true;
    }

    public Optional<UserEntity> getByUsernameAndPassword(UserEntity user) {
        Optional<UserEntity> userEntity = getByUsername(user.getUsername());
        if (userEntity.isPresent()) {
            if (passwordConfig.passwordEncoder().matches(user.getPassword(), userEntity.get().getPassword())) {
                return userEntity;
            } else {
                throw new UsernameNotFoundException(user.getPassword());
            }
        } else {
            throw new UsernameNotFoundException(user.getUsername());
        }
    }
}
