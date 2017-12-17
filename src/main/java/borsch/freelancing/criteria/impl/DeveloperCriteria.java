package borsch.freelancing.criteria.impl;

import borsch.freelancing.criteria.Criteria;
import borsch.freelancing.pojo.entities.DeveloperEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
public class DeveloperCriteria extends Criteria<DeveloperEntity> {

    public DeveloperCriteria() {
        this(0 ,0);
    }

    public DeveloperCriteria(int offset, int limit) {
        super(offset, limit, DeveloperEntity.class);
    }

    @Override
    public List<Predicate> query(Root<DeveloperEntity> root, CriteriaBuilder cb) {
        return new ArrayList<>();
    }
}
