package com.sprk.springboot_mvc.service.impl;

import com.sprk.springboot_mvc.entity.Employee;
import com.sprk.springboot_mvc.repository.EmployeeRepository;
import com.sprk.springboot_mvc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public boolean saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return true;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
