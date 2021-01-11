package com.udacity.jdnd.course3.critter.user.employee;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
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
        return modelMapper.map(employeeRepository.findById(employeeId).get(), EmployeeDTO.class);
    }

    public void setAvailability(long employeeId, Set<DayOfWeek> daysAvailable) {
        Employee employee = employeeRepository.getOne(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO erDTO) {
        List<Employee> employees = employeeRepository.findDistinctBySkillsInAndDaysAvailableIs(
                erDTO.getSkills(), erDTO.getDate().getDayOfWeek());

        // filter those that don't contain all skills
        return convertToEmployeeDTO(employees, erDTO.getSkills());
    }

    private List<EmployeeDTO> convertToEmployeeDTO(List<Employee> employees, Set<EmployeeSkill> skills) {
        List<EmployeeDTO> dtos = new ArrayList<>();
        for (Employee employee: employees){
            if(employee.getSkills().containsAll(skills)) {
                EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
                dtos.add(employeeDTO);
            }
        }
        return dtos;
    }
}
