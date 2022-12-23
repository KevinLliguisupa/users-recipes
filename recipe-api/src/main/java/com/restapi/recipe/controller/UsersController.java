package com.restapi.recipe.controller;

import com.restapi.recipe.dto.UsersInfoDTO;
import com.restapi.recipe.service.UsersService;
import com.restapi.recipe.vo.UsersLoginVO;
import com.restapi.recipe.vo.UsersUpdateVO;
import com.restapi.recipe.vo.UsersInsertVO;
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
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<Page<UsersInfoDTO>> getUsers(
            @RequestParam(required = false, defaultValue = "0")     Integer page,
            @RequestParam(required = false, defaultValue = "10")    Integer size,
            @RequestParam(required = false, defaultValue = "false") Boolean enablePagination,
            @RequestParam(required = false)  Boolean usrState) {
        if(usrState != null){
            return ResponseEntity.status(HttpStatus.OK).body(usersService.getUsers(page, size, enablePagination, usrState));
        }
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getUsers(page, size, enablePagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersInfoDTO> getById(@Valid @NotNull @PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getById(id));
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UsersLoginVO userVO) {

        switch (usersService.login(userVO)){
            case 1 : return ResponseEntity.status(HttpStatus.OK).body("Correct login");
            case 2 : return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found!");
            case 3 : return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect request");
    }

    @PostMapping
    public ResponseEntity<UsersInfoDTO> save(@Valid @RequestBody UsersInsertVO userVO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.save(userVO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersInfoDTO> update(@Valid @NotNull @PathVariable("id") Long id,
                                           @Valid @RequestBody UsersUpdateVO userVO) {

        return ResponseEntity.status(HttpStatus.OK).body(usersService.update(id, userVO));
    }

    @PutMapping("/state/{id}")
    public ResponseEntity<UsersInfoDTO> changeState(@Valid @NotNull @PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.changeState(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@Valid @NotNull @PathVariable("id") Long id) {
        usersService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted user: " + id);
    }

//    @GetMapping("/login")
//    public ResponseEntity<String> getByEmail(@Valid @RequestParam String email) {
//        return ResponseEntity.status(HttpStatus.OK).body(usersService.getByEmail(email));
//    }


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
