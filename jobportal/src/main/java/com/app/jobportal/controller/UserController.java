package com.app.jobportal.controller;


import com.app.jobportal.entity.User;
import com.app.jobportal.entity.UserType;
import com.app.jobportal.service.UserService;
import com.app.jobportal.service.UserTypeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private UserTypeService userTypeService;
    private UserService userService;

    public UserController(UserTypeService userTypeService,UserService userService)
    {
        this.userTypeService=userTypeService;
        this.userService=userService;
    }

    @GetMapping("/register")
    public String register(Model model)
    {
        List<UserType> userTypes=userTypeService.getAllTypes();
        model.addAttribute("getAllTypes",userTypes);
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/register/new")
    public String userRegistration(@Valid User user,Model model)
    {
        Optional<User> user1=userService.getUserBYEmail(user.getEmail());
        if(user1.isPresent())
        {
            model.addAttribute("error","email already exists, try to login or register with other email id");
            List<UserType> userTypes=userTypeService.getAllTypes();
            model.addAttribute("getAllTypes",userTypes);
            model.addAttribute("user",new User());
            return "register";
        }
        userService.addNewUser(user);
        return "dashboard";
    }


}
