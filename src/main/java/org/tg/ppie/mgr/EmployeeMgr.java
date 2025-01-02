package org.tg.ppie.mgr;

import org.tg.ppie.exception.ResourceNotFoundException;
import org.tg.ppie.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeMgr {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long employeeId) throws ResourceNotFoundException;

    Employee createEmployee(Employee employee);

    Employee patchUpdate(Long employeeId, Map<String, String> update) throws ResourceNotFoundException;

    Employee getEmployeeFullName(Long employeeId) throws ResourceNotFoundException;

    Employee updateEmployee(Long employeeId, Employee employeeDetails) throws ResourceNotFoundException;

}
