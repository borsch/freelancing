package borsch.freelancing.pojo.view;

import borsch.freelancing.pojo.enums.SkillLevelEnum;
import borsch.freelancing.pojo.helpers.GetableById;

import java.util.List;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
public class DeveloperView implements GetableById<Integer> {

    private int id;
    private SkillLevelEnum skill_level;
    private Integer user_id;
    private List<String> tags;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareId(int i) {
        return ((Integer) id).compareTo(i);
    }

    public SkillLevelEnum getSkill_level() {
        return skill_level;
    }

    public void setSkill_level(SkillLevelEnum skill_level) {
        this.skill_level = skill_level;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
