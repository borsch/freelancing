package borsch.freelancing.controllers.rest;

import borsch.freelancing.pojo.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.pojo.response.Response;
import borsch.freelancing.pojo.response.ResponseFactory;
import borsch.freelancing.pojo.view.UserView;
import borsch.freelancing.services.users.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * Created by oleh_kurpiak on 07.09.2016.
 */
@Controller
@RequestMapping(value = "/api/users")
public class UserApiController extends BaseApiController<UserEntity, UserView, Integer> {

    @Autowired
    private IUserService userService;

    @Autowired
    private ResponseFactory responseFactory;


    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public
    @ResponseBody Response<Boolean>
    signIn(
            @RequestBody UserView view
    ) throws BaseException {
        return responseFactory.get(userService.signInUser(view));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public
    @ResponseBody Response<Boolean>
    logout(HttpServletRequest request, HttpServletResponse response){
        return responseFactory.get(userService.logoutUser(request, response));
    }

    @RequestMapping(value = "/registration", method = RequestMethod.PUT)
    public
    @ResponseBody Response<Map<String, Object>>
    registerFullUser(
            @RequestBody UserView view
    ) throws BaseException, IllegalAccessException, InstantiationException {
        return responseFactory.get(userService.registration(view));
    }
}
