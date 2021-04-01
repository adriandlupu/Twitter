package org.fiipractic.controller;

import org.fiipractic.dto.UserDTO;
import org.fiipractic.model.User;
import org.fiipractic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Component
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getAll(Model model) {
        List<UserDTO> users = userService.getAll();
        model.addAttribute("tableName", "List of all users:");
        model.addAttribute("users", users);
        return "list";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm) throws SQLException {
        userService.create(userForm);
        return "redirect:/user/";
    }

    @GetMapping("/{userName}")
    public String search(@PathVariable String userName, Model model) {
        UserDTO userDTO = userService.findByUserName(userName);
        List<UserDTO> users = Collections.singletonList(userDTO);
        model.addAttribute("tableName", "Details for user with userName: " + userName);
        model.addAttribute("users", users);
        return "list";
    }
    @RequestMapping("/delete")
    public Long deleteUser(long id){return userService.deleteUser(id);}
}