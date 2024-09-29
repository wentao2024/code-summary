package com.umass.cs520.mapper;

import com.umass.cs520.domain.Evaluations;

public interface EvaluationsMapper {
    int deleteByPrimaryKey(Integer evaluationid);

    int insert(Evaluations record);

    int insertSelective(Evaluations record);

    Evaluations selectByPrimaryKey(Integer evaluationid);

    int updateByPrimaryKeySelective(Evaluations record);

    int updateByPrimaryKey(Evaluations record);
}