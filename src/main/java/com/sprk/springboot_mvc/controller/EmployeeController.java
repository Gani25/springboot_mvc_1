package com.sprk.springboot_mvc.controller;

import com.sprk.springboot_mvc.entity.Employee;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    @GetMapping("/employee")
    public String showForm(Model model){

        Employee employee = new Employee(); // create empty employee object
        model.addAttribute("employee", employee);
        return "form";
    }

    @PostMapping("/employee")

    public String processForm(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "form";
        }
        return "redirect:/";

    }
}
