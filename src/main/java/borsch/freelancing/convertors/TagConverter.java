package borsch.freelancing.convertors;

import borsch.freelancing.pojo.entities.TagEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
@Service
public class TagConverter extends Converter<TagEntity> {
    @Override
    public Map<String, Object> convert(TagEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();

        return map;
    }
}
