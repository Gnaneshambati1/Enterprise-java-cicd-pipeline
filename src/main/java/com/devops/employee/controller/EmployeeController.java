package com.devops.employee.controller;

import com.devops.employee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeController() {
        employees.add(
                new Employee(101L, "Gnanesh", "DevOps Engineer", "Cloud")
        );

        employees.add(
                new Employee(102L, "Rahul", "Software Engineer", "Development")
        );

        employees.add(
                new Employee(103L, "Priya", "QA Engineer", "Testing")
        );
    }

    // GET all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // GET employee by ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {

        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // CREATE employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        employees.add(employee);
        return employee;
    }

    // UPDATE employee
    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee updatedEmployee) {

        for (Employee employee : employees) {

            if (employee.getId().equals(id)) {

                employee.setName(updatedEmployee.getName());
                employee.setRole(updatedEmployee.getRole());
                employee.setDepartment(updatedEmployee.getDepartment());

                return employee;
            }
        }

        return null;
    }

    // DELETE employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        boolean removed = employees.removeIf(
                employee -> employee.getId().equals(id)
        );

        if (removed) {
            return "Employee deleted successfully";
        }

        return "Employee not found";
    }
}
