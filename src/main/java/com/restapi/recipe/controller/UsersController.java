package com.restapi.recipe.controller;

import com.restapi.recipe.dto.UserInfoDTO;
import com.restapi.recipe.exeption.DuplicatedException;
import com.restapi.recipe.service.UserService;
import com.restapi.recipe.vo.UserInsertVO;
import com.restapi.recipe.vo.UserLoginVO;
import com.restapi.recipe.vo.UserUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/v1/user")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserInfoDTO>> getUsers(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "false") Boolean enablePagination,
            @RequestParam(required = false) Boolean usrState) {
        if (usrState != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers(page, size, enablePagination, usrState));
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers(page, size, enablePagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginVO userVO) {

        switch (userService.login(userVO)) {
            case 1:
                return ResponseEntity.status(HttpStatus.OK).body("Correct login");
            case 2:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found!");
            case 3:
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect request");
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody UserInsertVO userVO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userVO));
        } catch (DuplicatedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserInfoDTO> update(@Valid @NotNull @PathVariable("id") Long id,
                                              @Valid @RequestBody UserUpdateVO userVO) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, userVO));
    }

    @PutMapping("/state/{id}")
    public ResponseEntity<UserInfoDTO> changeState(@Valid @NotNull @PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.changeState(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@Valid @NotNull @PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted user: " + id);
    }

    /*    //**
     * Method to find using custom repository
     * @param usrState Boolean state of the user
     * @return List of entities users
     //*
    @GetMapping("/state")
    public ResponseEntity<List<Users>> getUsers1(
            @RequestParam(required = false)  Boolean usrState) {
            return ResponseEntity.status(HttpStatus.OK).body(usersService.getUserds(usrState));
    }*/
}
