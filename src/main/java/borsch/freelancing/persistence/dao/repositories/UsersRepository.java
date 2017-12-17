package borsch.freelancing.persistence.dao.repositories;

import borsch.freelancing.pojo.entities.UserEntity;

/**
 * Created by Andrii on 18.08.2016.
 */
public interface UsersRepository extends BaseRepository<UserEntity,Integer>{
    public UserEntity findByEmail(String email);
}
