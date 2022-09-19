package com.individual.project.agenstvo.controllers;


import com.individual.project.agenstvo.logic.CurrentUser;
import com.individual.project.agenstvo.models.*;
import com.individual.project.agenstvo.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PassportRepository passportRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UrDataRepository urDataRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientPersonalDataRepository ClientPersonalDataRepository;
    @GetMapping("/registration")
    public String reg_view(Model model)
    {
        return "registration";
    }

    @GetMapping("/to_login")
    public String tologin(Model model)
    {
        CurrentUser.ResetSettings();

        return "redirect:/login";
    }

    @PostMapping("/registration")
    public String reg_action(User user, Model model)
    {

        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null)
        {
            model.addAttribute("error", "Такой пользователь уже существует");
            return "registration";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        CurrentUser.getInstance();
        CurrentUser.getInstance().user = user;
        return "redirect:/regpas";
    }


    @GetMapping("/regpas")
    public String reg_passportget(Model model, Passport passport)
    {
        if (CurrentUser.getInstance().user == null)
        {
            return "redirect:/to_login";
        }
        model.addAttribute("error", passport);
        return "reg_passport";
    }

    @PostMapping("/regpas")
    public String reg_passportpost(@ModelAttribute @Valid Passport passport, BindingResult bindingResult,  Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("error", passport);
            return  "reg_passport";
        }
        CurrentUser.getInstance().passport = passport;
        return "redirect:/regper";
    }

    @GetMapping("/regper")
    public String reg_perget(Model model)
    {
        if (CurrentUser.getInstance().user == null || CurrentUser.getInstance().passport == null)
        {
            return "redirect:/to_login";
        }
        return "reg_personal";
    }

    @PostMapping("/regper")
    public String reg_perpost(@ModelAttribute @Valid Person person, BindingResult bindingResult,  Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("error", person);
            return  "reg_passport";
        }
        CurrentUser.getInstance().person = person;
        return "redirect:/regaddress";
    }

    @GetMapping("/regaddress")
    public String reg_address(Model model)
    {
        if (CurrentUser.getInstance().person == null)
        {
            return "redirect:/to_login";
        }

        return "reg_address";
    }

    @PostMapping("/regaddress")
    public String reg_address(@ModelAttribute @Valid Address address, BindingResult bindingResult,  Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("error", address);
            return  "reg_address";
        }
        CurrentUser.getInstance().address = address;
        return "redirect:/regcpd";
    }

    @GetMapping("/regcpd")
    public String reg_cpd(Model model)
    {
        if (CurrentUser.getInstance().address == null)
        {
            return "redirect:/to_login";
        }

        return "reg_cpd";
    }

    @PostMapping("/regcpd")
    public String reg_cpd(@ModelAttribute @Valid ClientPersonalData cpd, BindingResult bindingResult,  Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("error", cpd);
            return  "reg_cpd";
        }
        CurrentUser.getInstance().cpd = cpd;
        return "redirect:/regbuisness";
    }


    @GetMapping("/regbuisness")
    public String reg_buisness(Model model)
    {
        if (CurrentUser.getInstance().cpd == null)
        {
            return "redirect:/to_login";
        }

        return "reg_buisness";
    }

    @PostMapping("/regbuisness")
    public String reg_buisness(@ModelAttribute @Valid UrData urdata, BindingResult bindingResult,  Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("error", urdata);
            return  "reg_buisness";
        }
        CurrentUser.getInstance().urData = urdata;
        userRepository.save(CurrentUser.getInstance().user);
        passportRepository.save(CurrentUser.getInstance().passport);
        addressRepository.save(CurrentUser.getInstance().address);
        CurrentUser.getInstance().person.passport = CurrentUser.getInstance().passport;
        personRepository.save(CurrentUser.getInstance().person);
        urDataRepository.save(CurrentUser.getInstance().urData);
        CurrentUser.getInstance().cpd.adressData = CurrentUser.getInstance().address;
        CurrentUser.getInstance().cpd.ClientUrData = CurrentUser.getInstance().urData;
        ClientPersonalDataRepository.save(CurrentUser.getInstance().cpd);
        Client currentClient = new Client();
        currentClient.user = CurrentUser.getInstance().user;
        currentClient.personalData = CurrentUser.getInstance().cpd;
        currentClient.person = CurrentUser.getInstance().person;
        clientRepository.save(currentClient);
        CurrentUser.getInstance().client = currentClient;
        return "redirect:login";
    }





//
//    @PostMapping("/registration/cpd")
//    public String reg_cpd(@ModelAttribute @Valid ClientPersonalData data, BindingResult bindingResult,  Model model)
//    {
//        if (bindingResult.hasErrors())
//        {
//            model.addAttribute("error", data);
//            return  "registration";
//        }
//
//        return "registration";
//    }
//
//    @PostMapping("/registration/address")
//    public String reg_address(@ModelAttribute @Valid Address address, BindingResult bindingResult,  Model model)
//    {
//        if (bindingResult.hasErrors())
//        {
//            model.addAttribute("error", address);
//            return  "registration";
//        }
//        return "registration";
//    }
//
//    @PostMapping("/registration/urdata")
//    public String reg_urData(@ModelAttribute @Valid UrData urData, BindingResult bindingResult,  Model model)
//    {
//        if (bindingResult.hasErrors())
//        {
//            model.addAttribute("error", urData);
//            return  "registration";
//        }
//        return "registration";
//    }
//
//    @PostMapping("/personal")
//    public String reg_personal(@ModelAttribute @Valid Person person, BindingResult bindingResult,  Model model)
//    {
//        if (bindingResult.hasErrors())
//        {
//            model.addAttribute("error", person);
//            return  "registration";
//        }
//
//        return "registration";
//    }
//
//

}
