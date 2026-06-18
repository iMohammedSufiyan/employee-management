package com.sufiyan.employee_management.repository;

import com.sufiyan.employee_management.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findByDepartmentIgnoreCase(String department, Pageable pageable);

    List<Employee> findByCityIgnoreCase(String city);

}
