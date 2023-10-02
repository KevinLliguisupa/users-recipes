package com.restapi.recipe.vo;


import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
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
