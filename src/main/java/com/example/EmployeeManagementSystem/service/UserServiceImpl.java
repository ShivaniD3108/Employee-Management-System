package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.example.EmployeeManagementSystem.model.User;
import com.example.EmployeeManagementSystem.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;
    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User addUser(User users) {
        return userRepo.save(users);
    }
    @Override
    public User getUserById(Integer id) {
        try{
            return userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
        }catch(ResourceNotFoundException e){
            System.out.println(e.getMessage());
            throw e;
        }
            }

    @Override
    public void deleteUser(Integer id) {
        User user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));;
        userRepo.delete(user);
    }

    @Override
    public User updateDetails(Integer id, User users) {
        User updatedDetails=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));;
//        updatedDetails.setId(users.getId());
        updatedDetails.setFirstName(users.getFirstName());
        updatedDetails.setLastName(users.getLastName());
        updatedDetails.setEmail(users.getEmail());
        return userRepo.save(updatedDetails);
    }
}
