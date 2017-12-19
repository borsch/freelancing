package borsch.freelancing.controllers.rest;

import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.pojo.entities.DeveloperEntity;
import borsch.freelancing.pojo.response.Response;
import borsch.freelancing.pojo.view.DeveloperView;
import borsch.freelancing.services.developers.IDeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by olehkurpiak on 18.12.2017.
 */
@Controller
@RequestMapping(value = "/api/developers")
public class DeveloperApiController extends BaseApiController<DeveloperEntity, DeveloperView, Integer> {

    @Autowired
    private IDeveloperService developerService;

    @RequestMapping(value = "/rating_recount", method = RequestMethod.POST)
    public @ResponseBody
    Response<Float> ratingRecount(
            @RequestBody DeveloperView view
    ) throws BaseException {
        return responseFactory.get(developerService.ratingRecount(view));
    }

}
