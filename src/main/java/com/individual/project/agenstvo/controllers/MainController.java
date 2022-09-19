package com.individual.project.agenstvo.controllers;

import com.individual.project.agenstvo.logic.CurrentUser;
import com.individual.project.agenstvo.repos.ClientRepository;
import com.individual.project.agenstvo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class MainController {
    @Autowired
    private UserRepository repos;
    @Autowired
    private ClientRepository clientRepos;

    @GetMapping("/index")
    public String reg_view(Model model)
    {
        if (SecurityContextHolder.getContext().getAuthentication() != null && CurrentUser.getInstance().user == null)
        {
            CurrentUser.ResetSettings();
            var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            var userName = ((UserDetails)principal).getUsername();
            var currentUser = repos.findByUsername(userName);

            CurrentUser.getInstance();
            CurrentUser.getInstance().user = currentUser;
            var orders = clientRepos.findByUser(currentUser);
            CurrentUser.getInstance().client =orders;
            var bebra = CurrentUser.getInstance().client;
        }
        return "home/index";
    }
}
