package com.posbackend.jwt.service;

import com.posbackend.jwt.exception.AlreadyExistsException;
import com.posbackend.jwt.exception.LoginErrorException;
import com.posbackend.jwt.model.UserPrincipal;
import com.posbackend.model.Employee;
import com.posbackend.repository.EmployeeRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import static com.posbackend.jwt.config.RolesEnum.ROLE_USER;

/**
 * This class is defined in SecurityConfiguration.java with the @Qualifier Tag ... which sets up the bean
 * automatically instead of normal dependancy injection like we've practiced in SpringBoot training.
 */

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements IUserService, UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private BCryptPasswordEncoder passwordEncoder;
    private EmployeeRepository empRepository;
    private LoginAttemptService loginAttemptService;

    private static final String ALREADY_EXISTS = "The username or email already exists - please choose another";

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder, EmployeeRepository empRepository,
                           LoginAttemptService loginAttemptService) {
        this.passwordEncoder = passwordEncoder;
        this.empRepository = empRepository;
        this.loginAttemptService = loginAttemptService;
    }


    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }


    @Override
    public Employee register(String firstName, String lastName, String username, String email) throws AlreadyExistsException {
        if(empRepository.findByEmployeeCreds(email, username) != null){
            throw new AlreadyExistsException(ALREADY_EXISTS);
        }
        else {
            Employee tmp = new Employee(firstName, lastName, username, email);
            String password = generatePassword();
            tmp.setPassword(encodePassword(password));
            tmp.setRole(ROLE_USER.name());
            tmp.setAuthorities(ROLE_USER.getAuthorities());
            tmp.setNotLocked(true);
            tmp.setActive(true);
            logger.info("NEW USER PASSWORD => {}", password);
            return empRepository.save(tmp);
        }
    }

    @Override
    public Employee login(Employee employee) throws LoginErrorException {
        Employee tmp = empRepository.findByEmployeeCreds(employee.getUsername(), employee.getPassword());
        if(tmp == null) {
            throw new LoginErrorException("");
        } else{
            return tmp;
        }
    }

    @Override
    public List<Employee> getUsers() {
        return null;
    }

    @Override
    public Employee findUserByUsername(String username) throws UsernameNotFoundException {
        Employee tmp = empRepository.findByUsername(username);
        if(tmp == null) {
            logger.error("NO USER FOUND BY USERNAME => {}", username);
            throw new UsernameNotFoundException("NO USER FOUND BY USERNAME => " + username);
        } else {
            validateLoginAttempt(tmp);
            return tmp;
        }
    }

    @Override
    public Employee findUserByEmail(String email) {
        return null;
    }

    @Override
    public void deleteUser(String username) throws IOException {

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    private void validateLoginAttempt(Employee employee) {
        if (employee.isNotLocked()){
            if(loginAttemptService.hasExceededMaxAttempts(employee.getUsername())) {
                logger.error("TOO MANY LOGIN ATTEMPTS - ACCOUNT IS NOW LOCKED");
                employee.setNotLocked(false);
            } else{
                employee.setNotLocked(true);
            }
        } else{
            logger.error("ACCOUNT IS LOCKED - UNABLE TO LOGIN");
            loginAttemptService.evictUserFromLoginAttemptCache(employee.getUsername());
        }
    }
}
