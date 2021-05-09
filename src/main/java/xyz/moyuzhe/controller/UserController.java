package xyz.moyuzhe.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.moyuzhe.entity.User;
import xyz.moyuzhe.service.UserService;
import xyz.moyuzhe.utils.BaiduUtils;
import xyz.moyuzhe.utils.JwtUtils;
import xyz.moyuzhe.utils.PassToken;
import xyz.moyuzhe.utils.ResultUtil;
import xyz.moyuzhe.vo.AccountVO;
import xyz.moyuzhe.vo.Result;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BaiduUtils baiduUtils;

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public User get(String id) {
        return userService.getById(id);
    }

    @PassToken
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result<Object> login(@RequestBody User user) {
//        System.out.println("进入登录接口");
//        System.out.println(user.toString());
        user = userService.findUser(user.getUsername(), user.getUserpw());
        if (user == null ){
            return new ResultUtil<Object>().setErrorMsg(100,"用户不存在或密码错误");
        }
        //如果成功了，聚合需要返回的信息
        AccountVO account = new AccountVO();

        //给分配一个token 然后返回
        String jwtToken = JwtUtils.createToken(user.getId(),user.getUsername());

        //我的处理方式是把token放到accountVO里去了
        account.setToken(jwtToken);
        user.setUserpw("");
        account.setUser(user);
        return new ResultUtil<Object>().setData(account);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result<Object> register(@RequestBody User user) {
        JSONObject result = baiduUtils.checkText(user.getUsername());
        String conclusion = result.getString("conclusion");
        if ("不合规".equals(conclusion)){
            return new ResultUtil<Object>().setErrorMsg("用户名疑似或存在违规！请不要传播非法内容");
        }

        int i = userService.checkUser(user.getUsername());
        if (i > 0){
            return new ResultUtil<Object>().setErrorMsg("用户已存在");
        }
        user.setId(String.valueOf(UUID.randomUUID()));
        userService.save(user);
        return new ResultUtil<Object>().setData("注册成功");
    }


    @RequestMapping(value = "/changeHeader", method = RequestMethod.POST)
    public void changeHeader(@RequestBody User user){
        userService.changeHeader(user.getId(),user.getHeaderimg());
    }

    @RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
    public Result<String> updateInfo(@RequestBody User user){
        User user2 = userService.getById(user.getId());
        user2.setEmail(user.getEmail());
        user2.setPhone(user.getPhone());
        userService.updateById(user2);
        return new ResultUtil<String>().setData("修改成功");
    }

    @PassToken
    @RequestMapping(value = "/admin/login",method = RequestMethod.POST)
    public Result<Object> adminLogin(@RequestBody User user) {
//        System.out.println("进入登录接口");
//        System.out.println(user.toString());
        user = userService.findUser(user.getUsername(), user.getUserpw());
        if (user == null ){
            return new ResultUtil<Object>().setErrorMsg(401,"用户不存在或密码错误");
        }
        if (user.getUsertype() == 1){
            return new ResultUtil<Object>().setErrorMsg(401,"该用户权限不足，无法登陆");
        }
        //如果成功了，聚合需要返回的信息
        AccountVO account = new AccountVO();

        //给分配一个token 然后返回
        String jwtToken = JwtUtils.createToken(user.getId(),user.getUsername());

        //我的处理方式是把token放到accountVO里去了
        account.setToken(jwtToken);
        user.setUserpw("");
        account.setUser(user);
        return new ResultUtil<Object>().setData(account);
    }

    @RequestMapping(value = "/admin/list", method = RequestMethod.GET)
    public Result<Object> getAllUser(){
        List<User> users = userService.list();
        return new ResultUtil<Object>().setData(users);
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public Result<Object> updateUser(@RequestBody User user){
        System.out.println(user);
        boolean flag = userService.updateById(user);
        if (flag){
            List<User> users = userService.list();
            return new ResultUtil<Object>().setData(users);
        }
        return new ResultUtil<Object>().setErrorMsg("修改失败");
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public Result<Object> deleteUser(@RequestBody Map<String,String> map){
        boolean flag = userService.removeById(map.get("id"));
        if (flag){
            List<User> users = userService.list();
            return new ResultUtil<Object>().setData(users);
        }
        return new ResultUtil<Object>().setErrorMsg("删除失败");
    }

}
