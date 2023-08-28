package com.java.springboot.thymeleaf.service;

import com.java.springboot.thymeleaf.dao.EmployeeRepository;
import com.java.springboot.thymeleaf.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void testFindAll() {
        List<Employee> mockEmployees = new ArrayList<>();
        mockEmployees.add(new Employee("John", "Doe","john.doe@gmail.com"));
        mockEmployees.add(new Employee("Jane", "Smith","jane.smith@gmail.com"));

        when(employeeRepository.findAll()).thenReturn(mockEmployees);

        List<Employee> result = employeeService.findAll();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Jane", result.get(1).getFirstName());
        assertEquals("Doe",result.get(0).getLastName());
        assertEquals("Smith",result.get(1).getLastName());
    }

    @Test
    public void testFindById() {
        Employee mockEmployee = new Employee(1, "John","Doe","john.doe@gmail.com");

        when(employeeRepository.findById(1)).thenReturn(Optional.of(mockEmployee));

        Employee result = employeeService.findById(1);

        assertEquals("John", result.getFirstName());
        assertEquals("Doe",result.getLastName());
        assertEquals("john.doe@gmail.com",result.getEmail());
    }

    @Test(expected = RuntimeException.class)
    public void testFindByIdNotFound() {
        when(employeeRepository.findById(1)).thenReturn(Optional.empty());

        employeeService.findById(1);
    }

    @Test
    public void testSave() {
        Employee employeeToSave = new Employee("John", "Doe","john.doe@gmail.com");

        employeeService.save(employeeToSave);

        verify(employeeRepository, times(1)).save(employeeToSave);
    }

    @Test
    public void testDeleteById() {
        employeeService.deleteById(1);

        verify(employeeRepository, times(1)).deleteById(1);
    }
}
