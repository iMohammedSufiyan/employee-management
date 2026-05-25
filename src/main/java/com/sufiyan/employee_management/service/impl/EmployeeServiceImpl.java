package com.sufiyan.employee_management.service.impl;

import com.sufiyan.employee_management.dto.EmployeeRequestDto;
import com.sufiyan.employee_management.dto.EmployeeResponseDto;
import com.sufiyan.employee_management.dto.GenericResponse;
import com.sufiyan.employee_management.entity.Employee;
import com.sufiyan.employee_management.exception.EmployeeNotFoundException;
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
    public GenericResponse<EmployeeResponseDto> saveEmployee(EmployeeRequestDto employeeRequestDto) {
        return GenericResponse.success(
                "Employee added successfully.",
                mapToDto(employeeRepository.save(mapToEntity(employeeRequestDto))));
    }

    @Override
    public GenericResponse<List<EmployeeResponseDto>> getAllEmployees() {
        return GenericResponse.success(employeeRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList());
    }

    @Override
    public GenericResponse<EmployeeResponseDto> getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        return GenericResponse.success(mapToDto(employee));
    }

    @Override
    public GenericResponse<EmployeeResponseDto> updateEmployee(Long employeeId, EmployeeRequestDto employeeRequestDto) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        existingEmployee.setName(employeeRequestDto.getName());
        existingEmployee.setAge(employeeRequestDto.getAge());
        existingEmployee.setGender(employeeRequestDto.getGender());
        existingEmployee.setDepartment(employeeRequestDto.getDepartment());
        existingEmployee.setSalary(employeeRequestDto.getSalary());
        existingEmployee.setCity(employeeRequestDto.getCity());

        return GenericResponse.success(
                "Employee details updated successfully.",
                mapToDto(employeeRepository.save(existingEmployee)));
    }

    @Override
    public GenericResponse<EmployeeResponseDto> deleteEmployee(Long employeeId) {
        try {
            GenericResponse<EmployeeResponseDto> employeeResponseDto = getEmployeeById(employeeId); // throws exception if not found
            employeeRepository.deleteById(employeeId);
            return employeeResponseDto;
        } catch (Exception e) {
            log.error("Error while deleting Employee with ID: {} | Error: {}", employeeId, e.getMessage());
            return GenericResponse.notFound("Cannot delete Employee with ID: " + employeeId + "because " + e.getCause().getMessage());
        }
    }

    private Employee mapToEntity(EmployeeRequestDto employeeRequestDto) {
        return Employee
                .builder()
                .name(employeeRequestDto.getName())
                .age(employeeRequestDto.getAge())
                .gender(employeeRequestDto.getGender())
                .department(employeeRequestDto.getDepartment())
                .salary(employeeRequestDto.getSalary())
                .city(employeeRequestDto.getCity())
                .build();
    }

    private EmployeeResponseDto mapToDto(Employee employee) {
        return EmployeeResponseDto
                .builder()
                .id(employee.getId())
                .name(employee.getName())
                .age(employee.getAge())
                .gender(employee.getGender())
                .department(employee.getDepartment())
                .salary(employee.getSalary())
                .city(employee.getCity())
                .build();
    }
}
