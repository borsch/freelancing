package borsch.freelancing.pojo.entities;

import borsch.freelancing.pojo.enums.SkillLevelEnum;
import borsch.freelancing.pojo.helpers.GetableById;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
@Entity
@Table(name = "DEVELOPERS")
public class DeveloperEntity implements Serializable, GetableById<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "RATING")
    private float rating;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @Column(name = "SKILL_LEVEL")
    @Enumerated(EnumType.ORDINAL)
    private SkillLevelEnum skillLevel;

    @ManyToMany
    @JoinTable(name = "TAGS_TO_DEVELOPERS", joinColumns = @JoinColumn(name = "DEVELOPER_ID"), inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private Set<TagEntity> tags;

    @OneToMany(mappedBy = "developer")
    private Set<ProjectEntity> projects;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public int compareId(int i) {
        return ((Integer)id).compareTo(i);
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public SkillLevelEnum getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevelEnum skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Set<TagEntity> getTags() {
        return tags;
    }

    public Set<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectEntity> projects) {
        this.projects = projects;
    }

    public void setTags(Set<TagEntity> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeveloperEntity that = (DeveloperEntity) o;

        if (id != that.id) return false;
        if (Float.compare(that.rating, rating) != 0) return false;
        return skillLevel == that.skillLevel;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (rating != +0.0f ? Float.floatToIntBits(rating) : 0);
        result = 31 * result + (skillLevel != null ? skillLevel.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeveloperEntity{" +
                "id=" + id +
                ", rating=" + rating +
                ", skillLevel=" + skillLevel +
                '}';
    }
}
