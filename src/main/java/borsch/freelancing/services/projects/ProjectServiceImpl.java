package borsch.freelancing.services.projects;

import borsch.freelancing.convertors.ProjectConverter;
import borsch.freelancing.criteria.Criteria;
import borsch.freelancing.criteria.impl.ProjectCriteria;
import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.exceptions.bad_request.WrongRestrictionException;
import borsch.freelancing.pojo.entities.ProjectEntity;
import borsch.freelancing.pojo.entities.TagEntity;
import borsch.freelancing.pojo.entities.UserEntity;
import borsch.freelancing.pojo.enums.ProjectStatusEnum;
import borsch.freelancing.pojo.enums.SkillLevelEnum;
import borsch.freelancing.services.Coefficients;
import borsch.freelancing.services.tags.ITagsService;
import borsch.freelancing.services.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by olehkurpiak on 18.12.2017.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ProjectServiceImpl extends IProjectService {

    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    private ProjectConverter converter;

    @Autowired
    private ITagsService tagsService;

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

    @Override
    public List<Map<String, Object>> suggestProjects(SkillLevelEnum level, List<String> tags, Set<String> fields) throws BaseException {
        ProjectCriteria criteria = new ProjectCriteria();
        criteria.setMin_skill_level(level);
        criteria.setStatus(ProjectStatusEnum.NEW);

        List<ProjectEntity> projects = getList(criteria);
        List<Map<String, Object>> result = new ArrayList<>();

        projects.add(ideal(level, tags));

        for (ProjectEntity project : projects) {
            List<String> projectTags = project.getTags().stream().map(TagEntity::getTag).collect(Collectors.toList());

            int commonTags = commonTags(projectTags, tags);

            if (commonTags < 1) {
                continue;
            }

            float commonTagsQuota = (float)commonTags / tags.size();

            // plus one, because middle - middle = 1
            int skillDiff = level.ordinal() - project.getMinSkillLevel().ordinal() + 1;

            Map<String, Object> map = converter.convert(project, fields);
            map.put("score", evaluate(commonTagsQuota, skillDiff));
            map.put("common_tags_percents", (int)(commonTagsQuota * 100));
            result.add(map);
        }

        result.sort(PROJECT_MAP_COMPARATOR);

        return result;
    }

    private float evaluate(float commonTagsQuota, int skillDiff) {
        return skillDiff * Coefficients.getCoefficient(Coefficients.SKILL_LEVEL_WEIGHT) +
                commonTagsQuota * Coefficients.getCoefficient(Coefficients.TAGS_WEIGHT);
    }

    private ProjectEntity ideal(SkillLevelEnum level, List<String> tags) {
        ProjectEntity project = new ProjectEntity();
        project.setTags(tagsService.getAll(tags));
        project.setMinSkillLevel(level);
        project.setName("ideal project");

        return project;
    }

    private int commonTags(List<String> projectTags, List<String> tags) {
        int common = 0;

        for (String projectTag : projectTags) {
            if (tags.contains(projectTag)) {
                ++common;
            }
        }

        return common;
    }

    private static final Comparator<Map<String, Object>> PROJECT_MAP_COMPARATOR = new Comparator<Map<String, Object>>() {
        public int compare(Map<String, Object> m1, Map<String, Object> m2) {
            Float f1 = (Float)m1.get("score");
            Float f2 = (Float)m2.get("score");

            return f2.compareTo(f1);
        }
    };
}
