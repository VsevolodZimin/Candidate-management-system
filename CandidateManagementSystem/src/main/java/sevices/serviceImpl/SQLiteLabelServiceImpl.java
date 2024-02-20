package sevices.serviceImpl;

import controller.Context;
import entity.LabelEntity;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import repository.JDBCLabelDAO;
import sevices.JDBCLabelService;
import view.components.frames.CustomErrorDialogue;


public class SQLiteLabelServiceImpl implements JDBCLabelService{

    JDBCLabelDAO dao;

    public SQLiteLabelServiceImpl(JDBCLabelDAO dao) {
        this.dao = dao;
    }
    
    @Override
    public ArrayList<LabelEntity> findAll() {
        try {
            return dao.find();
        }
        catch (SQLException e) {
            new CustomErrorDialogue(true, "Failed to retrieve entries from the database", Context.getParentFrame(), true).showDialogue();
        }
        return null;
    }

    @Override
    public LabelEntity findById(Integer id)  {
        try {
            return dao.findById(id);
        }
        catch (SQLException | IOException e) {
            new CustomErrorDialogue(true, "Failed to retrieve an entry from the database", Context.getParentFrame(), true).showDialogue();
        }
        return null;
    }

    @Override
    public void create(LabelEntity label) {
        try {
            int id = dao.save(label);
            label.setId(id);
        }
        catch (SQLException e) {
            new CustomErrorDialogue(true, "Failed to create the entry", Context.getParentFrame(), true).showDialogue();
        }
    }

    @Override
    public boolean delete(LabelEntity label) {
        try {
            return dao.delete(label.getId());
        }
        catch (SQLException | IOException e) {
            new CustomErrorDialogue(true, "Failed to delete entity", Context.getParentFrame(), true).showDialogue();
        }
        return false;
    }

    @Override
    public boolean update(LabelEntity label) {
        try {
            return dao.update(label);
        }
        catch (SQLException | IOException e) {
            new CustomErrorDialogue(true, "Failed to update entry", Context.getParentFrame(), true).showDialogue();
        }
        return false;
    }
}