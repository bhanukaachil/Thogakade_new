package Repository;

import java.util.List;

public interface CrudRepository<T,ID> extends SuperRepository{
    boolean create(T t);
    boolean update(T t);
    boolean delete(ID id);
    T getbyId(ID id);
    List<T> getAll();
}
