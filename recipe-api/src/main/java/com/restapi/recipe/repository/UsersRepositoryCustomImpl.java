package com.restapi.recipe.repository;

import com.restapi.recipe.model.Users;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class UsersRepositoryCustomImpl implements UsersRepositoryCustom {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<Users> findByState(Boolean state) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Users> query = criteriaBuilder.createQuery(Users.class);
        Root<Users> root = query.from(Users.class);

        Predicate activePredicate = criteriaBuilder.equal(root.get("usrState"), state);

        query.where(activePredicate);
        TypedQuery<Users> typedQuery = entityManager.createQuery(query);

        return typedQuery.getResultList();
    }
}
