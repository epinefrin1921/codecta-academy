package ba.codecta.academy.repository;

import ba.codecta.academy.model.DisneyCharacter;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class DisneyCharacterRepository extends Repository<DisneyCharacter, Integer>{

    public DisneyCharacterRepository() {
        super(DisneyCharacter.class);
    }
}
