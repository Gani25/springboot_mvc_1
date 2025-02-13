package com.sprk.springboot_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Date;

@Controller
public class DemoController {

    @GetMapping({"/","/home"})
    public String showHomePage(Model model){
        Date today = new Date();
        model.addAttribute("todaysDate", today);
        return "index";
    }
}
