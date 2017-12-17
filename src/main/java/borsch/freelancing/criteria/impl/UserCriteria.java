package borsch.freelancing.criteria.impl;

import borsch.freelancing.criteria.Criteria;
import borsch.freelancing.pojo.enums.RolesEnum;
import borsch.freelancing.exceptions.bad_request.WrongRestrictionException;
import borsch.freelancing.pojo.entities.RoleEntity;
import borsch.freelancing.pojo.entities.UserEntity;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleh on 08.07.2017.
 */
public class UserCriteria extends Criteria<UserEntity> {

    private String query;
    private List<Integer> ids;
    private List<RolesEnum> roles;
    private RolesEnum role;
    private Boolean active;

    public UserCriteria(String restriction) throws WrongRestrictionException {
        this(0, 0, restriction);
    }

    public UserCriteria(int offset, int limit, String restriction) throws WrongRestrictionException {
        super(offset, limit, UserEntity.class);

        UserCriteria parsed = parse(restriction, UserCriteria.class);
        if (parsed != null) {
            this.query = parsed.query;
            this.ids = parsed.ids;
            this.roles = parsed.roles;
            this.role = parsed.role;
        }
    }

    @Override
    public List<Predicate> query(Root<UserEntity> root, CriteriaBuilder cb){
        List<Predicate> predicates = new ArrayList<>();

        if (this.ids != null && !this.ids.isEmpty()) {
            Expression<Integer> exception = root.get("id");

            predicates.add(exception.in(this.ids));
        }

        if (this.query != null && !this.query.isEmpty()) {
            String likeQuery = '%' + this.query + '%';

            Expression<String> expression = root.get("email");
            Predicate p1 = cb.like(expression, likeQuery);

            expression = root.get("firstName");
            Predicate p2 = cb.like(expression, likeQuery);

            expression = root.get("lastName");
            Predicate p3 = cb.like(expression, likeQuery);

            predicates.add(cb.or(p1, p2, p3));
        }

        if (this.roles != null && !this.roles.isEmpty()) {
            Join<UserEntity, RoleEntity> userRole = root.join("roleEntity");
            Expression<RolesEnum> expression = userRole.get("name");
            predicates.add(expression.in(this.roles));
        }

        if (this.role != null) {
            Join<UserEntity, RoleEntity> userRole = root.join("roleEntity");
            Expression<RolesEnum> expression = userRole.get("name");
            predicates.add(cb.equal(expression, this.role));
        }

        if (this.active != null) {
            Expression<Boolean> expression = root.get("active");

            predicates.add(cb.equal(expression, this.active));
        }

        return predicates;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public List<RolesEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesEnum> roles) {
        this.roles = roles;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public RolesEnum getRole() {
        return role;
    }

    public void setRole(RolesEnum role) {
        this.role = role;
    }
}
