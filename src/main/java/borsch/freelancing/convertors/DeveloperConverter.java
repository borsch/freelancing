package borsch.freelancing.convertors;

import borsch.freelancing.pojo.entities.DeveloperEntity;
import borsch.freelancing.pojo.entities.ProjectEntity;
import borsch.freelancing.pojo.entities.TagEntity;
import borsch.freelancing.pojo.entities.UserEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static borsch.freelancing.convertors.Fields.Developer.*;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
@Service
public class DeveloperConverter extends Converter<DeveloperEntity> {
    @Override
    public Map<String, Object> convert(DeveloperEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();

        if (fields.contains(ID))
            map.put(ID, object.getId());
        if (fields.contains(RATING))
            map.put(RATING, object.getRating());
        if (fields.contains(SKILL_LEVEL))
            map.put(SKILL_LEVEL, object.getSkillLevel());

        Set<TagEntity> tags = object.getTags();
        if (tags != null) {
            if (fields.contains(TAGS))
                map.put(TAGS, tags.stream().map(TagEntity::getTag).collect(Collectors.toList()));
        }

        UserEntity user = object.getUser();
        if (fields.contains(USER_ID))
            map.put(USER_ID, user.getId());
        if (fields.contains(USER_NAME))
            map.put(USER_NAME, user.getName());

        Set<ProjectEntity> projects = object.getProjects();
        if (projects != null) {
            if (fields.contains(PROJECT_IDS))
                map.put(PROJECT_IDS, projects.stream().map(ProjectEntity::getId).collect(Collectors.toList()));
            if (fields.contains(PROJECTS_AMOUNT))
                map.put(PROJECTS_AMOUNT, projects.size());
        }

        return map;
    }
}
