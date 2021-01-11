package com.udacity.jdnd.course3.critter.user.employee;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;
}