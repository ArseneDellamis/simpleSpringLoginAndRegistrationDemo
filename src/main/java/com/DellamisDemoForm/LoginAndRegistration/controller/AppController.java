package com.DellamisDemoForm.LoginAndRegistration.controller;

import com.DellamisDemoForm.LoginAndRegistration.dao.UserRepository;
import com.DellamisDemoForm.LoginAndRegistration.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {
    // this will handle methods to show the home page

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String ViewHomePage(){
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return "register_success";
    }

    @GetMapping("/users")
    public String listUsers(Model model){
        List<User> listUsers=userRepository.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }
}
