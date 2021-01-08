package com.udacity.jdnd.course3.critter.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO save(CustomerDTO customerDTO){
        return null;
    }

    public List<CustomerDTO> getAllCustomers() {
        return null;
    }

    public CustomerDTO getOwnerByPet(long petId) {
        return null;
    }
}
