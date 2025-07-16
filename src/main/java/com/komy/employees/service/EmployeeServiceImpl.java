package com.komy.employees.service;

import com.komy.employees.dao.EmployeeRepository;
import com.komy.employees.entity.Employee;
import com.komy.employees.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long id) {
        var result = employeeRepository.findById(id);

        return result.orElseThrow(() -> new RuntimeException("Employee not found for id - " + id));
    }

    @Transactional
    @Override
    public Employee save(EmployeeRequest employeeRequest) {
        Employee employee = convertToEmployee(0, employeeRequest);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee convertToEmployee(long id, EmployeeRequest employeeRequest) {
        return new Employee(id, employeeRequest.getFirstName(), employeeRequest.getLastName(), employeeRequest.getEmail());
    }

    @Transactional
    @Override
    public Employee update(long id, EmployeeRequest employeeRequest) {
        Employee employee = convertToEmployee(id, employeeRequest);
        return employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }
}
