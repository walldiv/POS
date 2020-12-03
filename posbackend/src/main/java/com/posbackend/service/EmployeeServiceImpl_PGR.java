package com.posbackend.service;

import com.posbackend.model.Employee;
import com.posbackend.model.Timeclock;
import com.posbackend.repository.EmployeeRepository;
import com.posbackend.repository.TimeclockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class EmployeeServiceImpl_PGR implements IEmployeeService{

    @Autowired
    private EmployeeRepository employeeDao;

    @Autowired
    private TimeclockRepository timeDao;

    public EmployeeServiceImpl_PGR() {
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = (List<Employee>) employeeDao.findAll();
        return employees;
    }

    @Override
    public boolean clockEmployeeIn(Employee employee, OffsetDateTime timeIn) {
        Timeclock timestamp = new Timeclock();
        timestamp.in(employee.getEmployeeid(), timeIn);
        return timeDao.save(timestamp) != null;
    }

    @Override
    public boolean clockEmployeeOut(Employee employee, OffsetDateTime timeOut) {
        Timeclock stamp = null;
        List<Timeclock> tmp = timeDao.getLoggedInTimestamp(employee.getEmployeeid());
        tmp.removeIf(e -> e.getTimeOut() != null);
        for(Timeclock e : tmp) {
            System.out.println(e.toString());
            e.out(employee.getEmployeeid(), timeOut);
            stamp = timeDao.save(e);
            break;
        }
        return stamp == null ? false : true;
    }
}
