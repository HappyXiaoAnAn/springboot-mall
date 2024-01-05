package org.happyxiaoanan.springbootmall.service.impl;

import org.happyxiaoanan.springbootmall.dao.UserDao;
import org.happyxiaoanan.springbootmall.dto.UserLoginRequest;
import org.happyxiaoanan.springbootmall.dto.UserRegisterRequest;
import org.happyxiaoanan.springbootmall.model.User;
import org.happyxiaoanan.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {

        // check email
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if (user != null) {
            log.warn("This email {} has been registered!", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // use MD5 to generate password
        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);

        // create account
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        // check if user exist
        if (user == null) {
            log.warn("This email {} has not been registered!", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // generate hashed password by MD5
        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());

        // check if password is correct
        if (user.getPassword().equals(hashedPassword)) {
            return user;
        } else {
            log.warn("Password not correct!");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
