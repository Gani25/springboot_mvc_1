package com.sprk.springboot_mvc.service.impl;

import com.sprk.springboot_mvc.entity.Employee;
import com.sprk.springboot_mvc.repository.EmployeeRepository;
import com.sprk.springboot_mvc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

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

    @Override
    public String deleteEmployeeById(String empId) {
        // Find That Employee
        String message = "";
        if (empId.matches("\\d+")) {
            // If empId is Digit then only perform deletion
            Long employeeId = Long.parseLong(empId);
            Employee employee = employeeRepository.findById(employeeId).orElse(null);

            if (employee != null) {
                // Delete
                employeeRepository.delete(employee);

                message = "Employee deleted successfully";
            } else {
                // SHow Error Message
                message = "Employee with id: " + employeeId + " not found";
            }

        } else {
            message = "Employee id can have only numbers";
        }

        return message;
    }

    @Override
    public Employee getEmployeeById(Long empId) {


        Employee employee = employeeRepository.findById(empId).orElse(null);

        return employee;
    }
}
