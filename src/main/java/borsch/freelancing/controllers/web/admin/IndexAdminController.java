package borsch.freelancing.controllers.web.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Andrii on 26.08.2016.
 */
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasPermission(1,'ROLE_ADMIN,ROLE_MODERATOR')")
public class IndexAdminController {

    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    public String indexPage(Model model){
        return "/admin/index";
    }
}
