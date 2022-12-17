package tk.apap.rumahsehat.restcontroller;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.apap.rumahsehat.config.JwtTokenUtil;
import tk.apap.rumahsehat.model.JwtRequestLogin;
import tk.apap.rumahsehat.repository.UserDb;
import tk.apap.rumahsehat.security.JwtUserDetailsServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class JwtAuthenticationController {

    protected final Log logger = LogFactory.getLog(getClass());

    final UserDb userRepository;
    final AuthenticationManager authenticationManager;
    final JwtUserDetailsServiceImpl userDetailsService;
    final JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationController(UserDb userRepository, AuthenticationManager authenticationManager,
	JwtUserDetailsServiceImpl userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @CrossOrigin
    @PostMapping("/login/pasien")
    public ResponseEntity loginUser(@RequestBody JwtRequestLogin request) {
        Map<String, Object> responseMap = new HashMap<>();
        String username = request.getUsername();
        String password = request.getPassword();
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (auth.isAuthenticated()) {
                logger.info("Logged In");
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                String token = jwtTokenUtil.generateToken(userDetails);
                responseMap.put("error", false);
                responseMap.put("message", "Logged In");
                responseMap.put("token", token);
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("error", true);
                responseMap.put("message", "Invalid Credentials");
                return ResponseEntity.status(401).body(responseMap);
            }
        } catch (DisabledException e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "User is disabled");
            return ResponseEntity.status(500).body(responseMap);
        } catch (BadCredentialsException e) {
            responseMap.put("error", true);
            responseMap.put("message", "Invalid Credentials");
            return ResponseEntity.status(401).body(responseMap);
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "Something went wrong");
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    

}