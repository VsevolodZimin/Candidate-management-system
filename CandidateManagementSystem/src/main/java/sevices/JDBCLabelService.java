package sevices;

import entity.LabelEntity;
import java.util.ArrayList;


public interface JDBCLabelService extends JDBCService<LabelEntity> {
    
    @Override
    ArrayList<LabelEntity> findAll();
    
    @Override
    LabelEntity findById(Integer id);
    
    @Override
    void create(LabelEntity label);
    
    @Override
    boolean delete(LabelEntity label);
    
    @Override
    boolean update(LabelEntity label);
}