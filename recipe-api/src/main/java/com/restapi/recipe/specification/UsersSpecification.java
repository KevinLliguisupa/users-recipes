package com.restapi.recipe.specification;

import com.restapi.recipe.model.Users;
import org.springframework.data.jpa.domain.Specification;

public class UsersSpecification {

    public static Specification<Users> hasState(Boolean state) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("usrState"), state));
    }

    public static Specification<Users> hasEmail(String email) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("usrEmail"), email));
    }

}
