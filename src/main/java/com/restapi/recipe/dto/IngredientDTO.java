package com.restapi.recipe.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long ingId;

    private String ingName;

    private String ingImage;

    private Boolean ingState;

}
