package borsch.freelancing.pojo.entities;


import borsch.freelancing.pojo.enums.RolesEnum;
import borsch.freelancing.pojo.helpers.CompareIntegerId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Andrii on 18.08.2016.
 */
@Entity
@Table(name = "ROLE")
public class RoleEntity  extends CompareIntegerId implements Serializable{

    private static final long serialVersionUID = 1733101596308114392L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME")
    @Enumerated(EnumType.STRING)
    private RolesEnum name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RolesEnum getName() {
        return name;
    }

    public void setName(RolesEnum name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
