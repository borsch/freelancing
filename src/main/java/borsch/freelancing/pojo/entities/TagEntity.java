package borsch.freelancing.pojo.entities;

import borsch.freelancing.pojo.helpers.GetableById;

import javax.persistence.*;
import java.io.Serializable;

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
