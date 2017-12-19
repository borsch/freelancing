package borsch.freelancing.convertors;

import borsch.freelancing.pojo.entities.ProjectEntity;
import borsch.freelancing.pojo.entities.TagEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static borsch.freelancing.convertors.Fields.Project.*;

/**
 * Created by olehkurpiak on 18.12.2017.
 */
@Service
public class ProjectConverter extends Converter<ProjectEntity> {

    @Override
    public Map<String, Object> convert(ProjectEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();

        if (fields.contains(ID))
            map.put(ID, object.getId());
        if (fields.contains(NAME))
            map.put(NAME, object.getName());
        if (fields.contains(STATUS))
            map.put(STATUS, object.getStatus());
        if (fields.contains(MIN_SKILL_LEVEL))
            map.put(MIN_SKILL_LEVEL, object.getMinSkillLevel());
        if (fields.contains(DEVELOPER_RATING))
            map.put(DEVELOPER_RATING, object.getDeveloperRating());
        if (fields.contains(CLIENT_RATING))
            map.put(CLIENT_RATING, object.getClientRating());
        if (fields.contains(TAGS))
            map.put(TAGS, object.getTags().stream().map(TagEntity::getTag).collect(Collectors.toList()));
        if (fields.contains(DEVELOPER_ID) && object.getDeveloper() != null)
            map.put(DEVELOPER_ID, object.getDeveloper().getId());
        if (fields.contains(CLIENT_ID))
            map.put(CLIENT_ID, object.getClient().getId());

        return map;
    }
}
