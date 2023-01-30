package com.restapi.recipe.service;

import com.restapi.recipe.dto.IngredientDTO;
import com.restapi.recipe.model.Ingredient;
import com.restapi.recipe.repository.IngredientRepository;
import com.restapi.recipe.specification.IngredientSpecification;
import com.restapi.recipe.vo.IngredientInsertVO;
import com.restapi.recipe.vo.IngredientUpdateVO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public Page<IngredientDTO> getIngredients(@NotNull Boolean enablePagination, Integer size, Integer page) {
        Sort sorter = Sort.by("ingName");

        if (enablePagination) {
            return toPageIngredientDto(ingredientRepository.findAll(PageRequest.of(page, size, sorter)));
        }
        List<IngredientDTO> ingredientsList = toListIngredientDto(ingredientRepository.findAll(sorter));
        return toPage(ingredientsList);

    }

    public Page<IngredientDTO> getIngredients(@NotNull Boolean enablePagination, Integer size, Integer page, Boolean state) {
        Sort sorter = Sort.by("usrName");
        Specification<Ingredient> specification = Specification.where(IngredientSpecification.hasState(state));

        if (enablePagination) {
            return toPageIngredientDto(ingredientRepository.findAll(specification, PageRequest.of(page, size, sorter)));
        }
        List<IngredientDTO> ingredientsList = toListIngredientDto(ingredientRepository.findAll(specification, sorter));
        return toPage(ingredientsList);
    }

    public IngredientDTO getById(Long id) {
        Ingredient original = requireOne(id);
        return toDTO(original);
    }

    public IngredientDTO save(IngredientInsertVO vO) {
        Ingredient bean = new Ingredient();
        BeanUtils.copyProperties(vO, bean);
        bean.setIngState(true);
        bean = ingredientRepository.save(bean);
        return toDTO(bean);
    }

//    public void delete(Long id) {
//        ingredientRepository.deleteById(id);
//    }

    public IngredientDTO update(Long id, IngredientUpdateVO vO) {
        Ingredient bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ingredientRepository.save(bean);
        return toDTO(bean);
    }


    private IngredientDTO toDTO(Ingredient original) {
        IngredientDTO bean = new IngredientDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Ingredient requireOne(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    private Page<IngredientDTO> toPageIngredientDto(Page<Ingredient> original) {
        return original.map(this::toDTO);
    }

    private List<IngredientDTO> toListIngredientDto(List<Ingredient> original) {
        List<IngredientDTO> converted = new ArrayList<>();
        for (Ingredient ingredient : original) {
            converted.add(toDTO(ingredient));
        }
        return converted;
    }

    private Page<IngredientDTO> toPage(List<IngredientDTO> list) {
        int listSize = list.size() == 0 ? 10 : list.size();
        Pageable pageable = PageRequest.of(0, listSize);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        if (start > list.size())
            return new PageImpl<>(new ArrayList<>(), pageable, list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }
}
