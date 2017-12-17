package borsch.freelancing.criteria.impl;

import borsch.freelancing.criteria.Criteria;
import borsch.freelancing.pojo.entities.ClientEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
public class ClientCriteria extends Criteria<ClientEntity> {

    public ClientCriteria() {
        this(0, 0);
    }

    public ClientCriteria(int offset, int limit) {
        super(offset, limit, ClientEntity.class);
    }

    @Override
    public List<Predicate> query(Root<ClientEntity> root, CriteriaBuilder cb) {
        return new ArrayList<>();
    }
}
