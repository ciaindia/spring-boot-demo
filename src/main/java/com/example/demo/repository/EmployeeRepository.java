package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Native SQL
    @Query(value = "SELECT * FROM employee LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Employee> findAllNativePaginated(@Param("limit") int limit, @Param("offset") int offset);

    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> findAllNative();

    @Query(value = "SELECT * FROM employee WHERE id = :id", nativeQuery = true)
    Optional<Employee> findByIdNative(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO employee (name, department, salary) VALUES (:name, :department, :salary)", nativeQuery = true)
    void saveNative(@Param("name") String name, @Param("department") String department, @Param("salary") Double salary);

    @Modifying
    @Transactional
    @Query(value = "UPDATE employee SET name = :name, department = :department, salary = :salary WHERE id = :id", nativeQuery = true)
    void updateNative(@Param("id") Integer id, @Param("name") String name, @Param("department") String department,
            @Param("salary") Double salary);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM employee WHERE id = :id", nativeQuery = true)
    void deleteNative(@Param("id") Integer id);

    // JPQL
    @Query("SELECT e FROM Employee e")
    List<Employee> findAllJPQL();

    @Query("SELECT e FROM Employee e WHERE e.id = :id")
    Optional<Employee> findByIdJPQL(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.name = :name, e.department = :department, e.salary = :salary WHERE e.id = :id")
    void updateJPQL(@Param("id") Integer id, @Param("name") String name, @Param("department") String department,
            @Param("salary") Double salary);

    @Modifying
    @Transactional
    @Query("DELETE FROM Employee e WHERE e.id = :id")
    void deleteJPQL(@Param("id") Integer id);
}
