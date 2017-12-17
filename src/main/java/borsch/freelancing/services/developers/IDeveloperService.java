package borsch.freelancing.services.developers;

import borsch.freelancing.pojo.entities.DeveloperEntity;
import borsch.freelancing.pojo.view.DeveloperView;
import borsch.freelancing.services.BaseService;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
public abstract class IDeveloperService extends BaseService<DeveloperEntity, DeveloperView, Integer> {
    public IDeveloperService() {
        super(DeveloperEntity.class);
    }
}
