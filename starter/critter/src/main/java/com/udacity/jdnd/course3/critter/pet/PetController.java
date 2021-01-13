package com.udacity.jdnd.course3.critter.pet;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;
    private final ModelMapper modelMapper;

    public PetController(PetService petService, ModelMapper modelMapper) {
        this.petService = petService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = petService.savePet(modelMapper.map(petDTO, Pet.class));
        return modelMapper.map(pet, PetDTO.class);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPet(petId);
        if(pet != null) {
            return modelMapper.map(pet, PetDTO.class);
        }
        return null;
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.getPets().stream()
                .map(pet -> modelMapper.map(pet, PetDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

        return petService.getPetsByOwner(ownerId).
                stream().
                map(pet -> modelMapper.map(pet, PetDTO.class)).
                collect(Collectors.toList());

    }
}
