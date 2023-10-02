package com.restapi.recipe.repository;

import com.restapi.recipe.entities.User;

import java.util.List;

public interface UserRepositoryCustom {

    /**
     * Find users by their state this can "true" or "false"
     *
     * @param state Boolean the state of the user
     * @return list of users that have the specified status
     */
    List<User> findByState(Boolean state);

}
