package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<ScheduleDTO> getScheduleForCustomer(long customerId) {
        return null;
    }

    public List<ScheduleDTO> getScheduleForEmployee(long employeeId) {
        return null;
    }

    public List<ScheduleDTO> getScheduleForPet(long petId) {
        return null;
    }

    public List<ScheduleDTO> getAllSchedules() {
        return null;
    }

    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        return null;
    }
}
