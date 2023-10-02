package com.restapi.recipe.specification;

import com.restapi.recipe.entities.Ingredient;
import org.springframework.data.jpa.domain.Specification;

public class IngredientSpecification{
    public static Specification<Ingredient> hasState(Boolean state) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("ingState"), state));
    }
}
