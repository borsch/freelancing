package borsch.freelancing.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Andrii on 27.07.2016.
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String indexPage(
            Model model
    ){
        return "index/index";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String signUp() {
        return "auth/register";
    }

    @RequestMapping(value = "/forbidden", method = RequestMethod.GET)
    public String forbidden() {
             return "index/forbidden";
    }
}
