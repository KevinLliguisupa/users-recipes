package com.restapi.recipe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {

    @GetMapping
    public String greeting(){
        return "Welcome to Api Recipes Service";
    }
}
