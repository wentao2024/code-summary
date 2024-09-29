package com.umass.cs520.service;

import com.umass.cs520.domain.Users;
import com.umass.cs520.mapper.UsersMapper;
import java.util.List;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UsersService {

  @Autowired
  private UsersMapper usersMapper;


  public int deleteByPrimaryKey(Integer userid) {
    return usersMapper.deleteByPrimaryKey(userid);
  }


  public List<Users> selectAllUsers() {
    return usersMapper.selectAllUsers();
  }

  public Users selectByUsernamePassword(String username, String password) {
    return usersMapper.selectByUsernamePassword(username, password);
  }


  public int insert(Users record) {
    return usersMapper.insert(record);
  }


  public int insertSelective(Users record) {
    return usersMapper.insertSelective(record);
  }


  public Users selectByPrimaryKey(Integer userid) {
    return usersMapper.selectByPrimaryKey(userid);
  }


  public int updateByPrimaryKeySelective(Users record) {
    return usersMapper.updateByPrimaryKeySelective(record);
  }


  public int updateByPrimaryKey(Users record) {
    return usersMapper.updateByPrimaryKey(record);
  }

}
