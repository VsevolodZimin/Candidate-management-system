package sevices;
import entity.Entity;
import java.util.ArrayList;
import java.util.List;


public interface JDBCService<E extends Entity> {
    
     ArrayList<E> findAll();
     E findById(Integer id);
     void create(E e);
     boolean delete(E e);
     boolean update(E e);
    default  ArrayList<E> findCurrent(List<E> e){
        return null;
    }
    default  ArrayList<E> findImportant(List<E> e) {
        return null;
    }
    default  ArrayList<E> findHistorical(List<E> e){
        return null;
    }
    default  ArrayList<E> findOverdue(List<E> e) {
        return null;
    }
}