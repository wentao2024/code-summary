package com.umass.cs520.service;

import com.umass.cs520.domain.CodeSummarization;
import com.umass.cs520.domain.CodeSummarizations;
import com.umass.cs520.domain.Evaluations;
import com.umass.cs520.domain.dto.EvaluationsResponse;
import com.umass.cs520.domain.dto.ResultEvaluationRequest;
import com.umass.cs520.mapper.CodeSummarizationsMapper;
import com.umass.cs520.mapper.EvaluationsMapper;
import com.umass.cs520.util.RequestUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeSummarizationsService {

    @Autowired
    private CodeSummarizationsMapper codeSummarizationsMapper;
    @Autowired
    private EvaluationsMapper evaluationsMapper;

    
    public int deleteByPrimaryKey(Integer summarizationid) {
        return codeSummarizationsMapper.deleteByPrimaryKey(summarizationid);
    }

    
    public int insert(CodeSummarizations record) {
        return codeSummarizationsMapper.insert(record);
    }

    
    public int insertSelective(CodeSummarizations record) {
        return codeSummarizationsMapper.insertSelective(record);
    }

    
    public CodeSummarizations selectByPrimaryKey(Integer summarizationid) {
        return codeSummarizationsMapper.selectByPrimaryKey(summarizationid);
    }

    
    public int updateByPrimaryKeySelective(CodeSummarizations record) {
        return codeSummarizationsMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(CodeSummarizations record) {
        return codeSummarizationsMapper.updateByPrimaryKey(record);
    }

    public void resultEvaluation(ResultEvaluationRequest request) {
        String codeText = request.getCodeText();
        String summarization = request.getSummarization();
        String feedback = request.getFeedback();
        int naturalness = request.getNaturalness();
        int usefulness = request.getUsefulness();
        int consistency = request.getConsistency();
        String userId = RequestUtil.getUserId();

        CodeSummarizations codeSummarizations = new CodeSummarizations();
        codeSummarizations.setCodetext(codeText);
        codeSummarizations.setSummarization(summarization);
        codeSummarizations.setUserid(Integer.valueOf(userId));
        int sid = codeSummarizationsMapper.insertSelective(codeSummarizations);

        Evaluations evaluations = new Evaluations();
        evaluations.setFeedback(feedback);
        evaluations.setNaturalnessscore(naturalness);
        evaluations.setUsefulnessscore(usefulness);
        evaluations.setConsistencyscore(consistency);
        evaluations.setSummarizationid(codeSummarizations.getSummarizationid());
        evaluations.setUserid(Integer.valueOf(userId));
        evaluationsMapper.insertSelective(evaluations);
    }

    public EvaluationsResponse getEvaluations(List<Integer> users) {

        List<CodeSummarization> codeSummarizationsWithEvaluationByUserIDs = codeSummarizationsMapper.getCodeSummarizationsWithEvaluationByUserIDs(
            users);

        EvaluationsResponse evaluationsResponse = new EvaluationsResponse();
        evaluationsResponse.setEvaluations(codeSummarizationsWithEvaluationByUserIDs);

        return evaluationsResponse;
    }
}
