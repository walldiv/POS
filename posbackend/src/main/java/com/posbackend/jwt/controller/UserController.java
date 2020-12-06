package com.posbackend.jwt.controller;

import com.posbackend.jwt.JWTAuthProvider;
import com.posbackend.jwt.JWTTokenProvider;
import com.posbackend.jwt.exception.AccountLockedOutException;
import com.posbackend.jwt.exception.AlreadyExistsException;
import com.posbackend.jwt.exception.ExceptionHandling;
import com.posbackend.jwt.exception.LoginErrorException;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

import static com.posbackend.jwt.config.SecurityConstants.JWT_TOKEN_HEADER;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = {"/", "/user"})
public class UserController extends ExceptionHandling {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private AuthenticationManager authenticationManager;
    private JWTAuthProvider authProvider;
    private JWTTokenProvider jwtTokenProvider;
    private IUserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserController(AuthenticationManager authenticationManager, JWTAuthProvider authProvider, JWTTokenProvider jwtTokenProvider, IUserService userService,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.authProvider = authProvider;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
    public ResponseEntity<Employee> login(@RequestBody Employee employee) throws LoginErrorException, AccountLockedOutException {
        logger.info("INCOMING LOGIN REQUEST => {}   {}", employee.getUsername(), employee.getPassword());
        String encodedPass = this.bCryptPasswordEncoder.encode(employee.getPassword());
        Authentication auth = authenticate(employee.getUsername(), employee.getPassword());
        if(auth == null) {
            throw new AccountLockedOutException("blah");
        }
        Employee tmp = this.userService.findUserByUsername(employee.getUsername());
        if(tmp == null) {
            logger.error("ERROR LOGGING IN EMPLOYEE => {}", employee.getUsername());
            throw new LoginErrorException("blah");
        }
         else {
            logger.info("AUTHENTICATION PASSED!!!    => {}", auth.getCredentials().toString());
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

    private Authentication authenticate(String username, String password) {
        //System.out.println("AuthenticationManager => " + authenticationManager.toString());
        return authProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        //return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
