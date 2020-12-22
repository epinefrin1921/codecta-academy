package ba.codecta.academy.repository;

import ba.codecta.academy.repository.entity.GameCharacter;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class GameCharacterRepository extends Repository<GameCharacter, Integer>{
    public GameCharacterRepository(){
        super(GameCharacter.class);
    }
}
