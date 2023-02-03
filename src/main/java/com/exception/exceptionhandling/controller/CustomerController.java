package com.exception.exceptionhandling.controller;

import com.exception.exceptionhandling.exception.CustomerAlreadyExistsException;
import com.exception.exceptionhandling.exception.ErrorResponse;
import com.exception.exceptionhandling.model.Customer;
import com.exception.exceptionhandling.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // Get Customer by Id
    @GetMapping("/getCustomer/{id}")
    public Customer getCustomer(@PathVariable("id") Long id)
    {
        return customerService.getCustomer(id);
    }

    // Add new Customer
    @PostMapping("/addCustomer")
    public String
    addcustomer(@RequestBody Customer customer)
    {
        return customerService.addCustomer(customer);
    }

    // Update Customer details
    @PutMapping("/updateCustomer")
    public String
    updateCustomer(@RequestBody Customer customer)
    {
        return customerService.updateCustomer(customer);
    }
    @ExceptionHandler(value
            = CustomerAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse
    handleCustomerAlreadyExistsException(
            CustomerAlreadyExistsException ex)
    {
        return new ErrorResponse(HttpStatus.CONFLICT.value(),
                ex.getMessage());
    }
}
