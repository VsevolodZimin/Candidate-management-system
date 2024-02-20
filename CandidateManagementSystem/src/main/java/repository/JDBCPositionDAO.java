package repository;

import entity.CandidateEntity;
import entity.PositionEntity;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public interface JDBCPositionDAO extends JDBCdao<PositionEntity> {

    @Override
    ArrayList<PositionEntity> find() throws SQLException;
    @Override
    PositionEntity findById(Integer id) throws IOException, SQLException;
    @Override
    int save(PositionEntity position) throws SQLException;
    @Override
    boolean delete(Integer id) throws IOException, SQLException;
    @Override
    boolean update(PositionEntity position) throws IOException, SQLException;
    void attachPositionToCandidate(PositionEntity position, CandidateEntity candidate) throws SQLException;
    boolean detachCandidateFromPosition (PositionEntity position, CandidateEntity candidate) throws SQLException;
}
