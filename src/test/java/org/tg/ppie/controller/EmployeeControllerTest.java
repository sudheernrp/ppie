package org.tg.ppie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.tg.ppie.mgr.EmployeeMgr;
import org.tg.ppie.model.Employee;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EmployeeControllerTest {

    AutoCloseable openedMocks;

    private MockMvc mockMvc;

    @Mock
    private EmployeeMgr employeeMgr;

    @InjectMocks
    private EmployeeController employeeController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        openedMocks = MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        objectMapper = new ObjectMapper();
    }

    @AfterEach
    void tearDown() throws Exception {
        openedMocks.close();
    }

    @Test
    void testGetAllEmployees() throws Exception {
        Employee employee1 = Employee.builder().id(1L).firstName("John")
                .lastName("Doe").emailId("john.doe@example.com").build();
        List<Employee> employees = Collections.singletonList(employee1);

        when(employeeMgr.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].emailId").value("john.doe@example.com"));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        Employee employee = Employee.builder().id(1L).firstName("John")
                .lastName("Doe").emailId("john.doe@example.com").build();

        when(employeeMgr.getEmployeeById(1L)).thenReturn(employee);

        mockMvc.perform(get("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.emailId").value("john.doe@example.com"));
    }

    @Test
    void testCreateEmployee() throws Exception {
        Employee employee = Employee.builder().id(1L).firstName("John")
                .lastName("Doe").emailId("john.doe@example.com").build();

        when(employeeMgr.createEmployee(employee)).thenReturn(employee);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.emailId").value("john.doe@example.com"));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        Employee employee = Employee.builder().id(1L).firstName("John")
                .lastName("Doe").emailId("john.doe@example.com").build();

        when(employeeMgr.updateEmployee(1L, employee)).thenReturn(employee);

        mockMvc.perform(put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.emailId").value("john.doe@example.com"));
    }

    @Test
    void testPatchUpdateEmployee() throws Exception {
        Employee employee = Employee.builder().id(1L).firstName("John")
                .lastName("Doe").emailId("john.new@example.com").build();
        Map<String, String> update = Collections.singletonMap("emailId", "john.new@example.com");

        when(employeeMgr.patchUpdate(1L, update)).thenReturn(employee);

        mockMvc.perform(patch("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.emailId").value("john.new@example.com"));
    }

    @Test
    void testGetEmployeeFullName() throws Exception {
        Employee employee = Employee.builder().id(1L).firstName("John")
                .lastName("Doe").emailId("john.doe@example.com").build();

        when(employeeMgr.getEmployeeFullName(1L)).thenReturn(employee);

        mockMvc.perform(get("/employee/full-name/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }
}