package com.umass.cs520.mapper;

import com.umass.cs520.domain.CodeSummarization;
import com.umass.cs520.domain.CodeSummarizations;
import java.util.List;

public interface CodeSummarizationsMapper {
    int deleteByPrimaryKey(Integer summarizationid);

    int insert(CodeSummarizations record);

    int insertSelective(CodeSummarizations record);

    CodeSummarizations selectByPrimaryKey(Integer summarizationid);

    List<CodeSummarization> getCodeSummarizationsWithEvaluationByUserIDs(List<Integer> userIDs);

    int updateByPrimaryKeySelective(CodeSummarizations record);

    int updateByPrimaryKey(CodeSummarizations record);
}