package sevices;

import entity.CandidateEntity;
import entity.LabelEntity;
import util.enums.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.enums.Result;

public interface JDBCCandidateService extends JDBCService<CandidateEntity> {
        
    @Override
    ArrayList<CandidateEntity> findAll();
    
    @Override
    CandidateEntity findById(Integer id);
    
    @Override
    void create(CandidateEntity candidate);
    
    @Override
    boolean delete(CandidateEntity candidate);
    
    @Override
    boolean update(CandidateEntity candidate);
    
    @Override
    ArrayList<CandidateEntity> findCurrent(List<CandidateEntity> candidates);
    
    @Override
    ArrayList<CandidateEntity> findImportant(List<CandidateEntity> candidates);
    
    @Override
    ArrayList<CandidateEntity> findHistorical(List<CandidateEntity> candidates);

    @Override
    ArrayList<CandidateEntity> findOverdue(List<CandidateEntity> candidates);
    
    ArrayList<CandidateEntity> findByStage (Stage stage, List<CandidateEntity> candidates);
    
    ArrayList<CandidateEntity> findFromAgency (List<CandidateEntity> candidates);
    
     ArrayList<CandidateEntity> findFitForHire (List<CandidateEntity> candidates);
    
     ArrayList<CandidateEntity> findByResult(Result result, List<CandidateEntity> candidates);
    
     void updateLabels (CandidateEntity c, ArrayList<LabelEntity> updatedLabels) throws IOException, SQLException;
}