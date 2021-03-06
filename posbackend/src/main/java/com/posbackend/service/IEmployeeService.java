package com.posbackend.service;

import com.posbackend.model.Employee;

import java.time.OffsetDateTime;
import java.util.List;

public interface IEmployeeService {

    //getAllUsers
    public List<Employee> findAll();

    //Clock in an employee
    public boolean clockEmployeeIn(Employee employee, OffsetDateTime timeIn);

    //Clock out an employee
    public boolean clockEmployeeOut(Employee employee, OffsetDateTime timeOut);
}
