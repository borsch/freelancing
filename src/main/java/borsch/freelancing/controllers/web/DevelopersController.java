package borsch.freelancing.controllers.web;

import borsch.freelancing.criteria.impl.DeveloperCriteria;
import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.pojo.entities.ClientEntity;
import borsch.freelancing.pojo.entities.ProjectEntity;
import borsch.freelancing.pojo.entities.UserEntity;
import borsch.freelancing.pojo.enums.SkillLevelEnum;
import borsch.freelancing.services.developers.IDeveloperService;
import borsch.freelancing.services.projects.IProjectService;
import borsch.freelancing.services.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by olehkurpiak on 19.12.2017.
 */
@Controller
@RequestMapping("/developers")
public class DevelopersController {

    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IDeveloperService developerService;

    @RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
    public String developers() {
        return "developers/developers";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String findDevelopers(
            @RequestParam("project_id") int projectId,
            Model model
    ) {
        UserEntity user = sessionUtils.getCurrentUser();
        ClientEntity client = user.getClient();

        try {
            Map<String, Object> project = projectService.getById(
                    projectId,
                    new HashSet<>(Arrays.asList("name", "client_id", "developer_id", "min_skill_level", "tags"))
            );

            if (project.get("developer_id") != null || !client.getId().equals(project.get("client_id"))) {
                return "redirect:/";
            }

            model.addAttribute("project", project);

            SkillLevelEnum skillLevelEnum = (SkillLevelEnum)project.get("min_skill_level");
            List<String> tags = (List<String>)project.get("tags");

            try {
                List<Map<String, Object>> developers = developerService.suggestDevelopers(
                        skillLevelEnum, tags,
                        new HashSet<>(Arrays.asList("id", "user_name", "rating", "skill_level", "tags", "projects_amount"))
                );

                model.addAttribute("developers", developers);
            } catch (BaseException e) { }

            return "developers/find";
        } catch (BaseException e) {
            return "redirect:/profile";
        }
    }
}
