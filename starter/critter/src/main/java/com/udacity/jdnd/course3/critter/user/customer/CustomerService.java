package com.udacity.jdnd.course3.critter.user.customer;

import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private PetRepository petRepository;
    private ModelMapper modelMapper;

    public CustomerService(CustomerRepository customerRepository,
                           PetRepository petRepository,
                           ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
        this.modelMapper = modelMapper;
    }

    public CustomerDTO save(CustomerDTO customerDTO){
        Customer createdCustomer =
                customerRepository.save(modelMapper.map(customerDTO, Customer.class));

        return modelMapper.map(createdCustomer, CustomerDTO.class);
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    public CustomerDTO getOwnerByPet(long petId) {
        return modelMapper.map(customerRepository.findByPets(petRepository.getOne(petId)), CustomerDTO.class);

    }
}
