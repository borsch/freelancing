package borsch.freelancing.services.clients;

import borsch.freelancing.criteria.Criteria;
import borsch.freelancing.criteria.impl.ClientCriteria;
import borsch.freelancing.exceptions.bad_request.WrongRestrictionException;
import borsch.freelancing.pojo.entities.ClientEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ClientServiceImpl extends IClientService {
    @Override
    public Criteria<ClientEntity> parse(String restrict) throws WrongRestrictionException {
        return new ClientCriteria();
    }
}
