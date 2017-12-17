package borsch.freelancing.pojo.helpers;

/**
 * Created by Andrii on 25.07.2017.
 */
public interface GetableById<T> {

    T getId();
    int compareId(int i);
}
