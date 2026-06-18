package com.sufiyan.employee_management.service;

import com.sufiyan.employee_management.dto.EmployeeRequestDto;
import com.sufiyan.employee_management.dto.EmployeeResponseDto;
import com.sufiyan.employee_management.dto.GenericResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    GenericResponse<EmployeeResponseDto> saveEmployee(EmployeeRequestDto employeeRequestDto);

    GenericResponse<List<EmployeeResponseDto>> getAllEmployees();

    GenericResponse<Page<EmployeeResponseDto>> getAllEmployeesPaginated(int page, int size, String sortBy, String sortDirection);

    GenericResponse<EmployeeResponseDto> getEmployeeById(Long employeeId);

    GenericResponse<Page<EmployeeResponseDto>> searchByDepartment(String department, int page, int size);

    GenericResponse<List<EmployeeResponseDto>> searchByCity(String city);

    GenericResponse<EmployeeResponseDto> updateEmployee(Long id, EmployeeRequestDto employeeRequestDto);

    GenericResponse<EmployeeResponseDto> deleteEmployee(Long employeeId);
}
