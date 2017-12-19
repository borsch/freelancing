package borsch.freelancing.criteria.impl;

import borsch.freelancing.criteria.Criteria;
import borsch.freelancing.exceptions.bad_request.WrongRestrictionException;
import borsch.freelancing.pojo.entities.ClientEntity;
import borsch.freelancing.pojo.entities.DeveloperEntity;
import borsch.freelancing.pojo.entities.ProjectEntity;
import borsch.freelancing.pojo.entities.TagEntity;
import borsch.freelancing.pojo.enums.ProjectStatusEnum;
import borsch.freelancing.pojo.enums.SkillLevelEnum;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olehkurpiak on 18.12.2017.
 */
public class ProjectCriteria extends Criteria<ProjectEntity> {

    private Integer developer_id;
    private Integer client_id;
    private SkillLevelEnum min_skill_level;
    private List<String> tags;
    private ProjectStatusEnum status;

    public ProjectCriteria(String restrict) throws WrongRestrictionException {
        this();

        ProjectCriteria parsed = parse(restrict, ProjectCriteria.class);

        if (parsed != null) {
            this.developer_id = parsed.developer_id;
            this.client_id = parsed.developer_id;
            this.min_skill_level = parsed.min_skill_level;
            this.tags = parsed.tags;
            this.status = parsed.status;
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

        if (min_skill_level != null) {
            Expression<SkillLevelEnum> expression = root.get("minSkillLevel");

            predicates.add(cb.lessThanOrEqualTo(expression, min_skill_level));
        }

        if (status != null) {
            Expression<ProjectStatusEnum> expression = root.get("status");

            predicates.add(cb.lessThanOrEqualTo(expression, status));
        }

        if (tags != null && !tags.isEmpty()) {
            Join<DeveloperEntity, TagEntity> tagsJoin = root.join("tags", JoinType.INNER);
            Expression<String> expression = tagsJoin.get("tag");

            predicates.add(expression.in(tags));
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

    public SkillLevelEnum getMin_skill_level() {
        return min_skill_level;
    }

    public void setMin_skill_level(SkillLevelEnum min_skill_level) {
        this.min_skill_level = min_skill_level;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public ProjectStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ProjectStatusEnum status) {
        this.status = status;
    }
}
