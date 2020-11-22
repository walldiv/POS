package com.posbackend.service;

import com.posbackend.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Clock;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImplPGRTest {

    private LocalDate dob = LocalDate.of(1978, 05, 27);
    private Employee thisEmp = new Employee(1, "Dan", "Wallace", "6169156238", "340 Northland", "Rockford", "MI", dob,
            "378884957", "walldiv@gmail.com", "1234");
    private OffsetDateTime timeStamp = OffsetDateTime.now(Clock.systemDefaultZone());

    @Autowired
    private EmployeeServiceImpl_PGR svc;

    @Test
    public void testEmployeeLogin() {
        assertThat(svc).isNotNull();
        Employee test = svc.getEmployeeByLogin(thisEmp);
        System.out.printf("EMPLOYEE => %s", test.toString());
        Assert.assertNotNull("EMPLOYEE IS NULL", test);
    }

    @Test
    public void testClockEmployeeIn() {
        assertThat(svc).isNotNull();
        boolean result = svc.clockEmployeeIn(thisEmp, timeStamp);
        Assert.assertTrue("TIME STAMP NOT SAVED", result);
    }

    @Test
    public void testClockEmployeeOut() {
        assertThat(svc).isNotNull();
        boolean result = svc.clockEmployeeOut(thisEmp, timeStamp);
        Assert.assertTrue("NO CLOCKOUT PERFORMED", result);
    }
}