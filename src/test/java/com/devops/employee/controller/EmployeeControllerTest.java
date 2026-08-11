package com.devops.employee.controller;

import com.devops.employee.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeControllerTest {

    @Test
    void shouldCreateEmployee() {

        Employee employee = new Employee(
                104L,
                "Arjun",
                "Cloud Engineer",
                "AWS"
        );

        assertEquals(104L, employee.getId());
        assertEquals("Arjun", employee.getName());
        assertEquals("Cloud Engineer", employee.getRole());
        assertEquals("AWS", employee.getDepartment());
    }

    @Test
    void shouldValidateEmployeeName() {

        Employee employee = new Employee(
                105L,
                "Rahul",
                "Software Engineer",
                "Development"
        );

        assertNotNull(employee.getName());
        assertFalse(employee.getName().isEmpty());
    }
}
