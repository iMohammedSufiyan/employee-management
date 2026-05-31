package com.sufiyan.employee_management.service;

import com.sufiyan.employee_management.dto.AuthRequestDto;
import com.sufiyan.employee_management.dto.AuthResponseDto;
import com.sufiyan.employee_management.dto.GenericResponse;
import com.sufiyan.employee_management.dto.RegisterRequestDto;

public interface AuthService {

    GenericResponse<AuthResponseDto> register(RegisterRequestDto request);

    GenericResponse<AuthResponseDto> login(AuthRequestDto request);

}