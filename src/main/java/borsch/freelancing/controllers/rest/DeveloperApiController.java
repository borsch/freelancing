package borsch.freelancing.controllers.rest;

import borsch.freelancing.pojo.entities.DeveloperEntity;
import borsch.freelancing.pojo.view.DeveloperView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by olehkurpiak on 18.12.2017.
 */
@Controller
@RequestMapping(value = "/api/developers")
public class DeveloperApiController extends BaseApiController<DeveloperEntity, DeveloperView, Integer> {
}
