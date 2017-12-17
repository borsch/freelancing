package borsch.freelancing.convertors;

import borsch.freelancing.pojo.entities.ClientEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
@Service
public class ClientConverter extends Converter<ClientEntity> {


    @Override
    public Map<String, Object> convert(ClientEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();

        return map;
    }
}
