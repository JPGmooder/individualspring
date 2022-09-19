package com.individual.project.agenstvo.controllers;

import com.individual.project.agenstvo.logic.CurrentUser;
import com.individual.project.agenstvo.models.Product;
import com.individual.project.agenstvo.repos.ClientRepository;
import com.individual.project.agenstvo.repos.ProductRepository;
import com.individual.project.agenstvo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    ProductRepository repository;

    @Autowired
    UserRepository repos;

    @Autowired
    ClientRepository clientRepos;

    @GetMapping("/index")
    public String check(Model model)
    {

        return "employee/index";
    }

    @GetMapping("/orders")
    public String checkOrders(Model model)
    {
        var currentOrders = repository.findAll();
        Set<Product> currentProducts = new HashSet<Product>();
        for (var order:currentOrders) {
            if (order.isAccepted)
            {
            for (var emp : order.employees)
            {
                if (CurrentUser.getInstance().user.getId() == emp.user.getId() )
                {
                    currentProducts.add(order);
                }
            }
            }
        }

        model.addAttribute("orders", currentProducts);
        return "employee/orders";
    }
}
