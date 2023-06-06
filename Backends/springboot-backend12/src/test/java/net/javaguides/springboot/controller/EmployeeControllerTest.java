package net.javaguides.springboot.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetAllEmployees() throws Exception {
        // Prepare mock data
        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setFirstName("John");
        employee1.setLastName("Doe");

        Employee employee2 = new Employee();
        employee2.setId(2);
        employee2.setFirstName("Jane");
        employee2.setLastName("Smith");

        List<Employee> employees = Arrays.asList(employee1, employee2);

        // Mock the repository's findAll() method
        when(employeeRepository.findAll()).thenReturn(employees);

        // Perform the GET request and assert the response
        mockMvc.perform(get("/api/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].firstName").value("Jane"))
                .andExpect(jsonPath("$[1].lastName").value("Smith"));
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        // Prepare mock data
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("John");
        employee.setLastName("Doe");

        // Mock the repository's findById() method
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        // Perform the GET request and assert the response
        mockMvc.perform(get("/api/v1/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    public void testGetEmployeeById_WithInvalidId_Returns404NotFound() throws Exception {
        // Mock the repository's findById() method
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        // Perform the GET request and assert the response
        mockMvc.perform(get("/api/v1/employees/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        // Prepare mock data
        Employee existingEmployee = new Employee();
        existingEmployee.setId(1);
        existingEmployee.setFirstName("John");
        existingEmployee.setLastName("Doe");

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(1);
        updatedEmployee.setFirstName("Jane");
        updatedEmployee.setLastName("Smith");

        // Mock the repository's findById() and save() methods
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(updatedEmployee);

        // Perform the PUT request and assert the response
        mockMvc.perform(put("/api/v1/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Smith"));
    }

    private MockHttpServletRequestBuilder put(String url) {
        return org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put(url);
    }

    private String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
