package com.pluralsight.Controllers;

import com.pluralsight.DAO.CustomerDAO;
import com.pluralsight.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("customers")

public class CustomerController {
    CustomerDAO customerDAO;
    @Autowired
    public CustomerController(CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }
@GetMapping
    List<Customer> getCustomers() {
        return customerDAO.getAllCustomers();
    }


}
