package com.restapi.recipe.repository;

import com.restapi.recipe.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<User> findByState(Boolean state) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        Predicate activePredicate = criteriaBuilder.equal(root.get("usrState"), state);

        query.where(activePredicate);
        TypedQuery<User> typedQuery = entityManager.createQuery(query);

        return typedQuery.getResultList();
    }
}
