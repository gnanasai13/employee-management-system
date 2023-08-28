package com.java.springboot.thymeleaf.dao;

import com.java.springboot.thymeleaf.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    public List<Employee> findAllByOrderByLastNameAsc();

}
