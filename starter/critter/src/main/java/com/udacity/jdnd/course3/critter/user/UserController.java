package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.customer.Customer;
import com.udacity.jdnd.course3.critter.user.customer.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.customer.CustomerService;
import com.udacity.jdnd.course3.critter.user.employee.*;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    public UserController(CustomerService customerService, EmployeeService employeeService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        return modelMapper.map(customerService.save(customer), CustomerDTO.class);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer: customers){
            CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
            customerDTO.setPetIds(extractPetsIds(customer.getPets()));
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer = customerService.getOwnerByPet(petId);
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        customerDTO.setPetIds(extractPetsIds(customer.getPets()));

        return customerDTO;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {

        Employee employee = employeeService.saveEmployee(modelMapper.map(employeeDTO,Employee.class));
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return modelMapper.map(employeeService.getEmployee(employeeId), EmployeeDTO.class);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setAvailability(employeeId, daysAvailable);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees =  employeeService.findEmployeesForService(employeeDTO.getSkills(), employeeDTO.getDate().getDayOfWeek());

        List<EmployeeDTO> dtos = new ArrayList<>();
        for (Employee employee: employees){
            if(employee.getSkills().containsAll(employeeDTO.getSkills())) {
                EmployeeDTO tempEmployeeDTO = modelMapper.map(employee, EmployeeDTO.class);
                dtos.add(tempEmployeeDTO);
            }
        }
        return dtos;
    }


    private List<Long> extractPetsIds(List<Pet> pets) {
        List<Long> petsIds = new ArrayList<>();

        if(pets == null)
            return null;

        for (Pet pet:pets) {
            petsIds.add(pet.getId());
        }
        return petsIds;
    }

}
