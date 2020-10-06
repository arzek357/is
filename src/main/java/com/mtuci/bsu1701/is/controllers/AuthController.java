package com.mtuci.bsu1701.is.controllers;

import com.mtuci.bsu1701.is.configs.JwtTokenUtil;
import com.mtuci.bsu1701.is.entities.dtos.RegistrationUserRequestDto;
import com.mtuci.bsu1701.is.entities.dtos.securityDtos.JwtRequest;
import com.mtuci.bsu1701.is.entities.dtos.securityDtos.JwtResponse;
import com.mtuci.bsu1701.is.exceptions.securityExceptions.exceptions.LogInError;
import com.mtuci.bsu1701.is.exceptions.securityExceptions.exceptions.UserAlreadyExistsException;
import com.mtuci.bsu1701.is.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new LogInError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody RegistrationUserRequestDto registrationUserRequestDto) {
        try {
            userService.tryToRegisterUser(registrationUserRequestDto);
        } catch (UserAlreadyExistsException ex) {
            return new ResponseEntity<>(new LogInError(HttpStatus.BAD_REQUEST.value(), String.format("Пользователь с никнеймом %s уже существует!",registrationUserRequestDto.getUsername())), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new LogInError(HttpStatus.OK.value(), "Регистрация прошла успешно!"), HttpStatus.OK);
    }
}
