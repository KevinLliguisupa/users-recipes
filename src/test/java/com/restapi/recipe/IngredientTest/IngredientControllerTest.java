package com.restapi.recipe.IngredientTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.recipe.dto.IngredientDTO;
import com.restapi.recipe.service.IngredientService;
import com.restapi.recipe.service.UserService;
import com.restapi.recipe.vo.IngredientInsertVO;
import com.restapi.recipe.vo.IngredientUpdateVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class IngredientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IngredientService ingredientService;
    @MockBean
    private UserService usersService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void saveIngredientTest() throws Exception {
        //given
        IngredientInsertVO ingredientVo = IngredientInsertVO.builder()
                .ingName("Brocoli")
                .ingImage("http://brocoli.png")
                .build();
        given(ingredientService.save(any(IngredientInsertVO.class)))
                .willReturn(IngredientDTO.builder()
                        .ingName("Brocoli")
                        .ingImage("http://brocoli.png")
                        .ingState(true)
                        .build());

        //when
        ResultActions response = mockMvc.perform(post("/v1/ingredient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ingredientVo)));
        //then
        response.andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.ingName", is(ingredientVo.getIngName())))
                .andExpect(jsonPath("$.ingImage", is(ingredientVo.getIngImage())))
                .andExpect(jsonPath("$.ingState", is(true)));
    }

    @Test
    void getIngredientsTest() throws Exception {
        //given
        List<IngredientDTO> ingredientList = new ArrayList<>();
        ingredientList.add(IngredientDTO.builder().ingName("Brocoli").ingImage("http://brocoli.png").ingState(true).build());
        ingredientList.add(IngredientDTO.builder().ingName("Leche").ingImage("http://leche.png").ingState(true).build());
        ingredientList.add(IngredientDTO.builder().ingName("Harina").ingImage("http://harina.png").ingState(false).build());
        Page<IngredientDTO> ingredientsPage = new PageImpl<>(ingredientList, PageRequest.of(0, 3), 0);

        given(ingredientService.getIngredients(any(Boolean.class), any(Integer.class), any(Integer.class)))
                .willReturn(ingredientsPage);

        //when
        ResultActions response = mockMvc.perform(get("/v1/ingredient")
                .param("enablePagination", "true")
                .param("size", "10")
                .param("page", "0"));
        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.totalElements", is(ingredientList.size())));
    }

    @Test
    void getIngredientByIdTest() throws Exception {
        //given
        Long ingId = 1L;
        IngredientDTO ingredient = IngredientDTO.builder().ingName("Brocoli")
                .ingImage("http://brocoli.png").ingState(true).build();

        given(ingredientService.getById(ingId))
                .willReturn(ingredient);

        //when
        ResultActions response = mockMvc.perform(get("/v1/ingredient/{id}", ingId));
        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.ingName", is(ingredient.getIngName())))
                .andExpect(jsonPath("$.ingImage", is(ingredient.getIngImage())))
                .andExpect(jsonPath("$.ingState", is(true)));
    }

    @Test
    void updateIngredientTest() throws Exception {
        //given
        Long ingId = 1L;
        IngredientDTO savedIngredient = IngredientDTO.builder().ingName("Brocoli")
                .ingImage("http://brocoli.png").ingState(true).build();

        IngredientUpdateVO updatedIngredient = IngredientUpdateVO.builder()
                .ingName("Lechuga")
                .ingImage("http://lechuga.png")
                .build();

        given(ingredientService.getById(ingId)).willReturn(savedIngredient);
        given(ingredientService.update(any(Long.class), any(IngredientUpdateVO.class)))
                .willReturn(IngredientDTO.builder()
                        .ingId(ingId)
                        .ingName("Lechuga")
                        .ingImage("http://lechuga.png")
                        .ingState(true)
                        .build());

        //when
        ResultActions response = mockMvc.perform(put("/v1/ingredient/{id}", ingId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedIngredient)));
        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.ingName", is(updatedIngredient.getIngName())))
                .andExpect(jsonPath("$.ingImage", is(updatedIngredient.getIngImage())))
                .andExpect(jsonPath("$.ingState", is(true)));
    }
}
