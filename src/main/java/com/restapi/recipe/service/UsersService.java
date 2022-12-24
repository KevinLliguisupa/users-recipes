package com.restapi.recipe.service;

import com.restapi.recipe.dto.UsersInfoDTO;
import com.restapi.recipe.model.Users;
import com.restapi.recipe.repository.UsersRepository;
import com.restapi.recipe.specification.UsersSpecification;
import com.restapi.recipe.util.encryptUtility;
import com.restapi.recipe.vo.UsersInsertVO;
import com.restapi.recipe.vo.UsersLoginVO;
import com.restapi.recipe.vo.UsersUpdateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Page<UsersInfoDTO> getUsers(Integer page, Integer size, Boolean enablePagination, Boolean state) {
        Sort sorter = Sort.by("usrName");
        Specification<Users> specification = Specification.where(UsersSpecification.hasState(state));

        if (enablePagination) {
            return toPageUsersInfoDto(usersRepository.findAll(specification, PageRequest.of(page, size, sorter)));
        }
        List<UsersInfoDTO> usersList = toListUsersInfoDto(usersRepository.findAll(specification, sorter));
        return toPage(usersList);
    }

    public Page<UsersInfoDTO> getUsers(Integer page, Integer size, Boolean enablePagination) {
        Sort sorter = Sort.by("usrName");

        if (enablePagination) {
            return toPageUsersInfoDto(usersRepository.findAll(PageRequest.of(page, size, sorter)));
        }
        List<UsersInfoDTO> usersList = toListUsersInfoDto(usersRepository.findAll(sorter));
        return toPage(usersList);

    }

    public UsersInfoDTO getById(Long id) {
        Users original = requireOne(id);
        return toUsersInfoDto(original);
    }

    public int login(UsersLoginVO userVO) {
        try {
            Users originalUser = requireOneByEmail(userVO.getUsrEmail());
            if (!encryptUtility.comparePasswords(userVO.getUsrPassword(), originalUser.getUsrPassword())) {
                return 3;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return 2;
        }
        return 1;
    }

    public UsersInfoDTO save(UsersInsertVO userVO) {
        userVO.setUsrPassword(encryptUtility.encryptPassword(userVO.getUsrPassword()));
        Users bean = new Users();

        BeanUtils.copyProperties(userVO, bean);
        bean.setUsrState(true);
        bean = usersRepository.save(bean);
        return toUsersInfoDto(bean);
    }

    public UsersInfoDTO update(Long id, UsersUpdateVO userVO) {
        Users bean = requireOne(id);

        BeanUtils.copyProperties(userVO, bean);
        usersRepository.save(bean);
        return toUsersInfoDto(bean);
    }

    public UsersInfoDTO changeState(Long id) {
        Users bean = requireOne(id);
        bean.setUsrState(!bean.getUsrState());
        usersRepository.save(bean);
        return toUsersInfoDto(bean);
    }

    public void delete(Long id) {
        usersRepository.deleteById(id);
    }

    public Page<UsersInfoDTO> toPageUsersInfoDto(Page<Users> original) {
        return original.map(this::toUsersInfoDto);
    }

    public List<UsersInfoDTO> toListUsersInfoDto(List<Users> original) {
        List<UsersInfoDTO> converted = new ArrayList<>();
        for (Users user : original) {
            converted.add(toUsersInfoDto(user));
        }
        return converted;
    }

    private UsersInfoDTO toUsersInfoDto(Users original) {
        UsersInfoDTO bean = new UsersInfoDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    public Page<UsersInfoDTO> toPage(List<UsersInfoDTO> list) {
        Pageable pageable = PageRequest.of(0, list.size());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        if (start > list.size())
            return new PageImpl<>(new ArrayList<>(), pageable, list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    private Users requireOne(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    private Users requireOneByEmail(String email) {

        Specification<Users> specification = Specification.where(UsersSpecification.hasEmail(email));

        return usersRepository.findOne(specification)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + email));
    }

//    public String getByEmail(String email) {
//        Users original = requireOneByEmail(email);
//        return original.getUsrPassword();
//    }

    /*   //**
     * Find user using custom repository
     * @param state Boolean state of the user
     * @return List of entities users
     //*
    public List<Users> getUsers(Boolean state){
        return usersRepository.findUsersByState(state);
    }*/

}
