package com.example.springboot.service.impl;

import com.example.springboot.mapper.UserMapper;
import com.example.springboot.pojo.User;
import com.example.springboot.service.IUserService;
import com.example.springboot.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;


    public List<User> getAllUsers() throws SelectException {
        List<User> list = new ArrayList<>();
        list = userMapper.find();
//        if (list == null) {
//            throw new SelectException("查询错误");
//        }
        //可以为空
        return list;
    }

    public void modify(User user) throws UpdateException {
        int i = userMapper.modify(user);
        if (i != 1) {
            throw new UpdateException("更新失败");
        }
    }
    public void submitpassword(User user) throws UpdateException {
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        user.setPassword(getMd5Password(user.getPassword(),salt));
        int i = userMapper.submitpassword(user);
        if (i != 1) {
            throw new UpdateException("更新失败");
        }
    }


    public User CountAffirm(User user) throws UserNotFoundException,PassWordNotMatchException {

        User u=userMapper.findByName(user.getUsername());
        if (u==null){
            throw new UserNotFoundException("登录失败，用户名不存在！");
        }
        String salt = u.getSalt();
        System.err.println(salt);
        user.setPassword(getMd5Password(user.getPassword(),salt));
        System.err.println(u.getPassword());
        System.err.println(user.getPassword());
        if(!u.getPassword().equals(user.getPassword())){
            throw new PassWordNotMatchException("登录失败，密码不正确！");
        }

        return u;
    }

    public User getMyInfo(User user) throws UserNotFoundException {
        if (user == null) {
            throw new UserNotFoundException("错误传输");
        }
        User result = userMapper.findByName(user.getUsername());
        if (result == null) {
            throw new UserNotFoundException("错误，用户名不存在");
        }
        return result;
    }

    public void deleteUser(User user) throws UserNotFoundException,DeleteException {
        if (user == null) {
            throw new UserNotFoundException("错误传输");
        }
        int result =  userMapper.deleteUser(user.getUserid());
        if (result != 1) {
            throw new DeleteException("删除失败");
        }
    }

    public User searchUser(User user) throws UserNotFoundException {
        if (user == null) {
            throw new UserNotFoundException("错误传输");
        }
        User result =  userMapper.findById(user.getUserid());
        if (result == null) {
            throw new UserNotFoundException("用户不存在");
        }
        return result;
    }

//    public int submitusername(User user) {return userMapper.updateUserName(user.getUsername(),user.getUserid());
//    }
//
//    public int submitpassword(User user) {return userMapper.updateUserPassword(user.getPassword(),user.getUserid());
//    }
//
//    public int submittruename(User user) {return userMapper.updateTrueName(user.getTruename(),user.getUserid());
//    }
//
//    public int submittel(User user)      { return userMapper.updateUserTel(user.getTel(),user.getUserid());
//    }

    public void Register(User user) throws UsernameDuplicateException, InsertException {
        User result = userMapper.findByName(user.getUsername());
        if (result == null) {
            String salt = UUID.randomUUID().toString().toUpperCase();
            user.setSalt(salt);
            user.setPassword(getMd5Password(user.getPassword(),salt));
            Integer row= userMapper.insert(user.getUsername(),user.getPassword(), user.getTruename(),user.getTel(),user.getSalt());
            if (row != 1) {
                throw new InsertException(
                        "注册时发生未知错误，请联系系统管理员！");
            }
        } else {
            // 否：不允许注册，抛出异常
            throw new UsernameDuplicateException(
                    "尝试注册的用户名(" + user.getUsername() + ")已经被占用！");
        }
    }
    private String getMd5Password(String password,String SALT) {
        // 拼接原密码与盐值
        String str = SALT + password + SALT;
        // 循环加密5次
        for (int i = 0; i < 5; i++) {
            str = DigestUtils.md5DigestAsHex(str.getBytes());
        }
        // 返回结果
        return str;
    }


}
