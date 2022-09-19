package com.individual.project.agenstvo.controllers;

import com.individual.project.agenstvo.logic.CurrentUser;
import com.individual.project.agenstvo.models.*;
import com.individual.project.agenstvo.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserRepository repos;

    @Autowired
    private DoljnostRepository drepos;

    @Autowired
    private EmployeeRepository erepos;

    @Autowired
    private FilialRepository frepos;

    @Autowired
    private AddressRepository arepos;

    @Autowired
    private PassportRepository pasRepos;

    @Autowired
    private PersonRepository perRepos;


    @GetMapping("/index")
    public String reg_view(Model model)
    {
        return "admin/index";
    }

    @GetMapping("/check")
    public String checkUsers(Model model)
    {
        model.addAttribute("users", repos.findAll());
        return "admin/users/check";
    }


    @GetMapping("/doljnost/add")
    public String addDoljnost(Model model, Doljnost doljnost)
    {
        model.addAttribute("doljnost", doljnost);
        return "admin/doljnost/add";
    }

    @PostMapping("/doljnost/add")
    public String createDoljnost(@ModelAttribute @Valid Doljnost doljnost, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors() || drepos.findByDoljnostName(doljnost.doljnostName) != null)
        {
            model.addAttribute("error", drepos.findByDoljnostName(doljnost.doljnostName) != null? "Нельзя добавить данную должность, так как таковая уже существует" : bindingResult.getAllErrors());
            return "admin/doljnost/add";
        }
        drepos.save(doljnost);
        return "redirect:/admin/checkdolj";
    }

    @GetMapping("/checkdolj")
    public String checkDoljnost(Model model)
    {
        model.addAttribute("doljnost", drepos.findAll());
        return "admin/doljnost/check";
    }


    @GetMapping("/edit/doljnost/{id}")
    public String editDoljnost(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("doljnost", drepos.findById(id));
        return "admin/doljnost/edit";
    }


    @PostMapping("/edit/doljnost/{id}")
    public String editDoljnost(@ModelAttribute @Valid Doljnost doljnost, @PathVariable("id") Long id, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("error",  bindingResult.getAllErrors());
            return "admin/doljnost/edit";
        }
       var currentDolj = drepos.findById(id).get();
        currentDolj.doljnostName = doljnost.doljnostName;
        currentDolj.Salary = doljnost.Salary;
        drepos.save(currentDolj);
        return "redirect:/admin/checkdolj";
    }

    @GetMapping("/delete/doljnost/{id}")
    public String deleteDoljnost(@PathVariable("id") Long id, Model model)
    {
        drepos.deleteById(id);
        return "admin/doljnost/check";
    }


    @GetMapping("/search")
    String searchFlex(Model model, @RequestParam(value = "searcher", required = false) String text) {
        var users = repos.findByUsernameContains(text);
        model.
                addAttribute("users", users);
        return "/admin/users/check";
    }

    @PostMapping("/edit/{id}")
    public String checkUsers(@ModelAttribute @Valid User user, Model model, @RequestParam(value="action", required=true) String action)
    {
        if (action.equals("change"))
        {
            var currentUser = repos.findById(user.getId()).get();
            currentUser.roles = user.roles;
            currentUser.password = user.password;
            currentUser.username = user.username;
            repos.save(currentUser);
        }
        else if (action.equals("delete"))
        {
           repos.deleteById(user.getId());
        }
        model.addAttribute("users", repos.findAll());

        return "admin/users/check";
    }

    @GetMapping("/filial/check")
    public String checkFilial(Model model)
    {
        model.addAttribute("filial", frepos.findAll());
        return "admin/filial/check";
    }


    @GetMapping("/filial/edit/{id}")
    public String checkFilial(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("filial", frepos.findById(id));
        model.addAttribute("address", arepos.findAll());
        return "admin/filial/edit";
    }

    @GetMapping("/filial/search")
    String searchFilial(Model model, @RequestParam(value = "searcher", required = false) String text) {
        var users = frepos.findByFilialNameContains(text);
        model.
                addAttribute("filial", users);
        return "admin/filial/check";
    }


    @GetMapping("/filial/delete/{id}")
    public String deleteFilial(@PathVariable("id") Long id, Model model)
    {
        frepos.deleteById(id);
        model.addAttribute("filial", frepos.findById(id));
        return "admin/filial/check";
    }

    @PostMapping("/filial/edit/{id}")
    public String checkFilial(@PathVariable("id") Long id, @ModelAttribute @Valid Filial filial,
                              @RequestParam(value = "address") String addressID,
                              BindingResult bindingResult,
                              Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("error",  bindingResult.getAllErrors());
            return "admin/filial/edit";
        }
        var currentFilial = frepos.findById(id).get();
        currentFilial.address = arepos.findById(Long.parseLong( addressID)).get();
        currentFilial.filialName = filial.filialName;
        frepos.save(currentFilial);
        return "redirect:/admin/filial/check";
    }


    @GetMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id,
                              Model model)
    {
        erepos.deleteById(id);
        return "redirect:/admin/employee/check";
    }

    @GetMapping("/filial/add_address")
    public String addEmployee(Model model, Address address)
    {
       model.addAttribute("address", address);
        return "admin/filial/reg_address";
    }

    @PostMapping("/filial/add_address")
    public String addEmployee(@ModelAttribute @Valid Address address,
                              BindingResult bindingResult,
                              Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("error",  bindingResult.getAllErrors());
            return "admin/filial/reg_address";
        }
        CurrentUser.getInstance().address = address;
        return "redirect:/admin/filial/add_filial";
    }

    @GetMapping("/filial/add_filial")
    public String addEmployee(Model model, Filial filial)
    {
        model.addAttribute("filial", filial);
        return "admin/filial/reg_filial";
    }

    @PostMapping("/filial/add_filial")
    public String addEmployee(@ModelAttribute @Valid Filial filial,
                              BindingResult bindingResult,
                              Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("error",  bindingResult.getAllErrors());
            return "admin/filial/reg_filial";
        }
        filial.address = CurrentUser.getInstance().address;

        frepos.save(filial);
        return "redirect:/admin/filial/check";
    }



    @GetMapping("/employee/check")
    public String checkEmployee(Model model)
    {
        model.addAttribute("employee", erepos.findAll());
        return "admin/employee/check";
    }

    @GetMapping("/employee/add")
    public String addEmployee(Model model, Passport passport)
    {
        model.addAttribute("passport", passport);
        return "admin/employee/reg_pas";
    }

    @PostMapping("/employee/add_passport")
    public String addEmployee(@ModelAttribute @Valid Passport passport,
                              BindingResult bindingResult,
                              Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("error",  bindingResult.getAllErrors());
            return "admin/employee/reg_pas";
        }
        CurrentUser.getInstance().passport = passport;
        return "redirect:/admin/employee/add_personal";
    }

    @GetMapping("/employee/add_personal")
    public String addEmployee(Model model, Person person)
    {
        model.addAttribute("person", person);
        model.addAttribute("doljnost", drepos.findAll());
        model.addAttribute("filial", frepos.findAll());
        return "admin/employee/reg_personal";
    }

    @PostMapping("/employee/reg_personal")
    public String addEmployee(@ModelAttribute @Valid Person person,
                              @RequestParam(value = "doljnost") String doljnostID,
                              @RequestParam(value = "filial") String filialID,
                              BindingResult bindingResult,
                              Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("error",  bindingResult.getAllErrors());
            return "admin/employee/reg_pas";
        }

        var filial = frepos.findById(Long.parseLong( filialID)).get();
        var doljnost = drepos.findById(Long.parseLong(doljnostID)).get();
        CurrentUser.getInstance().filial = filial;
        CurrentUser.getInstance().doljnost = doljnost;
        CurrentUser.getInstance().person = person;
        return "redirect:/admin/employee/add_user";
    }

    @GetMapping("/employee/add_user")
    public String addEmployee(Model model, User user)
    {
        model.addAttribute("user", user);
        return "admin/employee/reg_user";
    }

    @PostMapping("/employee/add_user")
    public String addEmployee(@ModelAttribute @Valid User user,
                              BindingResult bindingResult,
                              Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("error",  bindingResult.getAllErrors());
            return "admin/employee/reg_user";
        }
        var curPas = CurrentUser.getInstance().passport;
       pasRepos.save(curPas);
        var curPerson = CurrentUser.getInstance().person;
        perRepos.save(curPerson);
        var curUser = user;
        repos.save(curUser);

        var curEmployee = new Employee();
        curEmployee.person = curPerson;
        curEmployee.user = curUser;
        curEmployee.filial = CurrentUser.getInstance().filial;
        curEmployee.doljnost = CurrentUser.getInstance().doljnost;;
        erepos.save(curEmployee);
        return "redirect:/admin/employee/check";
    }











}
