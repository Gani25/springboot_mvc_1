package com.sprk.springboot_mvc.controller;

import com.sprk.springboot_mvc.entity.Employee;
import com.sprk.springboot_mvc.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public String showForm(Model model) {

        Employee employee = new Employee(); // create empty employee object
        model.addAttribute("employee", employee);
        model.addAttribute("btnMessage", "Save Employee");
        return "form";
    }

    @PostMapping("/employee")
    public String processForm(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        Employee existingEmployee = (Employee) session.getAttribute("existingEmployee");

        if (existingEmployee != null) {
            employee.setId(existingEmployee.getId());
            session.setAttribute("existingEmployee", null);
            String message = "Employee with id: " + existingEmployee.getId() + " has been updated successfully";
            redirectAttributes.addFlashAttribute("successMessage", message);
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Employee has been saved successfully");

        }
        employeeService.saveEmployee(employee);
        return "redirect:/employee/dashboard";

    }

    @GetMapping("/employee/dashboard")
    public String showDashboard(Model model) {

        List<Employee> employees = employeeService.getAllEmployees();

        model.addAttribute("employees", employees);

        return "dashboard";
    }

    @GetMapping("/employee/delete")
    public String deleteEmployee(@RequestParam String empId, RedirectAttributes redirectAttributes) {
        String message = employeeService.deleteEmployeeById(empId);
        if (message.equals("Employee deleted successfully")) {
            redirectAttributes.addFlashAttribute("successMessage", message);

        } else {
            redirectAttributes.addFlashAttribute("errorMessage", message);
        }

        return "redirect:/employee/dashboard";
    }

    // Update Search Employee then show form -> Same form of add
    @GetMapping("/employee/update/{empId}")
    public String showUpdateForm(@PathVariable String empId, Model model, RedirectAttributes redirectAttributes, HttpSession session) {

        // Find That Employee
        String message = "";
        if (empId.matches("\\d+")) {
            // If empId is Digit then only perform deletion
            Long employeeId = Long.parseLong(empId);
            Employee employee = employeeService.getEmployeeById(employeeId);

            if (employee != null) {
                // Show Update Form
                model.addAttribute("employee", employee); // Id was present
                session.setAttribute("existingEmployee", employee);
                model.addAttribute("btnMessage", "Update Employee");
                return "form";
            } else {
                // SHow Error Message
                message = "Employee with id: " + employeeId + " not found";
                redirectAttributes.addFlashAttribute("errorMessage", message);
                return "redirect:/employee/dashboard";

            }

        } else {
            message = "Employee id can have only numbers";
            redirectAttributes.addFlashAttribute("errorMessage", message);
            return "redirect:/employee/dashboard";
        }

    }

}
