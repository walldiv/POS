package com.posbackend.jwt.controller;

import com.posbackend.jwt.JWTTokenProvider;
import com.posbackend.jwt.exception.AlreadyExistsException;
import com.posbackend.jwt.exception.ExceptionHandling;
import com.posbackend.jwt.model.UserPrincipal;
import com.posbackend.jwt.service.IUserService;
import com.posbackend.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

import static com.posbackend.jwt.config.SecurityConstants.JWT_TOKEN_HEADER;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = {"/", "/user"})
public class UserController extends ExceptionHandling {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private AuthenticationManager authenticationManager;
    private JWTTokenProvider jwtTokenProvider;
    private IUserService userService;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider, IUserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String firstPage() {
        return "Hello world!";
    }

    @PostMapping("/register")
    public ResponseEntity<Employee> register(@RequestBody Employee employee) throws AlreadyExistsException {
        Employee tmp = this.userService.register(employee.getFirstName(), employee.getLastName(), employee.getUsername(), employee.getEmail());
        return new ResponseEntity<>(tmp, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Employee> login(@RequestBody Employee employee){
        logger.info("INCOMING LOGIN REQUEST => {}   {}", employee.getUsername(), employee.getPassword());
        authenticate(employee.getUsername(), employee.getPassword());
        logger.info("AUTHENTICATION PASSED!!!");
        Employee tmp = this.userService.login(employee);
        if(tmp == null) {
            logger.error("ERROR LOGGING IN EMPLOYEE => {}", employee.getUsername());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            UserPrincipal userPrincipal = new UserPrincipal(tmp);
            HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
            return new ResponseEntity<>(tmp, jwtHeader, HttpStatus.OK);
        }
    }

    private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(userPrincipal));
        return headers;
    }

    private void authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }
}
