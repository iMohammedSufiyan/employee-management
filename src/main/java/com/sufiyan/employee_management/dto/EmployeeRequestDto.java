package com.sufiyan.employee_management.dto;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeRequestDto {

    @NotBlank(message = "Name is required")
    String name;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be less than 100")
    Integer age;

    @NotBlank(message = "Gender is required")
    String gender;

    @NotBlank(message = "Department is required")
    String department;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be positive")
    Double salary;

    @NotBlank(message = "City is required")
    String city;
}
