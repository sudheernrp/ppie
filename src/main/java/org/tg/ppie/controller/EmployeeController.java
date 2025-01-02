package org.tg.ppie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.tg.ppie.exception.ResourceNotFoundException;
import org.tg.ppie.mgr.EmployeeMgr;
import org.tg.ppie.model.Employee;
import org.tg.ppie.repository.EmployeeRepository;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class EmployeeController {

    private final EmployeeMgr employeeMgr;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeMgr.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity < Employee > getEmployeeById(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        Employee employee = employeeMgr.getEmployeeById(employeeId);
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeMgr.createEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity < Employee > updateEmployee(@PathVariable(value = "id") Long employeeId,
        @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeMgr.updateEmployee(employeeId, employeeDetails);
        return ResponseEntity.ok(employee);
    }

    // update Email only
    @PatchMapping("/employees/{id}")
    public ResponseEntity<Employee> patchUpdate(@PathVariable(value = "id") Long employeeId,
                                                @RequestBody Map<String, String> update) throws ResourceNotFoundException{

        Employee employee = employeeMgr.patchUpdate(employeeId, update);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/employee/full-name/{id}")
    public ResponseEntity < Employee > getEmployeeFullName(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeMgr.getEmployeeFullName(employeeId);
        return ResponseEntity.ok().body(employee);

    }

}