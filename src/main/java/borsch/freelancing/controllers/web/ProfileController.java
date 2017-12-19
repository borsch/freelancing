package borsch.freelancing.controllers.web;

import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.exceptions.service_error.AuthRequiredException;
import borsch.freelancing.services.projects.IProjectService;
import borsch.freelancing.services.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
@Controller
@RequestMapping("/profile")
@PreAuthorize("isAuthenticated()")
public class ProfileController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IProjectService projectService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String profile(Model model) {
        Set<String> fields = new HashSet<>(Arrays.asList("id", "developer_rating", "developer_projects_amount", "client_rating",
                "client_projects_amount", "developer_skill_level", "developer_tags"));
        try {
            Map<String, Object> user = userService.getCurrentUser(fields);
            model.addAttribute("user", user);
        } catch (AuthRequiredException e) {
            return "redirect:/";
        }


        return "profile/profile";
    }

    @RequestMapping(value = "/my_projects", method = RequestMethod.GET)
    public String myOrders(Model model) {
        Set<String> fields = new HashSet<>(Arrays.asList("id", "name", "status", "min_skill_level",
                "developer_rating", "client_rating", "tags", "developer_id", "client_id"));
        try {
            List<Map<String, Object>> myProjects = projectService.getCurrentUserProjects(fields);

            model.addAttribute("projects", myProjects);
        } catch (BaseException e) { }

        return "profile/my_projects";
    }

    @RequestMapping(value = "/i_developing", method = RequestMethod.GET)
    public String iDeveloping(Model model) {
        Set<String> fields = new HashSet<>(Arrays.asList("id", "name", "status", "min_skill_level",
                "developer_rating", "client_rating", "tags", "developer_id", "client_id"));
        try {
            List<Map<String, Object>> myProjects = projectService.getCurrentUserProjectsDeveloping(fields);

            model.addAttribute("projects", myProjects);
        } catch (BaseException e) { }

        return "profile/developing";
    }
}
