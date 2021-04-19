package xyz.moyuzhe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.moyuzhe.entity.User;
import xyz.moyuzhe.service.UserService;
import xyz.moyuzhe.utils.JwtUtils;
import xyz.moyuzhe.utils.PassToken;
import xyz.moyuzhe.vo.AccountVO;

import java.util.UUID;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public User get(@RequestParam String id) {
        return userService.getById(id);
    }

    @PassToken
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public AccountVO login(@RequestBody User user) {
        System.out.println("进入登录接口");
        System.out.println(user.toString());
//        user = userService.getById(user.getUsername());
        //如果成功了，聚合需要返回的信息
        AccountVO account = new AccountVO();

        //给分配一个token 然后返回
        String jwtToken = JwtUtils.createToken("1",user.getUsername(),"1");

        //我的处理方式是把token放到accountVO里去了
        account.setUsername(user.getUsername());
        account.setToken(jwtToken);

        return account;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public void register(@RequestBody User user) {
        user.setId(String.valueOf(UUID.randomUUID()));
        userService.save(user);
    }

}
