package sevices.serviceImpl;

import controller.Context;
import sevices.JDBCPositionService;
import entity.CandidateEntity;
import entity.PositionEntity;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import repository.SQLite.SQLitePositionDAOImpl;
import util.Utils;
import view.components.frames.CustomErrorDialogue;

public class SQLitePositionServiceImpl implements JDBCPositionService{
    
    public SQLitePositionServiceImpl (SQLitePositionDAOImpl positionDao) {
        this.positionDao = positionDao;
    }

    SQLitePositionDAOImpl positionDao;

@Override
    public ArrayList<PositionEntity> findAll() {
        ArrayList<PositionEntity> positions = null;
        try {
            positions = positionDao.find();
        }
        catch (SQLException e) {
            new CustomErrorDialogue(true, "Failed to retrieve entities from the database", Context.getParentFrame(), true).showDialogue();
        }
        return positions;
    }

   @Override
    public PositionEntity findById(Integer id) {
       PositionEntity position = null;
       try {
           position = positionDao.findById(id);
           position.setCandidates(findCandidatesByPosition(position.getId()));

       }
       catch (SQLException e) {
           new CustomErrorDialogue(true, "Failed to retrieve the entity from the database", Context.getParentFrame(), true).showDialogue();
       }
       return position;
    }
    
    @Override
    public ArrayList<PositionEntity> findCurrent (List<PositionEntity> positions) {
        ArrayList<PositionEntity> result = new ArrayList<>();
        for (PositionEntity position : positions) {
            if (position.getForStatistics() == PositionEntity.FALSE) {
                result.add(position);
            }
        }
        return result;
    }

@Override
    public ArrayList<PositionEntity> findHistorical(List<PositionEntity> positions) {
        ArrayList<PositionEntity> result = new ArrayList<>();
        for (PositionEntity position : positions) {
            if (position.getForStatistics() == PositionEntity.TRUE) {
                result.add(position);
            }
        }
        return result;
    }
    
@Override
    public ArrayList<PositionEntity> findImportant(List<PositionEntity> positions) {
        ArrayList<PositionEntity> result = new ArrayList<>();
        for(PositionEntity position: positions){
            if(position.getImportant() == PositionEntity.TRUE){
                result.add(position);
            }
        }
        return result;
    }
    
    
    public ArrayList<CandidateEntity> findCandidatesByPosition (Integer id) {
        try {
            return positionDao.findCandidatesByPosition(id);
        }
        catch (SQLException e) {
            new CustomErrorDialogue(true, "Failed to retrieve entities from the database", Context.getParentFrame(), true).showDialogue();
        }
        return null;
    }



    @Override
    public ArrayList<PositionEntity> findOverdue(List<PositionEntity> positions) {
        ArrayList<PositionEntity> overdue = new ArrayList<>();
        int total;
        for (PositionEntity position: positions) {
            total = Utils.calculateDays(position.getOpenDate(), LocalDate.now());
            if (total >= 40) {
                overdue.add(position);
            }
        }
        return overdue;
    }
 
    
    @Override
    public void create(PositionEntity p) {
        int id = 0;
        try {
            id = positionDao.save(p);
        }
        catch (SQLException e) {
            new CustomErrorDialogue(true, "Failed to create entity", Context.getParentFrame(), true).showDialogue();
        }
        p.setId(id);
    }

    @Override
    public boolean delete(PositionEntity p)  {
        try {
            positionDao.delete(p.getId());
            for (CandidateEntity candidate : p.getCandidates()) {
                positionDao.detachCandidateFromPosition(p, candidate);
            }
        }
        catch (SQLException e) {
            new CustomErrorDialogue(true, "Failed to delete entity", Context.getParentFrame(), true).showDialogue();
        }
        return true;
    }

    @Override
    public boolean update(PositionEntity p) {
        try {
            positionDao.update(p);

        }
        catch (SQLException e) {
            new CustomErrorDialogue(true, "Failed to update entity", Context.getParentFrame(), true).showDialogue();
        }
        return true;
    }

    @Override
    public void detachPositionFromCandidate (CandidateEntity candidate) {
        try {
            positionDao.detachPositionFromCandidate(candidate);

        }
        catch (SQLException e) {
            new CustomErrorDialogue(true, "Failed to detach a position from the candidate", Context.getParentFrame(), true).showDialogue();
        }
    }


    public void attachPositionToCandidate(PositionEntity position, CandidateEntity candidate) {
        try {
            positionDao.attachPositionToCandidate(position, candidate);
        }
        catch (SQLException e) {
            new CustomErrorDialogue(true, "Failed to attach the position to the candidate", Context.getParentFrame(), true).showDialogue();
        }
    }
}