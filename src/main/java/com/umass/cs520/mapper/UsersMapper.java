package com.umass.cs520.mapper;

import com.umass.cs520.domain.Users;
import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer userid);
    List<Users> selectAllUsers();

    Users selectByUsernamePassword(String username, String password);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}