package com.umass.cs520.service;

import com.umass.cs520.domain.FileUploads;
import com.umass.cs520.mapper.FileUploadsMapper;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class FileUploadsService{

    @Autowired
    private FileUploadsMapper fileUploadsMapper;

    
    public int deleteByPrimaryKey(Integer fileid) {
        return fileUploadsMapper.deleteByPrimaryKey(fileid);
    }

    
    public int insert(FileUploads record) {
        return fileUploadsMapper.insert(record);
    }

    
    public int insertSelective(FileUploads record) {
        return fileUploadsMapper.insertSelective(record);
    }

    
    public FileUploads selectByPrimaryKey(Integer fileid) {
        return fileUploadsMapper.selectByPrimaryKey(fileid);
    }

    
    public int updateByPrimaryKeySelective(FileUploads record) {
        return fileUploadsMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(FileUploads record) {
        return fileUploadsMapper.updateByPrimaryKey(record);
    }

}
