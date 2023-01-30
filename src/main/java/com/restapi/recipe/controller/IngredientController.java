package com.restapi.recipe.controller;

import com.restapi.recipe.dto.IngredientDTO;
import com.restapi.recipe.service.IngredientService;
import com.restapi.recipe.vo.IngredientUpdateVO;
import com.restapi.recipe.vo.IngredientInsertVO;
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
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<Page<IngredientDTO>> getIngredients(
            @RequestParam(required = false, defaultValue = "false") Boolean enablePagination,
            @RequestParam(required = false, defaultValue = "10")    Integer size,
            @RequestParam(required = false, defaultValue = "0")     Integer page) {
        return ResponseEntity.status(HttpStatus.OK).body(ingredientService.getIngredients(enablePagination, size, page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDTO> getById(@Valid @NotNull @PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(ingredientService.getById(id));
    }

    @PostMapping
    public ResponseEntity<IngredientDTO> save(@Valid @RequestBody IngredientInsertVO vO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredientService.save(vO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientDTO> update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody IngredientUpdateVO vO) {
        return ResponseEntity.status(HttpStatus.OK).body(ingredientService.update(id, vO));
    }
}
