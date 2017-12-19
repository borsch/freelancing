package borsch.freelancing.services.developers;

import borsch.freelancing.convertors.DeveloperConverter;
import borsch.freelancing.criteria.Criteria;
import borsch.freelancing.criteria.impl.DeveloperCriteria;
import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.exceptions.bad_request.WrongRestrictionException;
import borsch.freelancing.pojo.entities.DeveloperEntity;
import borsch.freelancing.pojo.entities.TagEntity;
import borsch.freelancing.pojo.entities.UserEntity;
import borsch.freelancing.pojo.enums.SkillLevelEnum;
import borsch.freelancing.services.tags.ITagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class DeveloperServiceImpl extends IDeveloperService {

    @Autowired
    private DeveloperConverter converter;

    @Autowired
    private ITagsService tagsService;

    @Override
    public Criteria<DeveloperEntity> parse(String restrict) throws WrongRestrictionException {
        return new DeveloperCriteria(restrict);
    }

    @Override
    public List<Map<String, Object>> suggestDevelopers(SkillLevelEnum min, List<String> tags, Set<String> fields) throws BaseException {
        DeveloperCriteria criteria = new DeveloperCriteria();
        criteria.setMin_skill_level(min);

        List<DeveloperEntity> developers = getList(criteria);
        List<Map<String, Object>> result = new ArrayList<>();

        developers.add(idealDeveloper(min, tags));

        for (DeveloperEntity developer : developers) {
            List<String> developerTags = developer.getTags().stream().map(TagEntity::getTag).collect(Collectors.toList());

            int commonTags = commonTags(developerTags, tags);

            if (commonTags < 1) {
                continue;
            }

            float commonQuota = (float)commonTags / tags.size();

            // plus one, because middle - middle = 1
            int skillDiff = developer.getSkillLevel().ordinal() - min.ordinal() + 1;
            float rating = developer.getRating() / MAX_RATING_VALUE;

            Map<String, Object> map = converter.convert(developer, fields);
            map.put("developer_score", evaluateDeveloper(commonQuota, skillDiff, rating));
            map.put("common_tags_percents", (int)(commonQuota * 100));

            result.add(map);
        }

        result.sort(DEVELOPER_MAP_COMPARATOR);

        return result;
    }

    private float evaluateDeveloper(float commonQuota, int skillDiff, float rating) {
        return SKILL_LEVEL_WEIGHT * skillDiff + commonQuota * TAGS_WEIGHT + rating * RATING_WEIGHT;
    }

    private DeveloperEntity idealDeveloper(SkillLevelEnum level, List<String> tags) {
        UserEntity user = new UserEntity();
        user.setName("ideal developer");

        DeveloperEntity developer = new DeveloperEntity();
        developer.setTags(tagsService.getAll(tags));
        developer.setUser(user);
        developer.setSkillLevel(level);
        developer.setId(0);
        developer.setRating(MAX_RATING_VALUE);
        developer.setProjects(new HashSet<>());

        return developer;
    }

    private int commonTags(List<String> developerTags, List<String> projectTags) {
        int common = 0;

        for (String developerTag : developerTags) {
            if (projectTags.contains(developerTag)) {
                ++common;
            }
        }

        return common;
    }

    private static final Comparator<Map<String, Object>> DEVELOPER_MAP_COMPARATOR = new Comparator<Map<String, Object>>() {
        public int compare(Map<String, Object> m1, Map<String, Object> m2) {
            Float f1 = (Float)m1.get("developer_score");
            Float f2 = (Float)m2.get("developer_score");

            return f2.compareTo(f1);
        }
    };

    private static final float SKILL_LEVEL_WEIGHT = 0.15f;
    private static final float TAGS_WEIGHT = 0.8f;
    private static final float RATING_WEIGHT = 0.05f;

    private static final float MAX_RATING_VALUE = 5f;
}
