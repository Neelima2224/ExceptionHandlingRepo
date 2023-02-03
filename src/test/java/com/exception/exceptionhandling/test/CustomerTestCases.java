package com.exception.exceptionhandling.test;

import com.exception.exceptionhandling.exception.CustomerAlreadyExistsException;
import com.exception.exceptionhandling.exception.NoSuchCustomerExistsException;
import com.exception.exceptionhandling.model.Customer;
import com.exception.exceptionhandling.repository.CustomerRepository;
import com.exception.exceptionhandling.service.CustomerServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;

@SpringBootTest
@RunWith(SpringRunner.class)

public class CustomerTestCases {

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getCustomerNotFound() {
        Long id = 9l;
      //  Customer customer = (new Customer(1l, "neelima", "Hyderabad"));
        Mockito.when(customerRepository.findById(id)).thenThrow(NoSuchElementException.class);
        assertThrows(NoSuchElementException.class, () -> customerServiceImpl.getCustomer(id));
    }

    @Test
    public void addCustomerAlreadyExist() {
        Customer customer = (new Customer(1l, "neelima", "Hyderabad"));
        Mockito.when(customerRepository.findById(customer.getId())).thenThrow(CustomerAlreadyExistsException.class);
        assertThrows(CustomerAlreadyExistsException.class, () -> customerServiceImpl.addCustomer(customer));
    }
    @Test
    public void update() {
        Customer customer = (new Customer(1l, "neelima", "Hyderabad"));
        Mockito.when(customerRepository.findById(customer.getId())).thenThrow(NoSuchCustomerExistsException.class);
        assertThrows(NoSuchCustomerExistsException.class, () -> customerServiceImpl.updateCustomer(customer));
    }
    @Test
    public void deleteNotFound() {
        Long id = 9l;
        Mockito.when(customerRepository.findById(id)).thenThrow( NoSuchElementException.class);
        assertThrows(NoSuchElementException.class, () -> customerServiceImpl.deleteCustomer(id));
    }

}

