package com.example.demo;


import com.example.demo.Models.Customer;
import com.example.demo.Models.CustomerCountry;
import com.example.demo.Models.CustomerGenre;
import com.example.demo.Models.CustomerSpender;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class Controller {
    Store store = new Store();

    @GetMapping("/url")
    public ArrayList<Customer> getAllCustomers() {
        return store.getAllCustomersFromDB();
    }

    @GetMapping("/spfc_customer/id/{id}")
    public Customer getSpecificCustomer(@PathVariable int id) {
        return store.getSpecificCustomerFromDB(id);
    }

    @GetMapping("/spfc_customer/{name}")
    public ArrayList<Customer> getSpecificCustomerByNameFromDB(@PathVariable String name) {
        return store.getSpecificCustomerByNameFromDB(name);
    }

    @GetMapping("/spfc_customer/page/{limit}/{offset}")
    public ArrayList<Customer> getCustomerPageFromDB(@PathVariable int limit, @PathVariable int offset) {
        return store.getCustomerPageFromDB(limit, offset);
    }

    @RequestMapping(value = "/spfc_customer/add/customer", method = RequestMethod.POST)
    public void addNewCustomer(@RequestBody Customer customer) {
        store.addCustomer(customer);
    }

    @RequestMapping(value = "/spfc_customer/update/{id}", method = RequestMethod.PUT)
    public void updateExistingCustomer(@PathVariable int id, @RequestBody Customer customer) {
        store.updateCustomer(customer, id);
    }

    @GetMapping("/spfc_customer/customer_country")
    public ArrayList<CustomerCountry> getCustomersPerCountry() {
        return store.getCustomersPerCountry();
    }

    @GetMapping("/spfc_customer/customer_spent")
    public ArrayList<CustomerSpender> getCustomerSpentList() {
        return store.getCustomerSpentList();
    }

    @GetMapping("/spfc_customer/customer_genre/{customerID}")
    public ArrayList<CustomerGenre> getCustomerMostPopularGenreList(@PathVariable int customerID) {
        return store.getCustomerMostPopularGenreList(customerID);
    }

}

