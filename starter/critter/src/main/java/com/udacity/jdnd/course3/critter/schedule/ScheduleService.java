package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.customer.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.employee.Employee;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;

    public ScheduleService(ScheduleRepository scheduleRepository,
                           EmployeeRepository employeeRepository,
                           CustomerRepository customerRepository,
                           PetRepository petRepository) {
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    public List<Schedule> getScheduleForCustomer(long customerId) {
        List<Pet> pets = petRepository.findByOwner(customerRepository.getOne(customerId));
        return scheduleRepository.findByPetsIn(pets);
    }


    public List<Schedule> getScheduleForEmployee(long employeeId) {
        return scheduleRepository.findAllByEmployeesId(employeeId);
    }

    public List<Schedule> getScheduleForPet(long petId) {
        return scheduleRepository.findAllByPetsId(petId);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule createSchedule(ScheduleDTO scheduleDTO) {

        List<Employee> employees = employeeRepository.findAllById(scheduleDTO.getEmployeeIds());
        List<Pet> pets = petRepository.findAllById(scheduleDTO.getPetIds());

        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities((scheduleDTO.getActivities()));
        schedule.setPets(pets);
        schedule.setEmployees(employees);

        return scheduleRepository.save(schedule);
    }

}
