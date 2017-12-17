package borsch.freelancing.pojo.view;

import borsch.freelancing.pojo.helpers.GetableById;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
public class ClientView implements GetableById<Integer> {

    private int id;
    private Integer user_id;

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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
