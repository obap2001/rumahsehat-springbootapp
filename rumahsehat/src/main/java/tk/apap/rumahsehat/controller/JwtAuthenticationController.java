package tk.apap.rumahsehat.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tk.apap.rumahsehat.config.JwtTokenUtil;
import tk.apap.rumahsehat.model.JwtRequest;
import tk.apap.rumahsehat.model.JwtResponse;
import tk.apap.rumahsehat.model.UserModel;
import tk.apap.rumahsehat.repository.UserDb;
import tk.apap.rumahsehat.service.AuthenticationService;


@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {


	private final AuthenticationService authenticationService;

	@RequestMapping(value = "/pasien/login", method = RequestMethod.POST)
	public ResponseEntity<?> pasienLogin(@RequestBody JwtRequest authenticationRequest)
			throws Exception {
				return ResponseEntity.ok(authenticationService.generateToken(authenticationRequest));
			}
}
