package com.posbackend.model;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name="timeclock")
public class Timeclock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "entryid")
    private int entryid;

//    @Column(name = "employee")
//    private int employee;

    @Column(name = "timein")
    private OffsetDateTime timeIn;
    @Column(name = "timeout", nullable = true)
    private OffsetDateTime timeOut;

//    @ManyToOne
//    @JoinColumn(name="employeeid")
    @Column(name = "employeeid")
    private int employeeid;

    public Timeclock() {
    }


    public void in(int employee, OffsetDateTime inTime) {
        this.timeIn = inTime;
        this.employeeid = employee;
    }
    public void out(int employee, OffsetDateTime inTime) {
        this.timeOut = inTime;
        this.employeeid = employee;
    }


    @Override
    public String toString() {
        return "Timeclock{" +
                "entryid=" + entryid +
                ", employee=" + employeeid +
                ", timeIn=" + timeIn +
                ", timeOut=" + timeOut +
                '}';
    }

    public int getEntryid() {
        return entryid;
    }

    public void setEntryid(int entryid) {
        this.entryid = entryid;
    }

    public int getEmployee() {
        return employeeid;
    }

    public void setEmployee(int employee) {
        this.employeeid = employee;
    }

    public OffsetDateTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(OffsetDateTime timeIn) {
        this.timeIn = timeIn;
    }

    public OffsetDateTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(OffsetDateTime timeOut) {
        this.timeOut = timeOut;
    }
}
