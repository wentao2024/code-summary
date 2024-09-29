package com.umass.cs520.mapper;

import com.umass.cs520.domain.FileUploads;

public interface FileUploadsMapper {
    int deleteByPrimaryKey(Integer fileid);

    int insert(FileUploads record);

    int insertSelective(FileUploads record);

    FileUploads selectByPrimaryKey(Integer fileid);

    int updateByPrimaryKeySelective(FileUploads record);

    int updateByPrimaryKey(FileUploads record);
}