package com.komy.employees.service;

import com.komy.employees.dao.EmployeeDAO;
import com.komy.employees.entity.Employee;
import com.komy.employees.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(long id) {
        var employee = employeeDAO.findById(id);
        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }
        return employee;
    }

    @Transactional
    @Override
    public Employee save(EmployeeRequest employeeRequest) {
        Employee employee = convertToEmployee(0, employeeRequest);
        return employeeDAO.save(employee);
    }

    @Override
    public Employee convertToEmployee(long id, EmployeeRequest employeeRequest) {
        return new Employee(id, employeeRequest.getFirstName(), employeeRequest.getLastName(), employeeRequest.getEmail());
    }

    @Transactional
    @Override
    public Employee update(long id, EmployeeRequest employeeRequest) {
        Employee employee = convertToEmployee(id, employeeRequest);
        return employeeDAO.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        employeeDAO.deleteById(id);
    }
}
