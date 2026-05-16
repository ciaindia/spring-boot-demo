package com.app.controller;

import com.app.model.Employee;
import com.app.repository.EmployeeRepository;
import com.app.repository.EmployeeNativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeNativeRepository employeeNativeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/native")
    public List<Employee> getAllEmployeesNative() {
        return employeeNativeRepository.findAllNative();
    }

    @GetMapping("/native/dept/{dept}")
    public List<Employee> getByDeptNative(@PathVariable String dept) {
        return employeeNativeRepository.findByDepartmentNative(dept);
    }

    @GetMapping("/native/salary/{minSalary}")
    public List<Employee> getHighEarnersNative(@PathVariable Double minSalary) {
        return employeeNativeRepository.findHighEarnersNative(minSalary);
    }

    @PostMapping("/native")
    public String insertNative(@RequestBody Employee employee) {
        employeeNativeRepository.insertEmployeeNative(employee.getName(), employee.getDepartment(), employee.getSalary());
        return "Employee inserted using native SQL!";
    }

    @PutMapping("/native/{id}")
    public String updateSalaryNative(@PathVariable Long id, @RequestParam Double salary) {
        int updated = employeeNativeRepository.updateSalaryNative(id, salary);
        return updated > 0 ? "Salary updated using native SQL!" : "Employee not found.";
    }

    @DeleteMapping("/native/{id}")
    public String deleteNative(@PathVariable Long id) {
        employeeNativeRepository.deleteEmployeeNative(id);
        return "Employee deleted using native SQL!";
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return "Employee deleted with id: " + id;
    }
}
