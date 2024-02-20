package repository;

import entity.Entity;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public interface JDBCdao<E extends Entity> {
    ArrayList<E> find() throws SQLException;
    E findById(Integer id) throws IOException, SQLException;
    int save(E e) throws SQLException;
    boolean delete(Integer id) throws IOException, SQLException;
    boolean update(E e) throws IOException, SQLException;
}
