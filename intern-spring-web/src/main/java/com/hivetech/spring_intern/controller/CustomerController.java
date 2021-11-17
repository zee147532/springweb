package com.hivetech.spring_intern.controller;

import com.hivetech.spring_intern.entity.Customer;
import com.hivetech.spring_intern.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/list")
    public String listCustomer(Model model,@Param("keyword") String keyword) {
        model.addAttribute("list", customerService.getAllCustomer(keyword));
        model.addAttribute("keyword", keyword);
        return "listCustomer";
    }

    @GetMapping("/add")
    public String addCustomer(Model model) {
            model.addAttribute("customer", new Customer());
            return "addCustomer";
    }

    @GetMapping(value = "/edit")
    public String editCustomer(@RequestParam("id") Integer customerID, Model model) {
        Optional<Customer> customerEdit = customerService.findCustomer(customerID);
        model.addAttribute("id", customerID);
        customerEdit.ifPresent(customer -> model.addAttribute("customer", customer));
        return "editCustomer";
    }

    @GetMapping(value = "/delete")
    public String deleteCustomer(@RequestParam("id") Integer customerID, RedirectAttributes model) {
        customerService.deleteCustomer(customerID);
        model.addFlashAttribute("success", "Xoa thanh cong!");
        return "redirect:/list";
    }

    @PostMapping("/save")
    public String saveCustomer(Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/list";
    }
}
