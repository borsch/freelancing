package borsch.freelancing.criteria.impl;

import borsch.freelancing.criteria.Criteria;
import borsch.freelancing.exceptions.bad_request.WrongRestrictionException;
import borsch.freelancing.pojo.entities.ClientEntity;
import borsch.freelancing.pojo.entities.DeveloperEntity;
import borsch.freelancing.pojo.entities.ProjectEntity;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olehkurpiak on 18.12.2017.
 */
public class ProjectCriteria extends Criteria<ProjectEntity> {

    private Integer developer_id;
    private Integer client_id;

    public ProjectCriteria(String restrict) throws WrongRestrictionException {
        this();

        ProjectCriteria parsed = parse(restrict, ProjectCriteria.class);

        if (parsed != null) {
            this.developer_id = parsed.developer_id;
            this.client_id = parsed.developer_id;
        }
    }

    public ProjectCriteria() {
        this(0, 0);
    }

    public ProjectCriteria(int offset, int limit) {
        super(offset, limit, ProjectEntity.class);
    }

    @Override
    public List<Predicate> query(Root<ProjectEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (developer_id != null) {
            Join<ProjectEntity, DeveloperEntity> developerJoin = root.join("developer", JoinType.INNER);
            Expression<Integer> expression = developerJoin.get("id");

            predicates.add(cb.equal(expression, developer_id));
        }

        if (client_id != null) {
            Join<ProjectEntity, ClientEntity> clientJoin = root.join("client", JoinType.INNER);
            Expression<Integer> expression = clientJoin.get("id");

            predicates.add(cb.equal(expression, client_id));
        }

        return predicates;
    }

    public Integer getDeveloper_id() {
        return developer_id;
    }

    public void setDeveloper_id(Integer developer_id) {
        this.developer_id = developer_id;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }
}
