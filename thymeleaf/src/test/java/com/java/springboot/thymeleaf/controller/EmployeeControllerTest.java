package com.java.springboot.thymeleaf.controller;

import com.java.springboot.thymeleaf.entity.Employee;
import com.java.springboot.thymeleaf.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testListEmployees() throws Exception {
        List<Employee> mockEmployees = new ArrayList<>();
        mockEmployees.add(new Employee("John", "Doe","john.doe@gmail.com"));
        mockEmployees.add(new Employee("Jane", "Smith","jane.smith@gmail.com"));

        when(employeeService.findAll()).thenReturn(mockEmployees);

        mockMvc.perform(get("/employees/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("list-employees"))
                .andExpect(model().attribute("employees", mockEmployees));
    }

    @Test
    public void testShowFormForAdd() throws Exception {
        mockMvc.perform(get("/employees/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee-form"))
                .andExpect(model().attributeExists("employee"));
    }

    @Test
    public void testShowFormForUpdate() throws Exception {
        Employee mockEmployee = new Employee(1,"John", "Doe","john.doe@gmail.com");
        when(employeeService.findById(1)).thenReturn(mockEmployee);

        mockMvc.perform(get("/employees/showFormForUpdate").param("employeeId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee-form"))
                .andExpect(model().attribute("employee", mockEmployee));
    }

    @Test
    public void testSaveEmployee() throws Exception {
        mockMvc.perform(post("/employees/save").flashAttr("employee", new Employee()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/employees/list"));

        verify(employeeService, times(1)).save(any(Employee.class));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(get("/employees/delete").param("employeeId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/employees/list"));

        verify(employeeService, times(1)).deleteById(1);
    }
}
