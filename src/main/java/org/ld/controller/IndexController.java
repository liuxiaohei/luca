package org.ld.controller;
import org.ld.mapper.UserDao;
import org.ld.pojo.User;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alen.c on 2018/4/23.
 */
@RestController //返回对象则自动解析为json字符串 因为spring boot 默认使用json解析框架自动返回，返回头是Content-Type:application/json;charset=UTF-8
public class IndexController {

    @Autowired
    private UserDao userinfoMapper;

    @PostMapping(value="login")
    public Object login(String username, String password){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",300);
        if(StringUtils.isEmptyOrWhitespaceOnly(username)||StringUtils.isEmptyOrWhitespaceOnly(password)){
            map.put("msg","用户名和密码不能为空！");
            return map;
        }
        User obj =userinfoMapper.login(username,password);
        if(obj==null){
            map.put("msg","查无此用户！");
            return map;
        }
        map.put("code",200);
        map.put("msg","登录成功！");
        return map;
    }


    @PostMapping(value="register")
    public Object register(String username, String password){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",300);
        if(StringUtils.isEmptyOrWhitespaceOnly(username)||StringUtils.isEmptyOrWhitespaceOnly(password)){
            map.put("msg","用户名和密码不能为空！");
            return map;
        }
        User obj =userinfoMapper.findByUsername(username);
        map.put("msg","此用户名已被注册！");
        if(obj==null){
            obj =new User();
            obj.setUsername(username);
            obj.setPassword(password);
            userinfoMapper.insert(obj);
            map.put("code",200);
            map.put("msg","注册成功！");
        }
        return map;
    }

}
