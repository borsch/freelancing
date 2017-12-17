package borsch.freelancing.convertors;

import borsch.freelancing.pojo.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.*;

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

        return map;
    }
}
