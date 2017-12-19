package borsch.freelancing.services.projects;

import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.pojo.entities.ProjectEntity;
import borsch.freelancing.pojo.enums.SkillLevelEnum;
import borsch.freelancing.pojo.view.ProjectView;
import borsch.freelancing.services.BaseService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by olehkurpiak on 18.12.2017.
 */
public abstract class IProjectService extends BaseService<ProjectEntity, ProjectView, Integer> {
    public IProjectService() {
        super(ProjectEntity.class);
    }

    public abstract List<Map<String, Object>> getCurrentUserProjects(Set<String> fields) throws BaseException;

    public abstract List<Map<String, Object>> getCurrentUserProjectsDeveloping(Set<String> fields) throws BaseException;

    public abstract List<Map<String, Object>> suggestProjects(SkillLevelEnum level, List<String> tags, Set<String> fields) throws BaseException;
}
