package borsch.freelancing.services.tags;

import borsch.freelancing.criteria.Criteria;
import borsch.freelancing.criteria.impl.TagCriteria;
import borsch.freelancing.exceptions.bad_request.WrongRestrictionException;
import borsch.freelancing.persistence.dao.repositories.TagsRepository;
import borsch.freelancing.pojo.entities.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TagsServiceImpl extends ITagsService {

    @Autowired
    private TagsRepository tagsRepository;

    @Override
    public Criteria<TagEntity> parse(String restrict) throws WrongRestrictionException {
        return new TagCriteria();
    }

    @Override
    public Set<TagEntity> getAll(List<String> tags) {
        Set<TagEntity> result = new HashSet<>();

        for (String tag : tags) {
            TagEntity entity = tagsRepository.findByTag(tag);

            if (entity == null) {
                entity = new TagEntity();
                entity.setTag(tag);

                entity = tagsRepository.saveAndFlush(entity);
            }

            result.add(entity);
        }

        return result;
    }
}
