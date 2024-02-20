package sevices;

import entity.CandidateEntity;
import entity.PositionEntity;
import java.util.ArrayList;
import java.util.List;


public interface JDBCPositionService extends JDBCService<PositionEntity> {

    @Override
    ArrayList<PositionEntity> findAll();

    @Override
    PositionEntity findById(Integer id);

    @Override
    void create(PositionEntity position);

    @Override
    boolean update(PositionEntity position);

    @Override
    boolean delete(PositionEntity position);

    @Override
    ArrayList<PositionEntity> findOverdue(List<PositionEntity> positions);

    @Override
    ArrayList<PositionEntity> findImportant(List<PositionEntity> positions);

    @Override
    ArrayList<PositionEntity> findCurrent(List<PositionEntity> positions);

    @Override
    ArrayList<PositionEntity> findHistorical(List<PositionEntity> positions);
    
    ArrayList<CandidateEntity> findCandidatesByPosition (Integer id);
    
    void detachPositionFromCandidate(CandidateEntity candidate);
    
    void attachPositionToCandidate(PositionEntity position, CandidateEntity candidate);
}