package com.app.repository;

import com.app.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeNativeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> findAllNative();

    @Query(value = "SELECT * FROM employee WHERE department = :dept", nativeQuery = true)
    List<Employee> findByDepartmentNative(@Param("dept") String dept);

    @Query(value = "SELECT * FROM employee WHERE salary > :minSalary", nativeQuery = true)
    List<Employee> findHighEarnersNative(@Param("minSalary") Double minSalary);

    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query(value = "INSERT INTO employee (name, department, salary, added_at, updated_at) VALUES (:name, :dept, :salary, NOW(), NOW())", nativeQuery = true)
    void insertEmployeeNative(@Param("name") String name, @Param("dept") String dept, @Param("salary") Double salary);

    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query(value = "UPDATE employee SET salary = :salary, updated_at = NOW() WHERE id = :id", nativeQuery = true)
    int updateSalaryNative(@Param("id") Long id, @Param("salary") Double salary);

    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query(value = "DELETE FROM employee WHERE id = :id", nativeQuery = true)
    void deleteEmployeeNative(@Param("id") Long id);
}
