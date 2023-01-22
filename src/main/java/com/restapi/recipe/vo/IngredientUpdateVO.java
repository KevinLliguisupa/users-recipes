package com.restapi.recipe.vo;


import com.restapi.recipe.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class IngredientUpdateVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "ingName can not null")
    private String ingName;

    @NotNull(message = "ingImage can not null")
    private String ingImage;
}
