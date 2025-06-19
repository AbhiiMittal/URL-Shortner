package com.url.shortner.tinyurl.service;

import com.url.shortner.tinyurl.model.User;
import com.url.shortner.tinyurl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getUserList() {
        try{
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserDetails(Long id) {
        try{
            Optional<User> optionalUser = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found")));
            return optionalUser.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String setUserDetails(User user) {
        try{
            Optional<User> optionalUser = Optional.ofNullable(userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("user not found")));
            User updatedUser = optionalUser.get();
            updatedUser.setName(user.getName());
            userRepository.save(updatedUser);
            return "updated successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String createNewUser(User user) {
        try{
            userRepository.save(user);
            return "user created successfully";
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public String deleteUser(Long id) {
        try{
            Optional<User> optionalUser = userRepository.findById(id);
            if(optionalUser.isEmpty()){
                throw new RuntimeException("User Does Not Exists");
            }
            User user = optionalUser.get();
            userRepository.delete(user);
            return "user deleted successfully";
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
