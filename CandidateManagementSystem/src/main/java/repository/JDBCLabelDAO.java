package repository;

import entity.LabelEntity;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public interface JDBCLabelDAO extends JDBCdao<LabelEntity>{
    
    ArrayList<LabelEntity> find() throws SQLException;
    LabelEntity findById(Integer id) throws IOException, SQLException;
    int save(LabelEntity le) throws SQLException;
    boolean delete(Integer id) throws IOException, SQLException;
    boolean update(LabelEntity le) throws IOException, SQLException;
}
