package com.restapi.recipe.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class IngredientQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long ingId;

    private String ingName;

    private String ingImage;

    private Boolean ingState;

}
