package borsch.freelancing.mergers;

import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.persistence.dao.repositories.TagsRepository;
import borsch.freelancing.persistence.dao.repositories.UsersRepository;
import borsch.freelancing.pojo.entities.DeveloperEntity;
import borsch.freelancing.pojo.entities.TagEntity;
import borsch.freelancing.pojo.view.DeveloperView;
import borsch.freelancing.services.tags.ITagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
@Service
public class DeveloperMerger implements Merger<DeveloperEntity, DeveloperView> {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ITagsService tagsService;

    @Override
    public void merge(DeveloperEntity entity, DeveloperView view) throws BaseException {
        if (view.getSkill_level() != null) entity.setSkillLevel(view.getSkill_level());
        else view.setSkill_level(entity.getSkillLevel());

        if (view.getUser_id() != null) entity.setUser(usersRepository.findOne(view.getUser_id()));
        else if (entity.getUser() != null) view.setUser_id(entity.getUser().getId());

        if (view.getTags() != null) entity.setTags(tagsService.getAll(view.getTags()));
        else if (entity.getTags() != null) view.setTags(entity.getTags().stream().map(TagEntity::getTag).collect(Collectors.toList()));
    }

}
