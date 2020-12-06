package com.posbackend.jwt;

import com.posbackend.jwt.exception.AccountLockedOutException;
import com.posbackend.jwt.model.UserPrincipal;
import com.posbackend.jwt.service.IUserService;
import com.posbackend.jwt.service.LoginAttemptService;
import com.posbackend.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthProvider implements AuthenticationProvider {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private IUserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private LoginAttemptService loginAttemptService;

    @Autowired
    public JWTAuthProvider(IUserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, LoginAttemptService loginAttemptService) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.loginAttemptService = loginAttemptService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        logger.info("AUTHENTICATE => {}    {}", username, password);

        Employee tmp = this.userService.findUserByUsername(username);
        if(tmp == null || !tmp.isNotLocked()) {
            return null;
        }
        if(bCryptPasswordEncoder.matches(password, tmp.getPassword())){
            logger.info("PASSWORDMATCHES!!!");
            final UserPrincipal principal = new UserPrincipal(tmp);
            final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, principal.getAuthorities());
            return auth;
        } else{
            logger.info("PASSWORD DOESNT MATCH");
            try {
                validateLoginAttempt(tmp);
            } catch (AccountLockedOutException e) {
//                e.printStackTrace();
            }
            logger.info("USER LOCKED OUT STATUS => {}", tmp.isNotLocked());
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    /**
     *
     * @param employee
     * if employee.isNotLocked & has NOT exceeded max attempts. - which increments the users count by 1
     */
    private void validateLoginAttempt(Employee employee) throws AccountLockedOutException {
        if (employee.isNotLocked()){
            if(loginAttemptService.hasExceededMaxAttempts(employee.getUsername())) {
                loginAttemptService.evictUserFromLoginAttemptCache(employee.getUsername());
                logger.error("TOO MANY LOGIN ATTEMPTS - ACCOUNT IS NOW LOCKED");
                employee.setNotLocked(false);
            } else{
                logger.info("USERS FAILED ATTEMPTS IS INCREMENTED BY 1");
                loginAttemptService.incrementFailedLoginAttempt(employee.getUsername());
                employee.setNotLocked(true);
            }
        } else{
            logger.error("ACCOUNT IS LOCKED - UNABLE TO LOGIN");
            loginAttemptService.evictUserFromLoginAttemptCache(employee.getUsername());
            employee.setNotLocked(true);
            throw new AccountLockedOutException("blah");
        }
    }
}
