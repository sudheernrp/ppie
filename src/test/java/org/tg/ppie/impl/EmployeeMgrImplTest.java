package org.tg.ppie.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tg.ppie.exception.ResourceNotFoundException;
import org.tg.ppie.model.Employee;
import org.tg.ppie.repository.EmployeeRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeMgrImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeMgrImpl employeeMgrImpl;

    @Test
    void testGetAllEmployees() {
        Employee employee1 = new Employee();
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employee1.setEmailId("john.doe@example.com");

        Employee employee2 = new Employee();
        employee2.setFirstName("Jane");
        employee2.setLastName("Doe");
        employee2.setEmailId("jane.doe@example.com");

        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeMgrImpl.getAllEmployees();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Jane", result.get(1).getFirstName());
    }

    @Test
    void testGetEmployeeById() throws ResourceNotFoundException {
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmailId("john.doe@example.com");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = employeeMgrImpl.getEmployeeById(1L);

        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("john.doe@example.com", result.getEmailId());
    }

    @Test
    void testGetEmployeeByIdNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeeMgrImpl.getEmployeeById(1L));
    }

    @Test
    void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmailId("john.doe@example.com");

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeMgrImpl.createEmployee(employee);

        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("john.doe@example.com", result.getEmailId());
    }

    @Test
    void testPatchUpdate() throws ResourceNotFoundException {
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmailId("john.doe@example.com");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(employee);

        Map<String, String> update = new HashMap<>();
        update.put("emailId", "john.new@example.com");

        Employee result = employeeMgrImpl.patchUpdate(1L, update);

        assertEquals("john.new@example.com", result.getEmailId());
    }

    @Test
    void testPatchUpdateNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        Map<String, String> update = new HashMap<>();
        update.put("emailId", "john.new@example.com");

        assertThrows(ResourceNotFoundException.class, () -> employeeMgrImpl.patchUpdate(1L, update));
    }

    @Test
    void testPatchUpdateEmptyEmail() {
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmailId("john.doe@example.com");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Map<String, String> update = new HashMap<>();
        update.put("emailId", "");

        assertThrows(ResourceNotFoundException.class, () -> employeeMgrImpl.patchUpdate(1L, update));
    }

    @Test
    void testGetEmployeeFullName() throws ResourceNotFoundException {
        List<Object[]> objectsList = new ArrayList<>();
        objectsList.add(new Object[]{"John"});

        when(employeeRepository.getEmployeeFullName(1L)).thenReturn(objectsList);

        Employee result = employeeMgrImpl.getEmployeeFullName(1L);

        assertEquals("John", result.getFirstName());
    }

    @Test
    void testGetEmployeeFullNameNotFound() {
        when(employeeRepository.getEmployeeFullName(1L)).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> employeeMgrImpl.getEmployeeFullName(1L));
    }

    @Test
    void testUpdateEmployee() throws ResourceNotFoundException {
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmailId("john.doe@example.com");

        Employee updatedEmployee = new Employee();
        updatedEmployee.setFirstName("John");
        updatedEmployee.setLastName("Smith");
        updatedEmployee.setEmailId("john.smith@example.com");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(updatedEmployee);

        Employee result = employeeMgrImpl.updateEmployee(1L, updatedEmployee);

        assertEquals("John", result.getFirstName());
        assertEquals("Smith", result.getLastName());
        assertEquals("john.smith@example.com", result.getEmailId());
    }

    @Test
    void testUpdateEmployeeNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        Employee updatedEmployee = new Employee();
        updatedEmployee.setFirstName("John");
        updatedEmployee.setLastName("Smith");
        updatedEmployee.setEmailId("john.smith@example.com");

        assertThrows(ResourceNotFoundException.class, () -> employeeMgrImpl.updateEmployee(1L, updatedEmployee));
    }
}