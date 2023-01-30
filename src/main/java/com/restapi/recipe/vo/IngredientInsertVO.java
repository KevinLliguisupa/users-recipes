package com.restapi.recipe.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientInsertVO implements Serializable {
    private static final long serialVersionUID = 1L;

//    @NotNull(message = "ingId can not null")
//    private Long ingId;

    @NotNull(message = "ingName can not null")
    private String ingName;

    @NotNull(message = "ingImage can not null")
    private String ingImage;

}
