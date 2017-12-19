package borsch.freelancing.services.developers;

import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.pojo.entities.DeveloperEntity;
import borsch.freelancing.pojo.enums.SkillLevelEnum;
import borsch.freelancing.pojo.view.DeveloperView;
import borsch.freelancing.services.BaseService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
public abstract class IDeveloperService extends BaseService<DeveloperEntity, DeveloperView, Integer> {
    public IDeveloperService() {
        super(DeveloperEntity.class);
    }

    public abstract List<Map<String, Object>> suggestDevelopers(SkillLevelEnum min, List<String> tags, Set<String> fields) throws BaseException;

    public abstract float ratingRecount(DeveloperView view) throws BaseException;
}
