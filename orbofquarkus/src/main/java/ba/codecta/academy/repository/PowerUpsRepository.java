package ba.codecta.academy.repository;

import ba.codecta.academy.repository.entity.PowerUp;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class PowerUpsRepository extends Repository<PowerUp, Integer>{
    public PowerUpsRepository(){
        super(PowerUp.class);
    }
}
