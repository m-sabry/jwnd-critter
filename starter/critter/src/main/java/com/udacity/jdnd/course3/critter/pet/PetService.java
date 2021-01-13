package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.customer.Customer;
import com.udacity.jdnd.course3.critter.user.customer.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {
    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    public PetService(PetRepository petRepository,
                      CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public List<Pet> getPetsByOwner(long ownerId) {
        return petRepository.findByOwner(customerRepository.getOne(ownerId));
    }

    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    public Pet getPet(long petId) {
        return petRepository.findById(petId).get();
    }

    public Pet savePet(Pet pet) {
        pet.setOwner(pet.getOwner());
        Pet createdPet = petRepository.save(pet);

        Customer customer = createdPet.getOwner();
        customer.addPet(createdPet);
        customerRepository.save(customer);

        return createdPet;
    }
}
