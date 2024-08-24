package com.example.one_manage.service;

import com.example.one_manage.entity.User;
import com.example.one_manage.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository UserRepository;
    public List<User> getAllUsers() {
        return UserRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return UserRepository.findById(id);
    }


    public Optional<User> getUserByUserName(String username) {
        return Optional.ofNullable(UserRepository.findByName(username));
    }


    public User createUser(User user) {
        return UserRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = UserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setPhone(userDetails.getPhone());
        user.setAddress(userDetails.getAddress());

        return UserRepository.save(user);
    }

    public void deleteUser(Long id) {
        UserRepository.deleteById(id);
    }


    @Transactional
    public void updateShowedNoti(String username, boolean showedNoti) {
        UserRepository.updateShowedNotiByUsername(showedNoti, username);
    }


}
