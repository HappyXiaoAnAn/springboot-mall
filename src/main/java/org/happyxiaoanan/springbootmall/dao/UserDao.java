package org.happyxiaoanan.springbootmall.dao;

import org.happyxiaoanan.springbootmall.dto.UserRegisterRequest;
import org.happyxiaoanan.springbootmall.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
