package xyz.moyuzhe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.moyuzhe.entity.Forums;
import xyz.moyuzhe.service.ForumsService;
import xyz.moyuzhe.utils.ResultUtil;
import xyz.moyuzhe.vo.Result;


@RestController
@RequestMapping("/api/forums")
public class ForumsController {

    @Autowired
    ForumsService forumsService;

    @RequestMapping(value = "/getForums",method = RequestMethod.GET)
    public Result<Object> getForums(){
        return new ResultUtil<Object>().setData(forumsService.getMenuTree());
    }

    @RequestMapping(value = "/getName",method = RequestMethod.GET)
    public Result<String> getName(String id){
        Forums forums = forumsService.getById(id);
        return new ResultUtil<String>().setData(forums.getName());
    }



}
