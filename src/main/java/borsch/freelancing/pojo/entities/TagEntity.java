package borsch.freelancing.pojo.entities;

import borsch.freelancing.pojo.helpers.GetableById;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
@Entity
@Table(name = "TAGS")
public class TagEntity implements Serializable, GetableById<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "TAG")
    private String tag;

    @ManyToMany
    @JoinTable(name = "TAGS_TO_DEVELOPERS", joinColumns = @JoinColumn(name = "TAG_ID"), inverseJoinColumns = @JoinColumn(name = "DEVELOPER_ID"))
    private Set<DeveloperEntity> developers;

    @ManyToMany
    @JoinTable(name = "TAGS_TO_PROJECTS", joinColumns = @JoinColumn(name = "TAG_ID"), inverseJoinColumns = @JoinColumn(name = "PROJECT_ID"))
    private Set<ProjectEntity> projects;

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Set<DeveloperEntity> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DeveloperEntity> developers) {
        this.developers = developers;
    }

    public Set<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectEntity> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagEntity tagEntity = (TagEntity) o;

        if (id != tagEntity.id) return false;
        return tag != null ? tag.equals(tagEntity.tag) : tagEntity.tag == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TagEntity{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                '}';
    }
}
