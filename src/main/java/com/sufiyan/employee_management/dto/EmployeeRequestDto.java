package com.sufiyan.employee_management.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
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
