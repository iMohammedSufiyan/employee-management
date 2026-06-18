package com.sufiyan.employee_management.service;

import com.sufiyan.employee_management.dto.EmployeeRequestDto;
import com.sufiyan.employee_management.dto.EmployeeResponseDto;
import com.sufiyan.employee_management.dto.GenericResponse;
import com.sufiyan.employee_management.entity.Employee;
import com.sufiyan.employee_management.exception.EmployeeNotFoundException;
import com.sufiyan.employee_management.repository.EmployeeRepository;
import com.sufiyan.employee_management.service.impl.EmployeeServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    Employee employee;
    EmployeeRequestDto employeeRequestDto;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .id(1L)
                .name("Mohammed Sufiyan")
                .age(28)
                .gender("Male")
                .department("IT")
                .salary(75000.0)
                .city("Hyderabad")
                .build();

        employeeRequestDto = EmployeeRequestDto.builder()
                .name("Mohammed Sufiyan")
                .age(28)
                .gender("Male")
                .department("IT")
                .salary(75000.0)
                .city("Hyderabad")
                .build();
    }

    @Test
    void getEmployeeById_shouldReturnEmployee_whenExists() {
        // Arrange
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        // Act
        GenericResponse<EmployeeResponseDto> response = employeeService.getEmployeeById(1L);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Mohammed Sufiyan", response.getData().getName());
        assertEquals("IT", response.getData().getDepartment());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void getEmployeeById_shouldThrowException_whenNotExists() {
        // Arrange
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.getEmployeeById(99L));
        verify(employeeRepository, times(1)).findById(99L);
    }

    @Test
    void getAllEmployees_shouldReturnAllEmployees() {
        // Arrange
        when(employeeRepository.findAll()).thenReturn(List.of(employee));

        // Act
        GenericResponse<List<EmployeeResponseDto>> response = employeeService.getAllEmployees();

        // Assert
        assertTrue(response.isSuccess());
        assertEquals(1, response.getData().size());
        assertEquals("Mohammed Sufiyan", response.getData().get(0).getName());
    }

    @Test
    void saveEmployee_shouldReturnSavedEmployee() {
        // Arrange
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // Act
        GenericResponse<EmployeeResponseDto> response = employeeService.saveEmployee(employeeRequestDto);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Mohammed Sufiyan", response.getData().getName());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void deleteEmployee_shouldDelete_whenExists() {
        // Arrange
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).deleteById(1L);

        // Act
        employeeService.deleteEmployee(1L);

        // Assert
        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteEmployee_shouldThrowException_whenNotExists() {
        // Arrange
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.deleteEmployee(99L));
        verify(employeeRepository, never()).deleteById(any());
    }
}
