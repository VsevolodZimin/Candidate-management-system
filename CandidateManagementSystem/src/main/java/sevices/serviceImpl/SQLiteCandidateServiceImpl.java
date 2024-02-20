package sevices.serviceImpl;

import controller.Context;
import entity.CandidateEntity;
import entity.LabelEntity;
import entity.PositionEntity;
import repository.JDBCCandidateDAO;
import sevices.JDBCPositionService;
import util.Utils;
import util.enums.Result;
import util.enums.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import sevices.JDBCCandidateService;
import view.components.frames.CustomErrorDialogue;

public class SQLiteCandidateServiceImpl implements JDBCCandidateService {
    JDBCCandidateDAO candidateDAO;
    JDBCPositionService positionService;

    public SQLiteCandidateServiceImpl (JDBCCandidateDAO candidateDAO, JDBCPositionService positionService) {
        this.candidateDAO = candidateDAO;
        this.positionService = positionService;
    }

    @Override
    public ArrayList<CandidateEntity> findAll() {
        ArrayList<CandidateEntity> candidates;
        try {
            candidates = candidateDAO.find();
            for(CandidateEntity candidate : candidates){
                candidate.setPosition(findPositionByCandidate(candidate.getId()));
                candidate.setAdditionalBenefits(candidateDAO.findLabelsByCandidate(candidate.getId()));
            }
            return candidates;
        }
        catch (SQLException e) {
            new CustomErrorDialogue(true, "Failed to connect to the database", Context.getParentFrame(), true).showDialogue();
        }
        return null;
    }

    @Override
    public CandidateEntity findById(Integer id) {
        CandidateEntity candidate = null;
        try {
            candidate = candidateDAO.findById(id);
            candidate.setPosition(findPositionByCandidate(id));
            candidate.setAdditionalBenefits(candidateDAO.findLabelsByCandidate(candidate.getId()));
        }
        catch (SQLException e) {
            new CustomErrorDialogue(true, "Failed to connect to the database", Context.getParentFrame(), true).showDialogue();
        }
        catch (IOException e) {
            new CustomErrorDialogue(true, "Failed to load from database", Context.getParentFrame(), true).showDialogue();
        }
        return candidate;
    }
    @Override
    public ArrayList<CandidateEntity> findCurrent(List<CandidateEntity> candidates) {
        ArrayList<CandidateEntity> result = new ArrayList<>();
        for (CandidateEntity candidate : candidates) {
            if (candidate.getForStatistics() == CandidateEntity.FALSE) {
                result.add(candidate);
            }
        }
        return result;
    }

    public PositionEntity findPositionByCandidate(Integer id) {
        try {
            return candidateDAO.findPositionByCandidate(id);
        }
        catch (SQLException e) {
            new CustomErrorDialogue(true, "Failed to connect to the database", Context.getParentFrame(), true).showDialogue();
        }
        return null;
    }

        @Override
    public ArrayList<CandidateEntity> findHistorical(List<CandidateEntity> candidates) {
        ArrayList<CandidateEntity> result = new ArrayList<>();
        for (CandidateEntity candidate : candidates) {
            if (candidate.getForStatistics() == CandidateEntity.TRUE) {
                result.add(candidate);
            }
        }
        return result;
    }

    @Override
    public ArrayList<CandidateEntity> findByStage (Stage stage, List<CandidateEntity> candidates) {
        ArrayList<CandidateEntity> result = new ArrayList<>();

        switch(stage){



            case IN_MAIL  -> {      
                for (CandidateEntity candidate : candidates) {
                    if(candidate.getCurrentStageFlag() == Stage.IN_MAIL) {
                        result.add(candidate);
                    }
                }
            }
           

            case PHONE_SCREENING  -> {      
                for (CandidateEntity candidate : candidates) {
                if(candidate.getCurrentStageFlag() == Stage.PHONE_SCREENING) {
                    result.add(candidate);
                }
            }
            }

            case HR_INTERVIEW    -> {       
                for (CandidateEntity candidate : candidates) {
                if(candidate.getCurrentStageFlag() ==  Stage.HR_INTERVIEW) {
                    result.add(candidate);
                }
            }
                return result;
            }

            case SUBMITTED_TO_HM  -> {      
                for (CandidateEntity candidate : candidates) {
                if(candidate.getCurrentStageFlag() == Stage.SUBMITTED_TO_HM) {
                    result.add(candidate);
                }
            }
                return result;
            }

            case HM_INTERVIEW     -> {      
                for (CandidateEntity candidate : candidates) {
                if(candidate.getCurrentStageFlag() == Stage.HM_INTERVIEW) {
                    result.add(candidate);
                }
            }
                return result;
            }
            case TEST             -> {      
                for (CandidateEntity candidate : candidates) {
                    if (candidate.getCurrentStageFlag() == Stage.TEST) {
                        result.add(candidate);
                    }
                }
                return result;
            }

            case OFFER_LETTER     -> {      
                for (CandidateEntity candidate : candidates) {
                    if (candidate.getCurrentStageFlag() == Stage.OFFER_LETTER) {
                        result.add(candidate);
                    }
                }
            }

            case SELECTION_CLOSED     -> {  
                for (CandidateEntity candidate : candidates) {
                    if (candidate.getCurrentStageFlag() == Stage.SELECTION_CLOSED) {
                        result.add(candidate);
                    }
                }
            }
        }
        return result;
    }

    
    @Override
    public ArrayList<CandidateEntity> findFromAgency (List<CandidateEntity> candidates) {
        ArrayList<CandidateEntity> result = new ArrayList<>();
        for(CandidateEntity candidate : candidates) {
            if(candidate.getFromAgency() == 1) {
                result.add(candidate);
            }
        }
        return result;
    }
    
