package com.example.demo.controller;

import com.example.demo.models.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class TFCustomerController {
    CustomerRepository customerRepository = new CustomerRepository();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("greeting", "Welcome to TF");
        return "index";
    }
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String showAllCustomers(Model model){
        ArrayList<Customer> customers = customerRepository.getAllCustomersFromDB();
        model.addAttribute("customers", customers);
        return "index";
    }

}
