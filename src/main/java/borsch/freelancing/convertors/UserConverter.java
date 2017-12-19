package borsch.freelancing.convertors;

import borsch.freelancing.pojo.entities.ClientEntity;
import borsch.freelancing.pojo.entities.DeveloperEntity;
import borsch.freelancing.pojo.entities.TagEntity;
import borsch.freelancing.pojo.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static borsch.freelancing.convertors.Fields.User.*;

/**
 * Created by oleh_kurpiak on 07.09.2016.
 */
@Component
public class UserConverter extends Converter<UserEntity> {
    @Override
    public Map<String, Object> convert(UserEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();

        if(fields.contains(ID))
            map.put(ID, object.getId());
        if(fields.contains(EMAIL))
            map.put(EMAIL, object.getEmail());
        if(fields.contains(ROLE))
            map.put(ROLE, object.getRoleEntity().getName());
        if (fields.contains(NAME))
            map.put(NAME, object.getName());

        ClientEntity client = object.getClient();
        if (client != null) {
            if (fields.contains(CLIENT_ID))
                map.put(CLIENT_ID, client.getId());
            if (fields.contains(CLIENT_RATING))
                map.put(CLIENT_RATING, client.getRating());
            if (fields.contains(CLIENT_PROJECTS_AMOUNT))
                map.put(CLIENT_PROJECTS_AMOUNT, client.getProjects().size());
        }

        DeveloperEntity developer = object.getDeveloper();
        if (developer != null) {
            if (fields.contains(DEVELOPER_ID))
                map.put(DEVELOPER_ID, developer.getId());
            if (fields.contains(DEVELOPER_PROJECTS_AMOUNT))
                map.put(DEVELOPER_PROJECTS_AMOUNT, developer.getProjects().size());
            if (fields.contains(DEVELOPER_RATING))
                map.put(DEVELOPER_RATING, developer.getRating());
            if (fields.contains(DEVELOPER_SKILL_LEVEL))
                map.put(DEVELOPER_SKILL_LEVEL, developer.getSkillLevel());
            if (fields.contains(DEVELOPER_TAGS))
                map.put(DEVELOPER_TAGS, developer.getTags().stream().map(TagEntity::getTag).collect(Collectors.toList()));
        }

        return map;
    }
}
