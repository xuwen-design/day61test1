package com.controller;

import com.pojo.User;
import com.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DemoUser {
    @Autowired
    private userService userService;
    @RequestMapping("/user/list")
    public String showALL(Model model)
    {
        List<User> users = userService.showAll();
        model.addAttribute("users",users);
        return "showlist";
    }
}
