package com.sufiyan.employee_management.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResponseDto {
    Long id;
    String name;
    Integer age;
    String gender;
    String department;
    Double salary;
    String city;
}
