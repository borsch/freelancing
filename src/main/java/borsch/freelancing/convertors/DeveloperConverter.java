package borsch.freelancing.convertors;

import borsch.freelancing.pojo.entities.DeveloperEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
@Service
public class DeveloperConverter extends Converter<DeveloperEntity> {
    @Override
    public Map<String, Object> convert(DeveloperEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();

        return map;
    }
}
