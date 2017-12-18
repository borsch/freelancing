package borsch.freelancing.controllers.web;

import borsch.freelancing.services.developers.IDeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by Andrii on 27.07.2016.
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String indexPage(Model model){
        return "index/index";
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping(value = "/sign_in")
    public String signIn() {
        return "auth/sign_in";
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String signUp() {
        return "auth/register";
    }

    @RequestMapping(value = "/forbidden", method = RequestMethod.GET)
    public String forbidden() {
             return "index/forbidden";
    }

    @RequestMapping(value = "/developers", method = RequestMethod.GET)
    public String developers() {

        return "index/developers";
    }

}
