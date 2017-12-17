package borsch.freelancing.pojo.entities;

import borsch.freelancing.pojo.enums.ProjectStatusEnum;
import borsch.freelancing.pojo.enums.SkillLevelEnum;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
@Entity
@Table(name = "PROJECTS")
public class ProjectEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "STATUS")
    private ProjectStatusEnum status;

    @Column(name = "MIN_SKILL_LEVEL")
    private SkillLevelEnum minSkillLevel;

    @Column(name = "DEVELOPER_RATING")
    private float developerRating;

    @Column(name = "CLIENT_RATING")
    private float clientRating;

    @JoinColumn(name = "DEVELOPER_ID")
    private DeveloperEntity developer;

    @JoinColumn(name = "CLIENT_ID")
    private ClientEntity client;

    @ManyToMany
    @JoinTable(name = "TAGS_TO_PROJECTS", joinColumns = @JoinColumn(name = "PROJECT_ID"), inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private Set<TagEntity> tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ProjectStatusEnum status) {
        this.status = status;
    }

    public SkillLevelEnum getMinSkillLevel() {
        return minSkillLevel;
    }

    public void setMinSkillLevel(SkillLevelEnum minSkillLevel) {
        this.minSkillLevel = minSkillLevel;
    }

    public float getDeveloperRating() {
        return developerRating;
    }

    public void setDeveloperRating(float developerRating) {
        this.developerRating = developerRating;
    }

    public float getClientRating() {
        return clientRating;
    }

    public void setClientRating(float clientRating) {
        this.clientRating = clientRating;
    }

    public DeveloperEntity getDeveloper() {
        return developer;
    }

    public void setDeveloper(DeveloperEntity developer) {
        this.developer = developer;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public Set<TagEntity> getTags() {
        return tags;
    }

    public void setTags(Set<TagEntity> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectEntity that = (ProjectEntity) o;

        if (id != that.id) return false;
        if (Float.compare(that.developerRating, developerRating) != 0) return false;
        if (Float.compare(that.clientRating, clientRating) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (status != that.status) return false;
        return minSkillLevel == that.minSkillLevel;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (minSkillLevel != null ? minSkillLevel.hashCode() : 0);
        result = 31 * result + (developerRating != +0.0f ? Float.floatToIntBits(developerRating) : 0);
        result = 31 * result + (clientRating != +0.0f ? Float.floatToIntBits(clientRating) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", minSkillLevel=" + minSkillLevel +
                ", developerRating=" + developerRating +
                ", clientRating=" + clientRating +
                '}';
    }
}
