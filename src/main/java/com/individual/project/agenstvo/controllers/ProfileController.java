package com.individual.project.agenstvo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @GetMapping("/check")
    String index(Model model) {

        model.addAttribute("");
        return "home/profile/checkprofile";
    }


}
