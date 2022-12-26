package com.restapi.recipe.controller;

import com.restapi.recipe.dto.UsersInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {

    @GetMapping
    public String gretting(){
        return "Welcome to Api Recipes Service";
    }
}
