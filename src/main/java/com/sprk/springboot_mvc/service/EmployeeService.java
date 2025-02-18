package com.sprk.springboot_mvc.service;

import com.sprk.springboot_mvc.entity.Employee;

import java.util.List;

public interface EmployeeService {

    boolean saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    String deleteEmployeeById(String empId);

    Employee getEmployeeById(Long empId);
}
