package borsch.freelancing.services.projects;

import borsch.freelancing.pojo.entities.ProjectEntity;
import borsch.freelancing.services.BaseValidator;

/**
 * Created by olehkurpiak on 18.12.2017.
 */
public abstract class IProjectValidation extends BaseValidator<ProjectEntity, Integer> {
    public IProjectValidation() {
        super(ProjectEntity.class);
    }
}
