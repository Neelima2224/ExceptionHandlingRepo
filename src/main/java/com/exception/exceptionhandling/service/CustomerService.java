package com.exception.exceptionhandling.service;

import com.exception.exceptionhandling.model.Customer;

public interface CustomerService {

    Customer getCustomer(Long id);

    String addCustomer(Customer customer);

    String updateCustomer(Customer customer);

    void deleteCustomer(Long id);
}
