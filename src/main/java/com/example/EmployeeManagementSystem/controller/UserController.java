package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.model.User;
import com.example.EmployeeManagementSystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String webpage() {
        return "index";
    }

    @GetMapping("/displayList")
    public String getAllUsers(Model model) {
        List<User> details = userService.getAllUsers();
        model.addAttribute("details", details);
        return "database-display";
    }

    @GetMapping("/search")
    public String searchUser() {
        return "search";
    }

    @GetMapping("/userid")
    public String getUserById(@RequestParam("employeeId") Integer id, Model model) {
        Optional<User> employee = Optional.ofNullable(userService.getUserById(id));
        employee.ifPresent(users -> model.addAttribute("users", users));
        return "userById";
    }

    @GetMapping("/addEmployee")
    public String showForm(Model model) {
        model.addAttribute("users", new User());
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addUser(@Valid @ModelAttribute("users") User users, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addEmployee";
        }
        userService.addUser(users);
        return "redirect:/employee/displayList";
    }

    @GetMapping("/update")
    public String updateForm(Model model) {
        model.addAttribute("users", new User());
        return "id-request-update";
    }

    @GetMapping("/updateDetails")
    public String updateDetailsByID(@RequestParam Integer id, Model model) {
        Optional<User> emp = Optional.ofNullable(userService.getUserById(id));
        if (emp.isPresent()) {
            model.addAttribute("users", emp.get());
            return "update-user";
        }
        return "redirect:/employee/displayList";
    }

    @GetMapping("/delete")
    public String deletionForm(Model model) {
        model.addAttribute("users", new User());
        return "id-request-delete";
    }

    @GetMapping("/deleteDetails")
    public String deleteDetailsByID(@RequestParam("id") Integer id, Model model) {
        Optional<User> exist = Optional.ofNullable(userService.getUserById(id));
        if (exist.isPresent()) {
            model.addAttribute("users", exist.get());
            return "user-deleted";
        }
        return "redirect:/employee/displayList";
    }

    @PostMapping("/updateDetails/{id}")
    public String updateDetails(@PathVariable Integer id, @Valid @ModelAttribute("users") User users, BindingResult result) {
        if (result.hasErrors()) {
            return "update-user";
        }
        userService.updateDetails(id, users);
        return "redirect:/employee/displayList";
    }

    @PostMapping("/deleteDetails/{id}")
    public String deleteUser(@PathVariable Integer id) {
        Optional<User> existing = Optional.ofNullable(userService.getUserById(id));
        if (existing.isPresent()) {
            userService.deleteUser(id);
        }
        return "redirect:/employee/displayList";
    }
}
