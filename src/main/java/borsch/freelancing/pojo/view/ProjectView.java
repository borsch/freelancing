package borsch.freelancing.pojo.view;

import borsch.freelancing.pojo.enums.ProjectStatusEnum;
import borsch.freelancing.pojo.enums.SkillLevelEnum;
import borsch.freelancing.pojo.helpers.GetableById;

import java.util.List;

/**
 * Created by olehkurpiak on 18.12.2017.
 */
public class ProjectView implements GetableById<Integer> {

    private int id;
    private String name;
    private SkillLevelEnum min_skill_level;
    private ProjectStatusEnum status;
    private List<String> tags;
    private Integer developer_id;
    private Float developer_rating;
    private Integer client_id;
    private Float client_rating;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareId(int i) {
        return ((Integer)id).compareTo(i);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SkillLevelEnum getMin_skill_level() {
        return min_skill_level;
    }

    public void setMin_skill_level(SkillLevelEnum min_skill_level) {
        this.min_skill_level = min_skill_level;
    }

    public ProjectStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ProjectStatusEnum status) {
        this.status = status;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getDeveloper_id() {
        return developer_id;
    }

    public void setDeveloper_id(Integer developer_id) {
        this.developer_id = developer_id;
    }

    public Float getDeveloper_rating() {
        return developer_rating;
    }

    public void setDeveloper_rating(Float developer_rating) {
        this.developer_rating = developer_rating;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public Float getClient_rating() {
        return client_rating;
    }

    public void setClient_rating(Float client_rating) {
        this.client_rating = client_rating;
    }
}
