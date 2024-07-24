package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User addUser(User users);

    public User getUserById(Integer id);

    public void deleteUser(Integer id);

    public User updateDetails(Integer id,User users);
}
