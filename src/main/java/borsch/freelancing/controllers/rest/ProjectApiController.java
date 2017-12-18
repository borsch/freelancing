package borsch.freelancing.controllers.rest;

import borsch.freelancing.pojo.entities.ProjectEntity;
import borsch.freelancing.pojo.view.ProjectView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by olehkurpiak on 18.12.2017.
 */
@Controller
@RequestMapping(value = "/api/projects")
public class ProjectApiController extends BaseApiController<ProjectEntity, ProjectView, Integer> {
}
