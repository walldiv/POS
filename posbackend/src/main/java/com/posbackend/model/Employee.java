package com.posbackend.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int employeeid;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;
    @Column(name = "socialsecurity")
    private String socialSecurity;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password", length = 60)
    private String password;
    @Column(name = "authorities", columnDefinition = "text[]")
    private String[] authorities;
    @Column(name = "role")
    private String role;
    @Column(name = "isactive")
    private boolean isActive;
    @Column(name = "isnotlocked")
    private boolean isNotLocked;


//    @OneToMany(mappedBy = "employeeid", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Timeclock> timeclocks;

    public Employee() {
    }

    public Employee(int employeeid, String firstName, String lastName, String phoneNumber, String address, String city, String state, LocalDate dateOfBirth, String socialSecurity, String email, String username, String password, String[] authorities, String role, boolean isActive, boolean isNotLocked) {
        this.employeeid = employeeid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.state = state;
        this.dateOfBirth = dateOfBirth;
        this.socialSecurity = socialSecurity;
        this.email = email;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.role = role;
        this.isActive = isActive;
        this.isNotLocked = isNotLocked;
    }

    //Constructor for looking up employees just by email/password given LOGIN METHOD
    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //Constructor for registration via given params
    public Employee(String firstName, String lastName, String username, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeid=" + employeeid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", socialSecurity='" + socialSecurity + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + Arrays.toString(authorities) +
                ", role='" + role + '\'' +
                ", isActive=" + isActive +
                ", isNotLocked=" + isNotLocked +
                '}';
    }

    public int getEmployeeid() {
        return employeeid;
    }
    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getSocialSecurity() {
        return socialSecurity;
    }
    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String[] getAuthorities() {
        return authorities;
    }
    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
    public boolean isNotLocked() {
        return isNotLocked;
    }
    public void setNotLocked(boolean notLocked) {
        isNotLocked = notLocked;
    }
}
