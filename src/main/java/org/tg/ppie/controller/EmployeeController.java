package org.tg.ppie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.tg.ppie.exception.ResourceNotFoundException;
import org.tg.ppie.model.Employee;
import org.tg.ppie.repository.EmployeeRepository;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity < Employee > getEmployeeById(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity < Employee > updateEmployee(@PathVariable(value = "id") Long employeeId,
        @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    // update Email only
    @PatchMapping("/employees/{id}")
    public ResponseEntity<Employee> patchUpdate(@PathVariable(value = "id") Long employeeId,
                                                @RequestBody Map<String, String> update) throws ResourceNotFoundException{

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        String emailId = update.get("emailId");
        if (!StringUtils.isEmpty(emailId)) {
            employee.setEmailId(emailId);
        }else{
            throw new ResourceNotFoundException("Patch Exception :: " + update.keySet());
        }

        employee.setEmailId(emailId);
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @GetMapping("/employee/full-name/{id}")
    public ResponseEntity < Employee > getEmployeeFullName(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        List<Object[]> objectsList = employeeRepository.getEmployeeFullName(employeeId);
        if(StringUtils.isEmpty(objectsList)){
            throw new ResourceNotFoundException("Employee not found for this id :: " + employeeId);
        }

        List<Employee> employees = new ArrayList<>();
        objectsList.stream().forEach((r) -> {
            Employee employee = new Employee();
            employee.setFirstName((String) r[0]);
            employees.add(employee);
        });
        return ResponseEntity.ok().body(employees.get(0));

    }

}