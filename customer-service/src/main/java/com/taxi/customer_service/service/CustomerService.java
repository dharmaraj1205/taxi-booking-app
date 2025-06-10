package com.taxi.customer_service.service;

import com.taxi.customer_service.entity.Customer;
import com.taxi.customer_service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer registerCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    public Customer getCustomerById(Long id){
        return customerRepository.findById(id).orElseThrow(()->new RuntimeException("Cutomer not found"));
    }
}
