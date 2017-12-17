package borsch.freelancing.services.tags;

import borsch.freelancing.pojo.entities.TagEntity;
import borsch.freelancing.services.BaseValidator;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
public abstract class ITagsValidation extends BaseValidator<TagEntity, Integer> {

    public ITagsValidation() {
        super(TagEntity.class);
    }
}
