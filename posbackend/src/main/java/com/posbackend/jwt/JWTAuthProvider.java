package com.posbackend.jwt;

import com.posbackend.jwt.model.UserPrincipal;
import com.posbackend.jwt.service.IUserService;
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

    @Autowired
    public JWTAuthProvider(IUserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        logger.info("AUTHENTICATE => {}    {}", username, password);

        Employee tmp = this.userService.findUserByUsername(username);
        if(tmp == null) {
            return null;
        }
        if(bCryptPasswordEncoder.matches(password, tmp.getPassword())){
            logger.info("PASSWORDMATCHES!!!");
            final UserPrincipal principal = new UserPrincipal(tmp);
            final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, principal.getAuthorities());
            return auth;
        } else{
            logger.info("PASSWORD DOESNT MATCH");
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
