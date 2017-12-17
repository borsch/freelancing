package borsch.freelancing.persistence.dao.repositories;

import borsch.freelancing.pojo.entities.RoleEntity;
import borsch.freelancing.pojo.enums.RolesEnum;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
public interface RoleRepository extends BaseRepository<RoleEntity, Integer> {

    RoleEntity findByName(RolesEnum name);

}
