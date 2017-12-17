package borsch.freelancing.mergers;

import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.pojo.entities.TagEntity;
import borsch.freelancing.pojo.view.TagView;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
@Service
public class TagMerger implements Merger<TagEntity, TagView> {

    @Override
    public void merge(TagEntity entity, TagView view) throws BaseException {
        if (!StringUtils.isBlank(view.getTag())) entity.setTag(view.getTag());
        else view.setTag(entity.getTag());
    }
}
