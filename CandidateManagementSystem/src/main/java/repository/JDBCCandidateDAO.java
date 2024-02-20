package repository;

import entity.CandidateEntity;
import entity.LabelEntity;
import entity.PositionEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface JDBCCandidateDAO extends JDBCdao<CandidateEntity> {
    ArrayList<CandidateEntity> find() throws SQLException;
    CandidateEntity findById(Integer id) throws IOException, SQLException;
    int save(CandidateEntity c) throws SQLException;
    boolean delete(Integer id) throws IOException, SQLException;
    boolean update(CandidateEntity c) throws IOException, SQLException;
    ArrayList<LabelEntity> findLabelsByCandidate(Integer id) throws SQLException;
    PositionEntity findPositionByCandidate(Integer id) throws SQLException;
    void attachLabelToCandidate (CandidateEntity candidate, LabelEntity label) throws SQLException;
    boolean detachLabelFromCandidate (CandidateEntity candidate, LabelEntity label) throws SQLException;
}
