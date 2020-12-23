package ba.codecta.academy.repository;

import ba.codecta.academy.repository.entity.Monster;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class MonsterRepository extends Repository<Monster, Integer>{
    public MonsterRepository(){
        super(Monster.class);
    }

}
