package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employeeDetails) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(employeeDetails.getName());
            employee.setDepartment(employeeDetails.getDepartment());
            employee.setSalary(employeeDetails.getSalary());
            return employeeRepository.save(employee);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeRepository.deleteById(id);
    }

    // Native SQL Endpoints

    @GetMapping("/native")
    public List<Employee> getAllEmployeesNative(@RequestParam(defaultValue = "1") int page) {
        int limit = 5;
        int offset = (page - 1) * limit;
        return employeeRepository.findAllNativePaginated(limit, offset);
    }

    @GetMapping("/native/{id}")
    public Employee getEmployeeByIdNative(@PathVariable Integer id) {
        return employeeRepository.findByIdNative(id).orElse(null);
    }

    @PostMapping("/native")
    public String createEmployeeNative(@RequestBody Employee employee) {
        employeeRepository.saveNative(employee.getName(), employee.getDepartment(), employee.getSalary());
        return "Employee created via Native SQL";
    }

    @PutMapping("/native/{id}")
    public String updateEmployeeNative(@PathVariable Integer id, @RequestBody Employee employeeDetails) {
        employeeRepository.updateNative(id, employeeDetails.getName(), employeeDetails.getDepartment(),
                employeeDetails.getSalary());
        return "Employee updated via Native SQL";
    }

    @DeleteMapping("/native/{id}")
    public String deleteEmployeeNative(@PathVariable Integer id) {
        employeeRepository.deleteNative(id);
        return "Employee deleted via Native SQL";
    }
}
