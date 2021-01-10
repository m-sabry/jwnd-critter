package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String notes;

    @Enumerated
    private PetType type;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Customer owner;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Schedule schedule;
}