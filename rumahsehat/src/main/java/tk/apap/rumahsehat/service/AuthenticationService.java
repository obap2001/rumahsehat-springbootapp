package tk.apap.rumahsehat.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import tk.apap.rumahsehat.config.JwtTokenUtil;
import tk.apap.rumahsehat.config.WebSecurityConfig;
import tk.apap.rumahsehat.model.JwtRequest;
import tk.apap.rumahsehat.model.JwtResponse;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtTokenUtil jwtTokenUtil;
    private final WebSecurityConfig webSecurityConfig;
    
    public JwtResponse generateToken(JwtRequest authenticationRequest) {
        try {
            Authentication authentication = webSecurityConfig.authenticationManagerBean().authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenUtil.generateToken(authentication);
            JwtResponse response = new JwtResponse(jwt);
            return response;
        }catch (BadCredentialsException ex){
            log.error("Bad Credentials", ex);
            throw new RuntimeException(ex.getMessage(), ex);
        }catch (Exception ex){
            log.error(ex.getMessage(), ex);
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }
    
}
