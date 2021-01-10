package com.udacity.jdnd.course3.critter.user.employee;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository,
                           ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee created = employeeRepository.save(employee);
        return modelMapper.map(created, EmployeeDTO.class);
    }

    public EmployeeDTO getEmployee(long employeeId) {
        return modelMapper.map(employeeRepository.findById(employeeId), EmployeeDTO.class);
    }

    public void setAvailability(long employeeId, Set<DayOfWeek> daysAvailable) {
    }

    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        return null;
    }
}
