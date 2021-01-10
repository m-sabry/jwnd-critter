package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.customer.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {
    private PetRepository petRepository;
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;

    public PetService(PetRepository petRepository,
                      CustomerRepository customerRepository,
                      ModelMapper modelMapper) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public List<PetDTO> getPetsByOwner(long ownerId) {
        List<Pet> pets = petRepository.findByOwner(customerRepository.getOne(ownerId));
        return pets.
                stream().
                map(pet -> modelMapper.map(pet, PetDTO.class)).
                collect(Collectors.toList());
    }

    public List<PetDTO> getPets() {
        List<Pet> pets = petRepository.findAll();
        return pets.stream()
                .map(pet -> modelMapper.map(pet, PetDTO.class))
                .collect(Collectors.toList());
    }

    public PetDTO getPet(long petId) {
        Optional<Pet> pet = petRepository.findById(petId);
        if(pet.isPresent())
            return modelMapper.map(pet, PetDTO.class);
        else
            return null;
    }

    public PetDTO savePet(PetDTO petDTO) {
        Pet pet = modelMapper.map(petDTO, Pet.class);
        pet.setOwner(customerRepository.getOne(petDTO.getOwnerId()));
        Pet createdPet = petRepository.save(pet);
        return modelMapper.map(createdPet, PetDTO.class);
    }
}
