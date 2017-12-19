package borsch.freelancing.controllers.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by olehkurpiak on 19.12.2017.
 */
@Controller
@RequestMapping("/projects")
public class ProjectsController {

    @RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
    public String projects() {
        return "projects/projects";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "projects/create";
    }

}
