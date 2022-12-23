package com.restapi.recipe.repository;

import com.restapi.recipe.model.Users;

import java.util.List;

public interface UsersRepositoryCustom {

    /**
     * Find users by their state this can "true" or "false"
     *
     * @param state Boolean the state of the user
     * @return list of users that have the specified status
     */
    List<Users> findByState(Boolean state);

}
