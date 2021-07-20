package com.example.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class Controller {
    Store store= new Store();

        @GetMapping("/url")
        public String getAllCustomers() {
            return store.getAllCustomersFromDB();
        }

        @GetMapping("/spfc_customer/id/{id}")
        public String getSpecificCustomer(@PathVariable int id){
            return store.getSpecificCustomerFromDB(id);
        }
        @GetMapping("/spfc_customer/{name}")
        public ArrayList<String> getSpecificCustomerByNameFromDB(@PathVariable String name){
            return store.getSpecificCustomerByNameFromDB(name);
        }
}

