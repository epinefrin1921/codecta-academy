package com.codecta.academy.repository;

import com.codecta.academy.repository.entity.DisneyCharacter;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class DisneyCharacterRepository extends Repository<DisneyCharacter, Integer>{

    public DisneyCharacterRepository() {
        super(DisneyCharacter.class);
    }

    public List<DisneyCharacter> findAllByParkId(Integer parkId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<DisneyCharacter> cq = cb.createQuery(DisneyCharacter.class);
        Root<DisneyCharacter> root = cq.from(DisneyCharacter.class);
        root.fetch("themePark", JoinType.INNER);
        CriteriaQuery<DisneyCharacter> all = cq.select(root);
        all.where(cb.equal(root.get("themePark"), parkId));
        TypedQuery<DisneyCharacter> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }
    public List<DisneyCharacter> findByCharacterNameAndGreeting(String name, String greeting){
        Query query = entityManager.createQuery("SELECT distinct t FROM DisneyCharacter t  where t.name=:name and t.greeting = :greeting");
        query.setParameter("name", name);
        query.setParameter("greeting", greeting);
        List resultList = query.getResultList();
        return (List<DisneyCharacter>) resultList;
    }
}
