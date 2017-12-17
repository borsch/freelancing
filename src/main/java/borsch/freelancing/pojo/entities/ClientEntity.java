package borsch.freelancing.pojo.entities;

import borsch.freelancing.pojo.helpers.GetableById;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
@Entity
@Table(name = "CLIENTS")
public class ClientEntity implements Serializable, GetableById<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "RATING")
    private float rating;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @OneToMany(mappedBy = "client")
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

        ClientEntity that = (ClientEntity) o;

        if (id != that.id) return false;
        return Float.compare(that.rating, rating) == 0;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (rating != +0.0f ? Float.floatToIntBits(rating) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", rating=" + rating +
                '}';
    }
}
