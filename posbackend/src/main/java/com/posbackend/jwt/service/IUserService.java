package com.posbackend.jwt.service;


import com.posbackend.jwt.exception.AlreadyExistsException;
import com.posbackend.jwt.exception.LoginErrorException;
import com.posbackend.model.Employee;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;
import java.util.List;

public interface IUserService {
    Employee register(String firstName, String lastName, String username, String email) throws AlreadyExistsException;

    Employee login(Employee employee) throws LoginErrorException;

    List<Employee> getUsers();

    Employee findUserByUsername(String username) throws UsernameNotFoundException;

    Employee findUserByEmail(String email);

    //Employee addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;

    //Employee updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;

    void deleteUser(String username) throws IOException;

    //void resetPassword(String email) throws MessagingException, EmailNotFoundException;

}
