package com.sufiyan.employee_management.service;

import com.sufiyan.employee_management.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long employeeId);

    Employee updateEmployee(Long id, Employee employee);

    Boolean deleteEmployee(Long employeeId);
}
