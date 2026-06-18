package com.sufiyan.employee_management.controller;

import com.sufiyan.employee_management.dto.EmployeeRequestDto;
import com.sufiyan.employee_management.dto.EmployeeResponseDto;
import com.sufiyan.employee_management.dto.GenericResponse;
import com.sufiyan.employee_management.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/employee")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {

    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<GenericResponse<EmployeeResponseDto>> saveEmployee(@RequestBody @Valid EmployeeRequestDto employeeRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeService.saveEmployee(employeeRequestDto));
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<EmployeeResponseDto>>> getAllEmployees() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeService.getAllEmployees());
    }

    @GetMapping("/paginated")
    public ResponseEntity<GenericResponse<Page<EmployeeResponseDto>>> getAllEmployeesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeService.getAllEmployeesPaginated(page, size, sortBy, sortDirection));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<GenericResponse<EmployeeResponseDto>> getEmployeeById(@PathVariable Long employeeId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeService.getEmployeeById(employeeId));
    }

    @GetMapping("/search/department")
    public ResponseEntity<GenericResponse<Page<EmployeeResponseDto>>> searchByDepartment(
            @RequestParam String department,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeService.searchByDepartment(department, page, size));
    }

    @GetMapping("/search/city")
    public ResponseEntity<GenericResponse<List<EmployeeResponseDto>>> searchByCity(@RequestParam String city) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeService.searchByCity(city));
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<GenericResponse<EmployeeResponseDto>> updateEmployee(
            @PathVariable Long employeeId,
            @RequestBody @Valid EmployeeRequestDto employeeRequestDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeService.updateEmployee(employeeId, employeeRequestDto));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<GenericResponse<EmployeeResponseDto>> deleteEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeService.deleteEmployee(employeeId));
    }
}
