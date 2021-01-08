package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
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
    private Long id;
    private LocalDate date;

    @ManyToMany(mappedBy = "schedules")
    private List<Employee> employees;

    @OneToMany(mappedBy = "schedule")
    private List<Pet> pets;

    @Enumerated
    @ElementCollection
    private Set<EmployeeSkill> activeSkills;
}