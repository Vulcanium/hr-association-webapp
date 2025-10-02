package com.vulcanium.hrassociationv2.controller;

import com.vulcanium.hrassociationv2.model.Employee;
import com.vulcanium.hrassociationv2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String employees(Model model) {
        Iterable<Employee> listEmployees = employeeService.getEmployees();
        model.addAttribute("employees", listEmployees);

        return "home";
    }

    @GetMapping("/createEmployee")
    public String createEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "formNewEmployee";
    }

    @GetMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable final int id, Model model) {
        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);

        return "formUpdateEmployee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public ModelAndView deleteEmployee(@PathVariable final int id) {
        employeeService.deleteEmployee(id);

        return new ModelAndView("redirect:/");
    }

    @PostMapping("/saveEmployee")
    public ModelAndView saveEmployee(@ModelAttribute Employee employee) {

        if (employee.getId() != null) {
            // Employee from update form has the password field not filled, so we fill it with the current password
            Employee current = employeeService.getEmployee(employee.getId());
            employee.setPassword(current.getPassword());
        }

        employeeService.saveEmployee(employee);

        return new ModelAndView("redirect:/");
    }
}
