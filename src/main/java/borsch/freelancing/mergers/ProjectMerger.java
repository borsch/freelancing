package borsch.freelancing.mergers;

import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.persistence.dao.repositories.ClientRepository;
import borsch.freelancing.persistence.dao.repositories.DeveloperRepository;
import borsch.freelancing.pojo.entities.ProjectEntity;
import borsch.freelancing.pojo.entities.TagEntity;
import borsch.freelancing.pojo.view.ProjectView;
import borsch.freelancing.services.tags.ITagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Created by olehkurpiak on 18.12.2017.
 */
@Service
public class ProjectMerger implements Merger<ProjectEntity, ProjectView> {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private ITagsService tagsService;

    @Override
    public void merge(ProjectEntity entity, ProjectView view) throws BaseException {
        if (view.getClient_id() != null) entity.setClient(clientRepository.findOne(view.getClient_id()));
        else if (entity.getClient() != null) view.setClient_id(entity.getClient().getId());

        if (view.getClient_rating() != null) entity.setClientRating(view.getClient_rating());
        else view.setClient_rating(entity.getClientRating());

        if (view.getDeveloper_id() != null) entity.setDeveloper(developerRepository.findOne(view.getDeveloper_id()));
        else if (entity.getDeveloper() != null) view.setDeveloper_id(entity.getDeveloper().getId());

        if (view.getDeveloper_rating() != null) entity.setDeveloperRating(view.getDeveloper_rating());
        else view.setDeveloper_rating(entity.getDeveloperRating());

        if (view.getMin_skill_level() != null) entity.setMinSkillLevel(view.getMin_skill_level());
        else view.setMin_skill_level(entity.getMinSkillLevel());

        if (view.getName() != null) entity.setName(view.getName());
        else view.setName(entity.getName());

        if (view.getStatus() != null) entity.setStatus(view.getStatus());
        else view.setStatus(entity.getStatus());

        if (view.getTags() != null) entity.setTags(tagsService.getAll(view.getTags()));
        else if (entity.getTags() != null) view.setTags(entity.getTags().stream().map(TagEntity::getTag).collect(Collectors.toList()));
    }
}
