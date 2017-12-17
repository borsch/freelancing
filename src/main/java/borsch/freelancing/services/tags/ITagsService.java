package borsch.freelancing.services.tags;

import borsch.freelancing.pojo.entities.TagEntity;
import borsch.freelancing.pojo.view.TagView;
import borsch.freelancing.services.BaseService;

import java.util.List;
import java.util.Set;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
public abstract class ITagsService extends BaseService<TagEntity, TagView, Integer> {
    public ITagsService() {
        super(TagEntity.class);
    }

    public abstract Set<TagEntity> getAll(List<String> tags);
}
