package com.restapi.recipe.service;

import com.restapi.recipe.dto.UserInfoDTO;
import com.restapi.recipe.entities.User;
import com.restapi.recipe.exeption.DuplicatedException;
import com.restapi.recipe.repository.UserRepository;
import com.restapi.recipe.specification.UserSpecification;
import com.restapi.recipe.util.encryptionUtility;
import com.restapi.recipe.vo.UserInsertVO;
import com.restapi.recipe.vo.UserLoginVO;
import com.restapi.recipe.vo.UserUpdateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository usersRepository;

    public Page<UserInfoDTO> getUsers(Integer page, Integer size, Boolean enablePagination, Boolean state) {
        Sort sorter = Sort.by("usrName");
        Specification<User> specification = Specification.where(UserSpecification.hasState(state));

        if (enablePagination) {
            return toPageUserInfoDto(usersRepository.findAll(specification, PageRequest.of(page, size, sorter)));
        }
        List<UserInfoDTO> usersList = toListUserInfoDto(usersRepository.findAll(specification, sorter));
        return toPage(usersList);
    }

    public Page<UserInfoDTO> getUsers(Integer page, Integer size, Boolean enablePagination) {
        Sort sorter = Sort.by("usrName");

        if (enablePagination) {
            return toPageUserInfoDto(usersRepository.findAll(PageRequest.of(page, size, sorter)));
        }
        List<UserInfoDTO> usersList = toListUserInfoDto(usersRepository.findAll(sorter));
        return toPage(usersList);

    }

    public UserInfoDTO getById(Long id) {
        User original = requireOne(id);
        return toUserInfoDto(original);
    }

    public int login(UserLoginVO userVO) {
        User originalUser = requireOneByEmail(userVO.getUsrEmail());
        if (originalUser == null) {
            return 2;
        } else {
            if (!encryptionUtility.comparePasswords(userVO.getUsrPassword(), originalUser.getUsrPassword())) {
                return 3;
            }
        }

        return 1;
    }

    public UserInfoDTO save(UserInsertVO userVO) {
        User existingUser = requireOneByEmail(userVO.getUsrEmail());
        System.out.println(existingUser);
        if (existingUser != null) {
            throw new DuplicatedException("User email already used");
        }
        userVO.setUsrPassword(encryptionUtility.encryptPassword(userVO.getUsrPassword()));
        User bean = new User();

        BeanUtils.copyProperties(userVO, bean);
        bean.setUsrState(true);
        bean = usersRepository.save(bean);
        return toUserInfoDto(bean);
    }

    public UserInfoDTO update(Long id, UserUpdateVO userVO) {
        User bean = requireOne(id);

        BeanUtils.copyProperties(userVO, bean);
        usersRepository.save(bean);
        return toUserInfoDto(bean);
    }

    public UserInfoDTO changeState(Long id) {
        User bean = requireOne(id);
        bean.setUsrState(!bean.getUsrState());
        usersRepository.save(bean);
        return toUserInfoDto(bean);
    }

//    public void delete(Long id) {
//        usersRepository.deleteById(id);
//    }

    public Page<UserInfoDTO> toPageUserInfoDto(Page<User> original) {
        return original.map(this::toUserInfoDto);
    }

    public List<UserInfoDTO> toListUserInfoDto(List<User> original) {
        List<UserInfoDTO> converted = new ArrayList<>();
        for (User user : original) {
            converted.add(toUserInfoDto(user));
        }
        return converted;
    }

    private UserInfoDTO toUserInfoDto(User original) {
        UserInfoDTO bean = new UserInfoDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    public Page<UserInfoDTO> toPage(List<UserInfoDTO> list) {
        Pageable pageable = PageRequest.of(0, list.size());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        if (start > list.size())
            return new PageImpl<>(new ArrayList<>(), pageable, list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    private User requireOne(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User id not found: " + id));
    }

    private User requireOneByEmail(String email) {

        Specification<User> specification = Specification.where(UserSpecification.hasEmail(email));

        return usersRepository.findOne(specification)
                .orElse(null);
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
