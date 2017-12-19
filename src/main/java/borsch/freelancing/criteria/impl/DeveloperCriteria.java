package borsch.freelancing.criteria.impl;

import borsch.freelancing.criteria.Criteria;
import borsch.freelancing.exceptions.bad_request.WrongRestrictionException;
import borsch.freelancing.pojo.entities.DeveloperEntity;
import borsch.freelancing.pojo.entities.TagEntity;
import borsch.freelancing.pojo.enums.SkillLevelEnum;
import borsch.freelancing.services.tags.ITagsService;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
public class DeveloperCriteria extends Criteria<DeveloperEntity> {

    private SkillLevelEnum skill_level;
    private SkillLevelEnum min_skill_level;
    private List<String> tags;

    public DeveloperCriteria(String restrict) throws WrongRestrictionException {
        this(0, 0);

        DeveloperCriteria parsed = parse(restrict, DeveloperCriteria.class);
        if (parsed != null) {
            this.skill_level = parsed.skill_level;
            this.tags = parsed.tags;
            this.min_skill_level = parsed.min_skill_level;
        }
    }

    public DeveloperCriteria() {
        this(0 ,0);
    }

    public DeveloperCriteria(int offset, int limit) {
        super(offset, limit, DeveloperEntity.class);
    }

    @Override
    public List<Predicate> query(Root<DeveloperEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (skill_level != null) {
            Expression<SkillLevelEnum> expression = root.get("skillLevel");

            predicates.add(cb.equal(expression, skill_level));
        }

        if (min_skill_level != null) {
            Expression<SkillLevelEnum> expression = root.get("skillLevel");

            predicates.add(cb.greaterThanOrEqualTo(expression, min_skill_level));
        }

        if (tags != null && !tags.isEmpty()) {
            Join<DeveloperEntity, TagEntity> tagsJoin = root.join("tags", JoinType.INNER);
            Expression<String> expression = tagsJoin.get("tag");

            predicates.add(expression.in(tags));
        }

        return predicates;
    }

    public SkillLevelEnum getSkill_level() {
        return skill_level;
    }

    public void setSkill_level(SkillLevelEnum skill_level) {
        this.skill_level = skill_level;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public SkillLevelEnum getMin_skill_level() {
        return min_skill_level;
    }

    public void setMin_skill_level(SkillLevelEnum min_skill_level) {
        this.min_skill_level = min_skill_level;
    }
}
