package com.pluralsight.Controllers;

import com.pluralsight.DAO.CustomerDAO;
import com.pluralsight.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin

public class CustomerController {
    CustomerDAO customerDao;
    @Autowired
    public CustomerController(CustomerDAO customerDao){
        this.customerDao = customerDao;
    }

@PostMapping("/register")
@PreAuthorize("permitAll()")
    public void addNewCustomer(@RequestBody Customer customer){

        customerDao.addCustomer(customer);

}


}
