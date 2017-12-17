package borsch.freelancing.criteria.impl;

import borsch.freelancing.criteria.Criteria;
import borsch.freelancing.pojo.entities.TagEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
public class TagCriteria extends Criteria<TagEntity> {

    public TagCriteria() {
        this(0, 0);
    }

    public TagCriteria(int offset, int limit) {
        super(offset, limit, TagEntity.class);
    }

    @Override
    public List<Predicate> query(Root<TagEntity> root, CriteriaBuilder cb) {
        return new ArrayList<>();
    }
}
