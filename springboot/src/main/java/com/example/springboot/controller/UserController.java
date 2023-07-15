package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.pojo.User;
import com.example.springboot.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController extends BaseController {
    @Autowired
    IUserService service;



    @GetMapping("/user")
    public List<User> user() {

        return service.getAllUsers();
    }

    @PostMapping("/MyInfo")
    public User myInfo(@RequestBody User user) {
        User u = new User();
        u=service.getMyInfo(user);
        System.out.println(u);
        return u;
    }

    @PostMapping("/modify")
    public Result<?> modify(@RequestBody User user) {

        service.modify(user);

        return Result.success();
    }
    @PostMapping("/submitpassword")
    public Result<?> submitpassword(@RequestBody User user) {
        System.err.println(user.getPassword());
        service.submitpassword(user);
        return Result.success();
    }


    @PostMapping("/searchUser")
    public User searchUser(@RequestBody User user) {
        User u =service.searchUser(user);
        return u;
    }
//
//    @PostMapping("/submittruename")
//    public Result<?> submittruename(@RequestBody User user) {
//        int code = service.submittruename(user);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }
//
//    @PostMapping("/submittel")
//    public Result<?> submittel(@RequestBody User user) {
//        int code = service.submittel(user);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }

//    @PostMapping("/submitusername")
//    public Result<?> submitusername(@RequestBody User user) {
//        int code = service.submitusername(user);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }

    @PostMapping("/deleteUser")
    public Result<?> deleteUser(@RequestBody User user) {
        service.deleteUser(user);
        return Result.success();
    }
    @PostMapping("/LoginIn")
    public Result<?> LoginIn(@RequestBody User user, HttpSession session) {
        User u =  service.CountAffirm(user);
        // 将登录结果中的username封装到session中
        session.setAttribute("userid", u.getUserid());
        session.setAttribute("username", u.getUsername());
        return Result.success();
    }


    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        service.Register(user);
        return Result.success();
    }
}
