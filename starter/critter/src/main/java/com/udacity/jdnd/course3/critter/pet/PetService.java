package com.udacity.jdnd.course3.critter.pet;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<PetDTO> getPetsByOwner(long ownerId) {
        return null;
    }

    public List<PetDTO> getPets() {
        return null;
    }

    public PetDTO getPet(long petId) {
        return null;
    }

    public PetDTO savePet(PetDTO petDTO) {
        return null;
    }
}
