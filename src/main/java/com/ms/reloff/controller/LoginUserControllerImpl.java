package com.ms.reloff.controller;

import static com.ms.reloff.utils.ConstantUtil.LOG_END;
import static com.ms.reloff.utils.ConstantUtil.LOG_START;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.reloff.config.JwtTokenProvider;
import com.ms.reloff.dto.UserAuthRequestDTO;
import com.ms.reloff.dto.UserAuthResponseDTO;
import com.ms.reloff.token.UserPrincipal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${reloff.base-uri}/login")
public class LoginUserControllerImpl implements LoginUserController {

	
	@Autowired
	public AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	public JwtTokenProvider tokenProvider;

	@PostMapping("/auth")
	public ResponseEntity<UserAuthResponseDTO> autenticacionUsuario(@Valid @RequestBody UserAuthRequestDTO dto) throws IOException {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		log.info("PASSWORD");
		log.info(passwordEncoder.encode(dto.getPassword()));

		log.info("[ SIGNIN :: " + dto.getUsername() + ".] ");
		
		Authentication sigin = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(sigin);
		
		UserAuthResponseDTO response = new UserAuthResponseDTO();
		UserPrincipal userPrincipal = (UserPrincipal) sigin.getPrincipal();
		response.setToken(tokenProvider.generateToken(sigin));

		response.setUsername(userPrincipal.getUsername());
		response.setFullName(userPrincipal.getFullName());
		response.setId(userPrincipal.getIdUsuario());
		response.setProfile(userPrincipal.getProfile().getName());

		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
