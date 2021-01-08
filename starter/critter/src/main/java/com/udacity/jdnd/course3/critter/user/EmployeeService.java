package com.udacity.jdnd.course3.critter.user;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        return null;
    }

    public EmployeeDTO getEmployee(long employeeId) {
        return null;
    }

    public void setAvailability(long employeeId, Set<DayOfWeek> daysAvailable) {
    }

    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        return null;
    }
}
