package borsch.freelancing.pojo.view;

import borsch.freelancing.pojo.enums.RolesEnum;
import borsch.freelancing.pojo.enums.SkillLevelEnum;
import borsch.freelancing.pojo.helpers.GetableById;

import java.util.List;

/**
 * Created by oleh_kurpiak on 07.09.2016.
 */
public class UserView implements GetableById<Integer> {

    private int id;
    private String email;
    private String name;
    private RolesEnum role = RolesEnum.user;

    // developer fields
    private SkillLevelEnum skill_level;
    private List<String> tags;

    public DeveloperView createDeveloper() {
        DeveloperView developer = new DeveloperView();
        developer.setSkill_level(skill_level);
        developer.setTags(tags);

        return developer;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareId(int i) {
        return getId().compareTo(i);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RolesEnum getRole() {
        return role;
    }

    public void setRole(RolesEnum role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SkillLevelEnum getSkill_level() {
        return skill_level;
    }

    public void setSkill_level(SkillLevelEnum skill_level) {
        this.skill_level = skill_level;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserView userView = (UserView) o;

        if (id != userView.id) return false;
        if (email != null ? !email.equals(userView.email) : userView.email != null) return false;
        return role == userView.role;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserView{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
