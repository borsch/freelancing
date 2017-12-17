package borsch.freelancing.persistence.dao.repositories;

import borsch.freelancing.pojo.entities.TagEntity;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
public interface TagsRepository extends BaseRepository<TagEntity, Integer> {

    TagEntity findByTag(String tag);

}
