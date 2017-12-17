package borsch.freelancing.pojo.view;

import borsch.freelancing.pojo.helpers.GetableById;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
public class TagView implements GetableById<Integer> {

    private int id;
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
}
