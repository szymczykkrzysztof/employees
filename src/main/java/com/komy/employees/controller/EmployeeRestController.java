package com.komy.employees.controller;

import com.komy.employees.entity.Employee;
import com.komy.employees.request.EmployeeRequest;
import com.komy.employees.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Rest Api Endpoints", description = "Operations related to employees.")
public class EmployeeRestController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @Operation(summary = "Get all employees", description = "Retrieve a list of all employees.")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @Operation(summary = "Fetch single employee", description = "Get a single employee from database.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{employeeId}")
    public Employee findById(@PathVariable("employeeId") @Min(value = 1) long employeeId) {
        return employeeService.findById(employeeId);
    }

    @Operation(summary = "Add new employee", description = "Add a new employee to database.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.save(employeeRequest);
    }

    @Operation(summary = "Update existing employee", description = "Update existing employee in database.")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable("employeeId") @Min(value = 1) long employeeId, @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.update(employeeId, employeeRequest);
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete existing employee", description = "Delete existing employee from database.")
    public void deleteEmployee(@PathVariable("employeeId") long employeeId) {
        employeeService.deleteById(employeeId);
    }


}
