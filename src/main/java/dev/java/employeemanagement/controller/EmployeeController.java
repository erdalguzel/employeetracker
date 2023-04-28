package dev.java.employeemanagement.controller;

import dev.java.employeemanagement.entity.Employee;
import dev.java.employeemanagement.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(path = "/list")
    public String listAllEmployees(Model model) {
        List<Employee> employees = employeeService.listAllEmployees();
        model.addAttribute("employees", employees);
        return "list-employee";
    }

    @RequestMapping(path = "/add")
    public String addEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "add-employee";
    }

    @RequestMapping(path = "/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.addEmployee(employee);
        return "redirect:/employee/list";
    }

    @RequestMapping(path = "/update/{id}")
    public String updateEmployee(@PathVariable(value = "id") Long id, Model model) {
        Employee employeeById = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employeeById);
        return "update-employee";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employee/list";
    }
}
