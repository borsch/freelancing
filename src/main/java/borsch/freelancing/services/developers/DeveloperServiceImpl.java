package borsch.freelancing.services.developers;

import borsch.freelancing.criteria.Criteria;
import borsch.freelancing.criteria.impl.DeveloperCriteria;
import borsch.freelancing.exceptions.bad_request.WrongRestrictionException;
import borsch.freelancing.pojo.entities.DeveloperEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class DeveloperServiceImpl extends IDeveloperService {

    @Override
    public Criteria<DeveloperEntity> parse(String restrict) throws WrongRestrictionException {
        return new DeveloperCriteria(restrict);
    }
}
