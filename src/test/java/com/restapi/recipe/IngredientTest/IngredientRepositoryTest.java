package com.restapi.recipe.IngredientTest;

import com.restapi.recipe.entities.Ingredient;
import com.restapi.recipe.repository.IngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IngredientRepositoryTest {

    @Autowired
    private IngredientRepository ingredientRepository;

    private Ingredient ingredient;

    @BeforeEach
    void setup() {
        ingredient = Ingredient.builder()
                .ingName("Cheese")
                .ingImage("cheeseImage.jpg")
                .ingState(true)
                .build();
    }

    @DisplayName("Should get a list with all ingredients")
    @Test
    void findAllIngredientsTest() {
        //given
        Ingredient newIngredient = Ingredient.builder()
                .ingName("Milk")
                .ingImage("milkImage.jpg")
                .ingState(true)
                .build();
        ingredientRepository.save(ingredient);
        ingredientRepository.save(newIngredient);

        //when
        List<Ingredient> ingredients = ingredientRepository.findAll();

        //then
        assertNotNull(ingredients);
//        assertEquals(2, ingredients.size());

    }

    @DisplayName("Should find an ingredient with the given id")
    @Test
    void findIngredientByIdTest() {
        //given
        ingredientRepository.save(ingredient);

        //when
        Ingredient foundIngredient = ingredientRepository.findById(ingredient.getIngId()).get();

        //then
        assertNotNull(foundIngredient);
    }

    @DisplayName("Should save a ingredient")
    @Test
    void saveIngredientTest() {
        //given - Conditions prior to the action of the function.
        Ingredient newIngredient = Ingredient.builder()
                .ingName("Milk")
                .ingImage("milkImage.jpg")
                .ingState(true)
                .build();

        //when - The set of actions to be executed
        Ingredient savedIngredient = ingredientRepository.save(newIngredient);

        //Then - The expected results. From them, the validations are made.
        assertNotNull(savedIngredient);
        assertNotEquals(0, savedIngredient.getIngId());
    }

    @DisplayName("Should find an ingredient with the given id")
    @Test
    void updateIngredientTest() {
        //given
        ingredientRepository.save(ingredient);

        //when
        Ingredient savedIngredient = ingredientRepository.findById(ingredient.getIngId()).get();
        savedIngredient.setIngName("Mozzarella Cheese");
        savedIngredient.setIngImage("mozzarellaCheese.png");
        savedIngredient.setIngState(false);

        Ingredient updatedIngredient = ingredientRepository.save(savedIngredient);

        //then
        assertEquals("Mozzarella Cheese", updatedIngredient.getIngName());
        assertEquals("mozzarellaCheese.png", updatedIngredient.getIngImage());
        assertEquals(false, updatedIngredient.getIngState());
    }

    @DisplayName("Should delete an ingredient with the given id")
    @Test
    void deleteIngredientTest() {
        //given
        ingredientRepository.save(ingredient);

        //when
        ingredientRepository.deleteById(ingredient.getIngId());
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(ingredient.getIngId());

        //then
        assertEquals(Optional.empty(), optionalIngredient);
    }
}
