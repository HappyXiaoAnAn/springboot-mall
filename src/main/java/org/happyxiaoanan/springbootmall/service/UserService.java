package org.happyxiaoanan.springbootmall.service;

import org.happyxiaoanan.springbootmall.dto.UserLoginRequest;
import org.happyxiaoanan.springbootmall.dto.UserRegisterRequest;
import org.happyxiaoanan.springbootmall.model.User;

public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);
}
