package borsch.freelancing.controllers.web;

import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.services.projects.IProjectService;
import borsch.freelancing.services.users.IUserService;
import borsch.freelancing.services.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by olehkurpiak on 19.12.2017.
 */
@Controller
@RequestMapping("/projects")
public class ProjectsController {

    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
    public String projects() {
        return "projects/projects";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "projects/create";
    }

    @RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
    public String view(
            @PathVariable("id") int id,
            Model model
    ) {
        try {
            Map<String, Object> project = projectService.getById(
                    id, new HashSet<>(Arrays.asList(
                        "id", "name", "min_skill_level", "tags", "developer_rating", "client_rating",
                            "status", "developer_id", "client_id"
                    ))
            );

            model.addAttribute("project", project);

            if (sessionUtils.isAuthorized()) {
                Map<String, Object> user = userService.getCurrentUser(new HashSet<>(Arrays.asList("client_id", "developer_id")));

                Integer userClient = (Integer)user.get("client_id");
                Integer userDeveloper = (Integer)user.get("developer_id");

                if (userClient.equals(project.get("client_id"))) {
                    model.addAttribute("client_same", true);
                }

                if (userDeveloper.equals(project.get("developer_id"))) {
                    model.addAttribute("developer_same", true);
                }
            }
        } catch (BaseException e) {
            return "redirect:/profile/my_projects";
        }


        return "projects/view";
    }
}
