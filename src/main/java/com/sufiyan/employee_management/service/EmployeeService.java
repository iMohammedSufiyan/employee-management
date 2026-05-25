package com.sufiyan.employee_management.service;

import com.sufiyan.employee_management.dto.EmployeeRequestDto;
import com.sufiyan.employee_management.dto.EmployeeResponseDto;
import com.sufiyan.employee_management.dto.GenericResponse;
import com.sufiyan.employee_management.entity.Employee;

import java.util.List;

public interface EmployeeService {
    GenericResponse<EmployeeResponseDto> saveEmployee(EmployeeRequestDto employeeRequestDto);

    GenericResponse<List<EmployeeResponseDto>> getAllEmployees();

    GenericResponse<EmployeeResponseDto> getEmployeeById(Long employeeId);

    GenericResponse<EmployeeResponseDto> updateEmployee(Long id, EmployeeRequestDto employeeRequestDto);

    GenericResponse<EmployeeResponseDto> deleteEmployee(Long employeeId);
}
