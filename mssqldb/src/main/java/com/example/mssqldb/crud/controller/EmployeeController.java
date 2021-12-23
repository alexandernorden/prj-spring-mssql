package com.example.mssqldb.crud.controller;

import com.example.mssqldb.crud.exception.ResourceNotFoundException;
import com.example.mssqldb.crud.model.Employee;
import com.example.mssqldb.crud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository empRepo;

    @GetMapping("employees")
    public List<Employee> getAllEmployee() {
        return empRepo.findAll();
    }

    @GetMapping("employees/{id}")
    public ResponseEntity<Employee> getEmployeeId(@PathVariable(value="id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = empRepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found with Id : "+ employeeId));
        return ResponseEntity.ok().body(employee);
    }
}
