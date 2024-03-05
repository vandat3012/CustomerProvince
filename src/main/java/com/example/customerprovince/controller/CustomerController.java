package com.example.customerprovince.controller;

import com.example.customerprovince.model.Customer;
import com.example.customerprovince.model.Province;
import com.example.customerprovince.service.ICustomerService;
import com.example.customerprovince.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;
    @Autowired
    private IProvinceService iProvinceService;
    @ModelAttribute("provinces")
    public Iterable<Province> listProvinces() {
        return iProvinceService.findAll();
    }
    @GetMapping("")
    public String listCustomer(Model model) {
        Iterable<Customer> customerIterable = iCustomerService.findAll();
        model.addAttribute("customers",customerIterable);
        return "customer/listcustomer";
    }

    @GetMapping("/create")
    public String create (Model model) {
        model.addAttribute("customers",new Customer());
        return "customer/create";
    }
    @PostMapping("/save")
    public String save (Customer customer) {
        iCustomerService.save(customer);
        return "redirect:/customers";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView formEdit(@PathVariable Long id) {
        Optional<Customer> customerOptional = iCustomerService.findById(id);
        if (customerOptional.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("customer/edit");
            modelAndView.addObject("customers",customerOptional.get());
            return modelAndView;
        }else {
            return new ModelAndView("error");
        }
    }
    @PostMapping("/edit/{id}")
    public String edit (@ModelAttribute("customers")Customer customer) {
        iCustomerService.save(customer);
        return "redirect:/customers";
    }
    @GetMapping("/delete/{id}")
    public String delete (@PathVariable Long id) {
        iCustomerService.remove(id);
        return "redirect:/customers";
    }
}
