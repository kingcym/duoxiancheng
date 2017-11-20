package com.example.demo.security.aa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by King on 2017/9/7.
 */
@RestController
public class UserController {

    // 路由映射到/users
    @RequestMapping(value = "/users", produces="application/json;charset=UTF-8")
    public List<String> usersList() {

        ArrayList<String> users =  new ArrayList<String>(){{
            add("freewolf");
            add("tom");
            add("jerry");
        }};

        return users;
    }

    @RequestMapping(value = "/hello", produces="application/json;charset=UTF-8")
    public List<String> hello() {
        ArrayList<String> users =  new ArrayList<String>(){{ add("hello"); }};
        return users;
    }

    @RequestMapping(value = "/world", produces="application/json;charset=UTF-8")
    public List<String> world() {
        ArrayList<String> users =  new ArrayList<String>(){{ add("world"); }};
        return users ;
    }
}