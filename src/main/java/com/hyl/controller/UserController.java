package com.hyl.controller;

import com.hyl.common.Authority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HeYonglin
 * @date 2019-06-11 17:11
 */
@RestController
public class UserController {

    //http://localhost:8080/login
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public Map login(){
        Map<String,String> map = new HashMap<>();
        map.put("message","login is ok");
        return map;
    }

    //http://localhost:8080/queryAll?userAuthority=hyl
    //http://localhost:8080/queryAll?userAuthority=admin
    @Authority
    @RequestMapping(value = "queryAll",method = RequestMethod.GET)
    public Map queryAll(){
        Map<String,String> map = new HashMap<>();
        map.put("message","query all is ok");
        return map;
    }

}
