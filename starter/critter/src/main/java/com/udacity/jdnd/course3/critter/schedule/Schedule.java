package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.employee.Employee;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeSkill;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;


    @ManyToMany(targetEntity = Employee.class)
    private List<Employee> employees;

    @ManyToMany(targetEntity = Pet.class)
    private List<Pet> pets;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> activities;
}