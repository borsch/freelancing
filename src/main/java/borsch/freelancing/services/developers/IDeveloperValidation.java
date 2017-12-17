package borsch.freelancing.services.developers;

import borsch.freelancing.pojo.entities.DeveloperEntity;
import borsch.freelancing.services.BaseValidator;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
public abstract class IDeveloperValidation extends BaseValidator<DeveloperEntity, Integer> {
    public IDeveloperValidation() {
        super(DeveloperEntity.class);
    }
}
