package com.restapi.recipe.specification;

import com.restapi.recipe.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> hasState(Boolean state) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("usrState"), state));
    }

    public static Specification<User> hasEmail(String email) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("usrEmail"), email));
    }

}
