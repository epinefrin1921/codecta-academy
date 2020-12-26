package ba.codecta.academy.repository;

import ba.codecta.academy.repository.entity.Dungeon;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class DungeonRepository extends Repository<Dungeon, Integer>{
    public DungeonRepository(){
        super(Dungeon.class);
    }
    public List<Dungeon> getDungeonsWithOrb(){
        Query query = entityManager.createQuery("SELECT d FROM Dungeon d, PowerUp p where d.powerUp.id=p.id and p.purpose=2");
        return query.getResultList();
    }
    public List<Dungeon> getDungeonsWithoutOrb(){
        Query query = entityManager.createQuery("SELECT d FROM Dungeon d, PowerUp p where d.powerUp.id=p.id and p.purpose<2");
        return query.getResultList();
    }
}
