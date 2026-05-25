package com.sufiyan.employee_management.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long employeeId) {
        super("Employee not found with ID: " + employeeId);
    }

    // User can pass custom exception message
    public EmployeeNotFoundException(String msg, Long employeeId) {
        super(msg + " " + employeeId);
    }

}
