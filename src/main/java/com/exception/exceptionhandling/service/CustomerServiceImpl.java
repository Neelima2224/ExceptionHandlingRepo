package com.exception.exceptionhandling.service;

import com.exception.exceptionhandling.exception.CustomerAlreadyExistsException;
import com.exception.exceptionhandling.exception.NoSuchCustomerExistsException;
import com.exception.exceptionhandling.model.Customer;
import com.exception.exceptionhandling.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRespository;

    // Method to get customer by Id.Throws
    // NoSuchElementException for invalid Id
    public Customer getCustomer(Long id) {
        return customerRespository.findById(id).orElseThrow(
                ()
                        -> new NoSuchElementException(
                        "NO CUSTOMER PRESENT WITH ID = " + id));
    }

    // Method to add new customer details to database.Throws
    // CustomerAlreadyExistsException when customer detail
    // already exist
    public String addCustomer(Customer customer) {
        Customer existingCustomer
                = customerRespository.findById(customer.getId())
                .orElse(null);
        if (existingCustomer == null) {
            customerRespository.save(customer);
            return "Customer added successfully";
        } else
            throw new CustomerAlreadyExistsException(
                    "Customer already exists!!");
    }

    // Method to update customer details to database.Throws
    // NoSuchCustomerExistsException when customer doesn't
    // already exist in database
    public String updateCustomer(Customer customer) {
        Customer existingCustomer
                = customerRespository.findById(customer.getId())
                .orElse(null);
        if (existingCustomer == null)
            throw new NoSuchCustomerExistsException(
                    "No Such Customer exists!!");
        else {
            existingCustomer.setName(customer.getName());
            existingCustomer.setAddress(
                    customer.getAddress());
            customerRespository.save(existingCustomer);
            return "Record updated Successfully";
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer existingCustomer
                = customerRespository.findById(id)
                .orElse(null);
        if (existingCustomer == null)
            throw new   NoSuchElementException(
                    "NO CUSTOMER PRESENT WITH ID = " + id);
        else {
            customerRespository.deleteById(id);

        }
    }
}
