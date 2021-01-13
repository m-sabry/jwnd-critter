package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.employee.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleService.createSchedule(scheduleDTO);

        ScheduleDTO createdScheduleDTO = new ScheduleDTO();
        createdScheduleDTO.setId(schedule.getId());
        createdScheduleDTO.setActivities(schedule.getActivities());
        createdScheduleDTO.setDate(schedule.getDate());
        createdScheduleDTO.setEmployeeIds(extractEmployeesIds(schedule.getEmployees()));
        createdScheduleDTO.setPetIds(extractPetsIds(schedule.getPets()));

        return createdScheduleDTO;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return getScheduleDTOS(scheduleService.getAllSchedules());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return getScheduleDTOS(scheduleService.getScheduleForPet(petId));
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return getScheduleDTOS(scheduleService.getScheduleForEmployee(employeeId));
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return getScheduleDTOS(scheduleService.getScheduleForCustomer(customerId));
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
