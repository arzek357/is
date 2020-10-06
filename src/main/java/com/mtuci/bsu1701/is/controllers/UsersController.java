package com.mtuci.bsu1701.is.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.mtuci.bsu1701.is.entities.User;
import com.mtuci.bsu1701.is.entities.dtos.userDtos.UserDto;
import com.mtuci.bsu1701.is.services.UserService;
import com.mtuci.bsu1701.is.utils.Views;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UsersController {
    private UserService userService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(Views.Small.class)
    public User createNewUser(@Valid @RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping("/{id}")
    @JsonView(Views.BigUser.class)
    public User getById(@PathVariable Long id) {
        return userService.findById(id).orElseThrow(() -> new UsernameNotFoundException("User with id = " + id + " not found"));
    }
}
