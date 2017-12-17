package borsch.freelancing.services.clients;

import borsch.freelancing.pojo.entities.ClientEntity;
import borsch.freelancing.services.BaseValidator;
import org.springframework.stereotype.Service;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
public abstract class IClientValidation extends BaseValidator<ClientEntity, Integer> {
    public IClientValidation() {
        super(ClientEntity.class);
    }
}
