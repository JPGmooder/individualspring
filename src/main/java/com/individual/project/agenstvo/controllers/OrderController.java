package com.individual.project.agenstvo.controllers;


import com.individual.project.agenstvo.logic.CurrentUser;
import com.individual.project.agenstvo.models.Product;
import com.individual.project.agenstvo.repos.PeriodRepository;
import com.individual.project.agenstvo.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/add")
    public String reg_view(Model model, Product product)
    {
        model.addAttribute("product", product);

        return "home/orders/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute @Valid Product order,
                        BindingResult bindingResult, Model model)
    {

      if (bindingResult.hasErrors())
      {
          model.addAttribute("error", bindingResult.getAllErrors());
          return  "home/orders/add";
      }

      if (!order.period.getPeriodEnd().after(order.period.getPeriodStart()))
      {
          model.addAttribute("error", "Невозможно выставить данную дату, повторите попытку");
          return  "home/orders/add";
      }
        Product currentOrder = new Product();
        currentOrder.client = CurrentUser.getInstance().client;
        var period = order.period;
        periodRepository.save(period);
        currentOrder.period = order.period;
        currentOrder.productPrice = order.productPrice;
        currentOrder.productDescription = order.productDescription;
        currentOrder.client = CurrentUser.getInstance().client;
        productRepository.save(currentOrder);
        return "home/index";
    }


    @GetMapping("/check")
    public String check( Model model)
    {
        model.addAttribute("orders", productRepository.findAllByClient(CurrentUser.getInstance().client));
        return "home/orders/check";
    }

    @GetMapping("/search")
    public String search( Model model, @RequestParam(value = "searcher", required = false) String text)
    {
        model.addAttribute("orders", productRepository.findAllByProductDescriptionContains(text));
        return "home/orders/check";
    }

    @PostMapping("/edit/{id}")
    public String check(@ModelAttribute @Valid Product product, @PathVariable("id") Long id, BindingResult bindingResult, Model model, @RequestParam(value="action", required=true) String action)
    {

        if (bindingResult.hasErrors())
        {
            model.addAttribute("error", bindingResult.getAllErrors());
            return  "redirect:/check";
        }

        if (action.equals("change"))
        {
           return  "redirect:/orders/edit/order/" + id;
        }
        else if (action.equals("delete"))
        {
            productRepository.deleteById(id);
        }
        model.addAttribute("orders", productRepository.findAll());

        return "home/orders/check";
    }


    @GetMapping("/edit/order/{id}")
    public String check(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("order", productRepository.findById(id));
        return "home/orders/edit";
    }

    @PostMapping("/edit/order/{id}")
    public String check(@PathVariable("id") Long id, @ModelAttribute @Valid Product order,
                        BindingResult bindingResult,
                        Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("error", bindingResult.getAllErrors());
            return  "home/orders/edit";
        }
        else if (!order.period.getPeriodEnd().after(order.period.getPeriodStart()))
        {
            model.addAttribute("error", "Невозможно выставить данную дату, повторите попытку");
            return  "home/orders/edit";
        }

        var currentOrder = productRepository.findById(id).get();
        var currentperiod = periodRepository.findById(currentOrder.period.ID_Period).get();


        currentperiod.PeriodEnd = order.period.PeriodEnd;
        currentperiod.PeriodStart = order.period.PeriodStart;
        periodRepository.save(currentperiod);

        currentOrder.period = order.period;
        currentOrder.productPrice = order.productPrice;
        currentOrder.productDescription = order.productDescription;
        currentOrder.isAccepted = false;
        productRepository.save(currentOrder);
        return "redirect:/orders/check";
    }

}
