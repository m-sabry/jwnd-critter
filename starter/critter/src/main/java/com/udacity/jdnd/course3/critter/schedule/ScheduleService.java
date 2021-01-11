package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeRepository;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.customer.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.employee.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

    public List<ScheduleDTO> getScheduleForCustomer(long customerId) {
        List<Pet> pets = petRepository.findByOwner(customerRepository.getOne(customerId));
        List<Schedule> schedules = scheduleRepository.findByPetsIn(pets);
        return getScheduleDTOS(schedules);
    }


    public List<ScheduleDTO> getScheduleForEmployee(long employeeId) {
        List<Schedule> schedules = scheduleRepository.findAllByEmployeesId(employeeId);
        return getScheduleDTOS(schedules);
    }

    public List<ScheduleDTO> getScheduleForPet(long petId) {
        List<Schedule> schedules = scheduleRepository.findAllByPetsId(petId);
        return getScheduleDTOS(schedules);
    }

    private List<ScheduleDTO> getScheduleDTOS(List<Schedule> schedules) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            scheduleDTO.setId(schedule.getId());
            scheduleDTO.setActivities(schedule.getActivities());
            scheduleDTO.setDate(schedule.getDate());
            scheduleDTO.setEmployeeIds(extractEmployeesIds(schedule.getEmployees()));
            scheduleDTO.setPetIds(extractPetsIds(schedule.getPets()));

            scheduleDTOS.add(scheduleDTO);
        }

        return scheduleDTOS;
    }

    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return getScheduleDTOS(schedules);
    }

    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {

        List<Employee> employees = employeeRepository.findAllById(scheduleDTO.getEmployeeIds());
        List<Pet> pets = petRepository.findAllById(scheduleDTO.getPetIds());

        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities((scheduleDTO.getActivities()));
        schedule.setPets(pets);
        schedule.setEmployees(employees);

        Schedule created = scheduleRepository.save(schedule);

        ScheduleDTO createdScheduleDTO = new ScheduleDTO();
        createdScheduleDTO.setId(created.getId());
        createdScheduleDTO.setActivities(created.getActivities());
        createdScheduleDTO.setDate(created.getDate());
        createdScheduleDTO.setEmployeeIds(extractEmployeesIds(created.getEmployees()));
        createdScheduleDTO.setPetIds(extractPetsIds(created.getPets()));

        return createdScheduleDTO;
    }

    private List<Long> extractPetsIds(List<Pet> pets) {
        List ids = new ArrayList();
        for (Pet pet:pets){
            ids.add(pet.getId());
        }
        return ids;
    }

    private List<Long> extractEmployeesIds(List<Employee> employees) {
        List ids = new ArrayList();
        for (Employee employee: employees){
            ids.add(employee.getId());
        }
        return ids;
    }
}
