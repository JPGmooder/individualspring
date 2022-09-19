package com.individual.project.agenstvo.controllers;

import com.individual.project.agenstvo.models.Employee;
import com.individual.project.agenstvo.models.Product;
import com.individual.project.agenstvo.repos.EmployeeRepository;
import com.individual.project.agenstvo.repos.ProductRepository;
import com.individual.project.agenstvo.repos.UserRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ProductRepository repos;

    @Autowired
    private EmployeeRepository erepos;



    @GetMapping("/check")
    public String check(Model model)
    {

        model.addAttribute("orders", repos.findAll());
        return "manager/orders";
    }

    @GetMapping("/edit/{id}")
    public String editDoljnost(@PathVariable("id") Long id, Model model)
    {
        var curOrder = repos.findById(id).get();

        model.addAttribute("order", curOrder);
        var sotr = erepos.findAll();
        List<Employee> myList = new ArrayList<Employee>();
        for (var emp:sotr) {
            if (!curOrder.employees.contains(emp))
                myList.add(emp);
        }

        model.addAttribute("sotr", myList);
        model.addAttribute("confirm1", repos.findById(id).get().isAccepted ); //* тут бул нормально передается
        return "manager/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoljnost(@PathVariable("id") Long id)
    {
        repos.deleteById(id);
        return "redirect:/manager/check";
    }


    @PostMapping("/edit/{id}")
    public String editDoljnost(@PathVariable("id") Long id,
                               @RequestParam(value = "inWork", defaultValue = "false") boolean confirm1,
                               @RequestParam(value="action", required=true) String action,
                               @RequestParam(value = "selectilca", required=false) String sotrID,
                               Model model)
    {
        var currentOrder = repos.findById(id).get();


        if (action.equals("add"))
        {

            var currentSotr = erepos.findById(Long.parseLong(sotrID)).get();
            if (currentOrder.employees == null) currentOrder.employees = new HashSet<Employee>();
            currentOrder.employees.add(currentSotr);
            repos.save(currentOrder);
            return "redirect:/manager/edit/" + id;
        }
        if (action.equals("apply"))
        {
            currentOrder.isAccepted = confirm1;
            repos.save(currentOrder);
            return "redirect:/manager/check";
        }

        return "manager/edit";
    }

}
