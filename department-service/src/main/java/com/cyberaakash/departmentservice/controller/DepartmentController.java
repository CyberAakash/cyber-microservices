package com.cyberaakash.departmentservice.controller;

import com.cyberaakash.departmentservice.client.EmployeeClient;
import com.cyberaakash.departmentservice.model.Department;
import com.cyberaakash.departmentservice.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping
    public Department add(@RequestBody Department department) {
        log.info("Department add: {}", department);
        return repository.addDepartment(department);
    }

    @GetMapping
    public List<Department> findAll() {
        log.info("Department find");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        log.info("Department find: id={}", id);
        return repository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        log.info("Department find");
        List<Department> departments
                = repository.findAll();
        departments.forEach(department ->
                department.setEmployees(
                        employeeClient.findByDepartment(department.getId())));
        return  departments;
    }
}
