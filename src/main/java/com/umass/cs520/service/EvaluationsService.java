package com.umass.cs520.service;

import com.umass.cs520.domain.Evaluations;
import com.umass.cs520.mapper.EvaluationsMapper;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EvaluationsService{

    @Autowired
    private EvaluationsMapper evaluationsMapper;

    
    public int deleteByPrimaryKey(Integer evaluationid) {
        return evaluationsMapper.deleteByPrimaryKey(evaluationid);
    }

    
    public int insert(Evaluations record) {
        return evaluationsMapper.insert(record);
    }

    
    public int insertSelective(Evaluations record) {
        return evaluationsMapper.insertSelective(record);
    }

    
    public Evaluations selectByPrimaryKey(Integer evaluationid) {
        return evaluationsMapper.selectByPrimaryKey(evaluationid);
    }

    
    public int updateByPrimaryKeySelective(Evaluations record) {
        return evaluationsMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Evaluations record) {
        return evaluationsMapper.updateByPrimaryKey(record);
    }

}
