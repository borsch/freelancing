package borsch.freelancing.services.projects;

import borsch.freelancing.convertors.ProjectConverter;
import borsch.freelancing.criteria.Criteria;
import borsch.freelancing.criteria.impl.ProjectCriteria;
import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.exceptions.bad_request.WrongRestrictionException;
import borsch.freelancing.pojo.entities.ProjectEntity;
import borsch.freelancing.pojo.entities.UserEntity;
import borsch.freelancing.services.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by olehkurpiak on 18.12.2017.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ProjectServiceImpl extends IProjectService {

    @Autowired
    private SessionUtils sessionUtils;

    @Override
    public Criteria<ProjectEntity> parse(String restrict) throws WrongRestrictionException {
        return new ProjectCriteria(restrict);
    }

    @Override
    public List<Map<String, Object>> getCurrentUserProjects(Set<String> fields) throws BaseException {
        UserEntity currentUser = sessionUtils.getCurrentUser();

        ProjectCriteria criteria = new ProjectCriteria();
        criteria.setClient_id(currentUser.getClient().getId());

        return getList(criteria, fields);
    }

    @Override
    public List<Map<String, Object>> getCurrentUserProjectsDeveloping(Set<String> fields) throws BaseException {
        UserEntity currentUser = sessionUtils.getCurrentUser();

        ProjectCriteria criteria = new ProjectCriteria();
        criteria.setDeveloper_id(currentUser.getDeveloper().getId());

        return getList(criteria, fields);
    }
}
