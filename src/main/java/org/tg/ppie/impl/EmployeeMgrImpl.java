package org.tg.ppie.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.tg.ppie.exception.ResourceNotFoundException;
import org.tg.ppie.mgr.EmployeeMgr;
import org.tg.ppie.model.Employee;
import org.tg.ppie.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeMgrImpl implements EmployeeMgr {

    private final EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long employeeId) throws ResourceNotFoundException {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee patchUpdate(Long employeeId, Map<String, String> update) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        String emailId = update.get("emailId");
        if (!StringUtils.isEmpty(emailId)) {
            employee.setEmailId(emailId);
        }else{
            throw new ResourceNotFoundException("Patch Exception :: " + update.keySet());
        }

        employee.setEmailId(emailId);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeFullName(Long employeeId) throws ResourceNotFoundException {
        List<Object[]> objectsList = employeeRepository.getEmployeeFullName(employeeId);
        if(CollectionUtils.isEmpty(objectsList)){
            throw new ResourceNotFoundException("Employee not found for this id :: " + employeeId);
        }
        List<Employee> employees = new ArrayList<>();
        objectsList.forEach(r -> {
            Employee employee = new Employee();
            employee.setFirstName((String) r[0]);
            employees.add(employee);
        });
        return employees.get(0);
    }

    @Override
    public Employee updateEmployee(Long employeeId, Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        return employeeRepository.save(employee);
    }
}
