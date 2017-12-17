package borsch.freelancing.controllers.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by oleh_kurpiak on 12.09.2016.
 */
@Controller
@RequestMapping(value = "/admin/users")
public class AdminUsersController {

    @RequestMapping(value = {"/", "/all"}, method = RequestMethod.GET)
    public String getAllUsers(){
        return "admin/users/all";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("id", 0);
        return "admin/users/edit";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("id", id);
        return "admin/users/edit";
    }
}