    @Override
    public ArrayList<CandidateEntity> findFitForHire (List<CandidateEntity> candidates) {
        ArrayList<CandidateEntity> result = new ArrayList<>();
        for(CandidateEntity candidate : candidates) {
            if(candidate.getSuitableForSinclair() == 1) {
                result.add(candidate);
            }
        }
        return result;
    }

    
    @Override
    public ArrayList<CandidateEntity> findImportant(List<CandidateEntity> candidates) {
        ArrayList<CandidateEntity> resultingCandidates = new ArrayList<>();
        for(CandidateEntity candidate: candidates){
            if(candidate.getImportant() == CandidateEntity.TRUE){
                resultingCandidates.add(candidate);
            }
        }
        return resultingCandidates;
    }

    @Override
    public ArrayList<CandidateEntity> findByResult(Result result, List<CandidateEntity> candidates) {
        ArrayList<CandidateEntity> resultingCandidates = new ArrayList<>();

        switch (result) {

            case OFFER_DECLINED -> {
                for (CandidateEntity candidate : candidates) {
                    if (candidate.getResult() == Result.OFFER_DECLINED) {
                        resultingCandidates.add(candidate);
                    }
                }
                return resultingCandidates;
            }

            case HM_DISCARDS -> {
                for (CandidateEntity candidate : candidates) {
                    if (candidate.getResult() == Result.HM_DISCARDS) {
                        resultingCandidates.add(candidate);
                    }
                }
                return resultingCandidates;
            }

            case HR_DISCARDS -> {
                for (CandidateEntity candidate : candidates) {
                    if (candidate.getResult() == Result.HR_DISCARDS) {
                        resultingCandidates.add(candidate);
                    }
                }
                return resultingCandidates;
            }

            case HIRED -> {
                for (CandidateEntity candidate : candidates) {
                    if (candidate.getResult() == Result.HIRED) {
                        resultingCandidates.add(candidate);
                    }
                }
                return resultingCandidates;
            }

        }
        return resultingCandidates;
    }


    @Override
    public ArrayList<CandidateEntity> findOverdue(List<CandidateEntity> candidates) {
        ArrayList<CandidateEntity> overdue = new ArrayList<>();
        int currentInterval;
        int total;

        for (CandidateEntity candidate: candidates) {
            currentInterval = Utils.getCurrentCandidateInterval(candidate);
            total = Utils.calculateDays(candidate.getInMail(), candidate.getCurrentStageDate(candidate.getCurrentStageFlag()));
            if (currentInterval >= 7 || total >= 40) {
                overdue.add(candidate);
            }
        }
        return overdue;
    }


    @Override
    public void create(CandidateEntity c) {
        try {
            int id = candidateDAO.save(c);
            c.setId(id);
            for (LabelEntity le : c.getAdditionalBenefits()) {
                c.setId(id);
                candidateDAO.attachLabelToCandidate(c, le);
            }
            if (c.getPosition() != null) {
                positionService.attachPositionToCandidate(c.getPosition(), c);
            }
        }
        catch (SQLException e) {
            new CustomErrorDialogue(true, "Failed to create entry", Context.getParentFrame(), true).showDialogue();
        }
    }
    

    @Override
    public boolean delete(CandidateEntity candidate) {
        try {
            candidateDAO.delete(candidate.getId());
            for (LabelEntity label : candidate.getAdditionalBenefits()) {
                candidateDAO.detachLabelFromCandidate(candidate, label);
            }
            positionService.detachPositionFromCandidate(candidate);
        }
        catch (SQLException | IOException e) {
            new CustomErrorDialogue(true, "Failed to delete entry", Context.getParentFrame(), true).showDialogue();
        }
        return false;
    }

    @Override
    public boolean update(CandidateEntity c) {
        try {
            candidateDAO.update(c);
            positionService.detachPositionFromCandidate(c);

            if (c.getPosition() != null) {
                positionService.attachPositionToCandidate(c.getPosition(), c);
            }
        }
        catch (SQLException e) {
            new CustomErrorDialogue(true, "Failed to connect to the database", Context.getParentFrame(), true).showDialogue();
        }
        catch (IOException e) {
            new CustomErrorDialogue(true, "Failed to save", Context.getParentFrame(), true).showDialogue();
        }
        return false;
    }
    

//if added to bookmarks, if added to history

    @Override
    public void updateLabels(CandidateEntity c, ArrayList<LabelEntity> updatedLabels) {
        try {
            if (updatedLabels != null) {

                for (LabelEntity le : c.getAdditionalBenefits()) {
                    if (!updatedLabels.contains(le)) {
                        candidateDAO.detachLabelFromCandidate(c, le);
                    }
                }

                for (LabelEntity le : updatedLabels) {
                    if (!c.getAdditionalBenefits().contains(le)) {
                        candidateDAO.attachLabelToCandidate(c, le);
                    }
                }
            }
        }
        catch (SQLException e) {
            new CustomErrorDialogue(true, "Failed to connect to the database", Context.getParentFrame(), true).showDialogue();
        }
    }
    
    public void updateCandidateWithLabels(CandidateEntity c, ArrayList<LabelEntity> updatedLabels) {
        update(c);
        updateLabels(c, updatedLabels);
    }
}