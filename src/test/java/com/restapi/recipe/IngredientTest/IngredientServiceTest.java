package com.restapi.recipe.IngredientTest;

import com.restapi.recipe.dto.IngredientDTO;
import com.restapi.recipe.model.Ingredient;
import com.restapi.recipe.repository.IngredientRepository;
import com.restapi.recipe.service.IngredientService;
import com.restapi.recipe.vo.IngredientInsertVO;
import com.restapi.recipe.vo.IngredientUpdateVO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;
    @InjectMocks
    private IngredientService ingredientService;

    private AutoCloseable closeable;
    private Ingredient ingredient;


    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        ingredient = Ingredient.builder()
                .ingName("Cheese")
                .ingImage("cheeseImage.jpg")
                .ingState(true)
                .build();
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @DisplayName("Get ingredients with pagination")
    @Test
    void getIngredientsTestWithPagination() {
        //given
        Ingredient newIngredient = Ingredient.builder()
                .ingName("Milk")
                .ingImage("milkImage.jpg")
                .ingState(true)
                .build();
        Page<Ingredient> ingredientsPage = new PageImpl<>(
                List.of(ingredient, newIngredient), PageRequest.of(0, 2),0);
        given(ingredientRepository.findAll(any(PageRequest.class)))
                .willReturn(ingredientsPage);

        //when
        Page<IngredientDTO> ingredients = ingredientService.getIngredients(true, 3, 3);

        //then
        assertNotNull(ingredients);
    }

    @DisplayName("Get ingredients without pagination")
    @Test
    void getIngredientsTestWithoutPagination() {
        //given
        Ingredient newIngredient = Ingredient.builder()
                .ingName("Milk")
                .ingImage("milkImage.jpg")
                .ingState(true)
                .build();
        given(ingredientRepository.findAll(any(Sort.class))).willReturn(List.of(ingredient, newIngredient));

        //when
        Page<IngredientDTO> ingredients = ingredientService.getIngredients(false, 1, 0);

        //then
        assertNotNull(ingredients);
    }

    @DisplayName("Get ingredients with state 'true'")
    @Test
    void getIngredientsTestWithState() {
        //given
        Ingredient newIngredient = Ingredient.builder()
                .ingName("Milk")
                .ingImage("milkImage.jpg")
                .ingState(true)
                .build();

        given(ingredientRepository.findAll(any(Specification.class), any(Sort.class)))
                .willReturn(List.of(ingredient, newIngredient));

        //when
        Page<IngredientDTO> ingredients = ingredientService.getIngredients(false,
                2, 0,true);

        //then
        assertNotNull(ingredients);
        assertNotEquals(0, ingredients.getTotalElements());
    }

    @DisplayName("Get ingredients with state 'true' and pagination")
    @Test
    void getIngredientsTestWithStateAndPagination() {
        //given
        Ingredient newIngredient = Ingredient.builder()
                .ingName("Milk")
                .ingImage("milkImage.jpg")
                .ingState(true)
                .build();

        Page<Ingredient> ingredientsPage = new PageImpl<>(
                List.of(ingredient, newIngredient), PageRequest.of(0, 1),0);
        given(ingredientRepository.findAll(any(Specification.class), any(PageRequest.class)))
                .willReturn(ingredientsPage);

        //when
        Page<IngredientDTO> ingredients = ingredientService.getIngredients(true,
                1, 0,true);

        //then
        assertNotNull(ingredients);
        assertNotEquals(0, ingredients.getTotalElements());
    }

    @DisplayName("Get list empty")
    @Test
    void getListOfIngredientsEmpty() {
        //given
        given(ingredientRepository.findAll(any(Sort.class)))
                .willReturn(List.of());

        //when
        Page<IngredientDTO> ingredients = ingredientService.getIngredients(false,
                1, 0);

        //then
        assertNotNull(ingredients);

    }

    @DisplayName("Get ingredient existing by ID")
    @Test
    void getByIdTest() {
        //given
        given(ingredientRepository.findById(any(long.class))).willReturn(Optional.of(ingredient));

        //when
        IngredientDTO ingredient = ingredientService.getById((long) 1);

        //then
        assertNotNull(ingredient);
    }

    @DisplayName("Get non-existing ingredient by ID")
    @Test
    void getByIdTestNoExisting() {
        //given
        given(ingredientRepository.findById(any(long.class))).willReturn(Optional.empty());

        //then
        assertThrows(NoSuchElementException.class,() -> {
            //when
            ingredientService.getById((long) 2);
        });
    }

    @DisplayName("Save a Ingredient")
    @Test
    void SaveTest() {
        //given
        given(ingredientRepository.save(any(Ingredient.class))).willReturn(ingredient);

        //when
        IngredientDTO savedIngredient = ingredientService.save(new IngredientInsertVO());

        //then
        assertNotNull(savedIngredient);
    }

    @DisplayName("Update a ingredient")
    @Test
    void UpdateTest() {
        //given
        given(ingredientRepository.findById(any(long.class))).willReturn(Optional.of(ingredient));
        given(ingredientRepository.save(any(Ingredient.class))).willReturn(ingredient);
        ingredient.setIngName("Lettuce");
        ingredient.setIngImage("Lettuce.png");

        //when
        IngredientDTO updatedIngredient = ingredientService.update((long) 1,
                new IngredientUpdateVO(ingredient.getIngName(), ingredient.getIngImage()));

        //then
        assertNotNull(updatedIngredient);
        assertEquals("Lettuce", updatedIngredient.getIngName());
        assertEquals("Lettuce.png", updatedIngredient.getIngImage());
        assertEquals(true, updatedIngredient.getIngState());
    }
}

/*    //Another way to test
    void SaveIngredientTest() {
        //when
        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(ingredient);

        //then
        assertNotNull(ingredientService.save(new IngredientVO()));
    }*/
