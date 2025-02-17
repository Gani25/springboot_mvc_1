package com.sprk.springboot_mvc.controller;

import com.sprk.springboot_mvc.entity.Employee;
import com.sprk.springboot_mvc.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public String showForm(Model model){

        Employee employee = new Employee(); // create empty employee object
        model.addAttribute("employee", employee);
        return "form";
    }

    @PostMapping("/employee")
    public String processForm(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "form";
        }

        employeeService.saveEmployee(employee);

        redirectAttributes.addFlashAttribute("successMessage", "Employee has been saved successfully");

        return "redirect:/employee/dashboard";

    }

    @GetMapping("/employee/dashboard")
    public String showDashboard(Model model){

        List<Employee> employees = employeeService.getAllEmployees();

        model.addAttribute("employees", employees);

        return "dashboard";
    }

}
