package com.example.demo.controller;


import com.example.demo.repository.CustomerRepository;
import com.example.demo.models.Customer;
import com.example.demo.models.CustomerCountry;
import com.example.demo.models.CustomerGenre;
import com.example.demo.models.CustomerSpender;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CustomerController {
    CustomerRepository customerRepository = new CustomerRepository();

    @GetMapping("/all-customers")
    public ArrayList<Customer> getAllCustomers() {
        return customerRepository.getAllCustomersFromDB();
    }

    @GetMapping("/customer/id/{id}")
    public Customer getSpecificCustomer(@PathVariable int id) {
        return customerRepository.getSpecificCustomerFromDB(id);
    }

    @GetMapping("/customer/name/{name}")
    public ArrayList<Customer> getSpecificCustomerByNameFromDB(@PathVariable String name) {
        return customerRepository.getSpecificCustomerByNameFromDB(name);
    }

    @GetMapping("/customers/page/{limit}/{offset}")
    public ArrayList<Customer> getCustomerPageFromDB(@PathVariable int limit, @PathVariable int offset) {
        return customerRepository.getCustomerPageFromDB(limit, offset);
    }

    @RequestMapping(value = "/customer/add-customer", method = RequestMethod.POST)
    public void addNewCustomer(@RequestBody Customer customer) {
        customerRepository.addCustomer(customer);
    }

    @RequestMapping(value = "/customer/update/{id}", method = RequestMethod.PUT)
    public void updateExistingCustomer(@PathVariable int id, @RequestBody Customer customer) {
        customerRepository.updateCustomer(customer, id);
    }

    @GetMapping("/customers/country")
    public ArrayList<CustomerCountry> getCustomersPerCountry() {
        return customerRepository.getCustomersPerCountry();
    }

    @GetMapping("/customer/spent")
    public ArrayList<CustomerSpender> getCustomerSpentList() {
        return customerRepository.getCustomerSpentList();
    }

    @GetMapping("/customer/genre/{customerID}")
    public ArrayList<CustomerGenre> getCustomerMostPopularGenreList(@PathVariable int customerID) {
        return customerRepository.getCustomerMostPopularGenreList(customerID);
    }

}

