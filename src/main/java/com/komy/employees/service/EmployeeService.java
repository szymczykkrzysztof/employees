package com.komy.employees.service;

import com.komy.employees.entity.Employee;
import com.komy.employees.request.EmployeeRequest;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(long id);

    Employee save(EmployeeRequest employeeRequest);

    Employee convertToEmployee(long id, EmployeeRequest employeeRequest);

    Employee update(long id, EmployeeRequest employeeRequest);

    void deleteById(long id);
}
