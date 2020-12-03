package com.posbackend.repository;

import com.posbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e WHERE e.username = ?1 OR e.password = ?2")
    Employee findByEmployeeCreds(String username, String password);

    Employee findByUsername(String username);
}
