package com.sufiyan.employee_management.service.impl;

import com.sufiyan.employee_management.entity.Employee;
import com.sufiyan.employee_management.repository.EmployeeRepository;
import com.sufiyan.employee_management.service.EmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not foud with ID: " + employeeId));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = getEmployeeById(id);
        existingEmployee.setName(employee.getName());
        existingEmployee.setAge(employee.getAge());
        existingEmployee.setGender(employee.getGender());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setCity(employee.getCity());
        return employeeRepository.save(existingEmployee);
    }

    @Override
    public Boolean deleteEmployee(Long employeeId) {
        try {
            getEmployeeById(employeeId); // throws exception if not found
            employeeRepository.deleteById(employeeId);
            return true;
        } catch (Exception e) {
            log.error("Error while deleting Employee with ID: {} | Error: {}", employeeId, e.getMessage());
            throw new RuntimeException("Cannot delete Employee with ID: " + employeeId);
        }
    }
}
