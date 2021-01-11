package com.udacity.jdnd.course3.critter.user.customer;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer: customers){
            CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
            customerDTO.setPetIds(extractPetsIds(customer.getPets()));
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    private List<Long> extractPetsIds(List<Pet> pets) {
        List<Long> petsIds = new ArrayList<>();

        if(pets == null)
            return null;

        for (Pet pet:pets) {
            petsIds.add(pet.getId());
        }
        return petsIds;
    }

    public CustomerDTO getOwnerByPet(long petId) {
        Customer customer = customerRepository.findByPets(petRepository.getOne(petId));
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        customerDTO.setPetIds(extractPetsIds(customer.getPets()));
        return customerDTO;
    }
}
