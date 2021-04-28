package xyz.moyuzhe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.moyuzhe.entity.Forums;
import xyz.moyuzhe.service.ForumsService;
import xyz.moyuzhe.utils.ResultUtil;
import xyz.moyuzhe.utils.TreeUtils;
import xyz.moyuzhe.vo.Result;
import xyz.moyuzhe.vo.TreeNodeNew;

import java.util.List;
import java.util.Map;
import java.util.UUID;


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

    @RequestMapping(value = "/admin/list",method = RequestMethod.GET)
    public Result<Object> getAllForums(){
        TreeUtils treeUtils = new TreeUtils();
        List<TreeNodeNew> treeData = treeUtils.getTreeData(forumsService.getAllForums());
        return new ResultUtil<Object>().setData(treeData);
    }

    @RequestMapping(value = "/admin/getForumsList",method = RequestMethod.GET)
    public Result<Object> getForumsList(){
        return new ResultUtil<Object>().setData(forumsService.getMenuTree());
    }

    @RequestMapping(value = "/admin/getForums", method = RequestMethod.GET)
    public Result<Object> getForumsList(@RequestParam String fup){
        List<Forums> forumsList = forumsService.getForumsList(fup);

        return new ResultUtil<Object>().setData(forumsList);
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public Result<Object> addForums(@RequestBody Forums forums){
        String id = String.valueOf(UUID.randomUUID());
        forums.setId(id);
        forums.setPath("/forums?forums="+id);
        System.out.println(forums);
        forumsService.save(forums);
        List<Forums> forumsList = forumsService.getForumsList(forums.getFup());
        return new ResultUtil<Object>().setData(forumsList);
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public Result<Object> updateForums(@RequestBody Map<String,String> map){
        Forums forums = forumsService.getById(map.get("id"));

        forums.setName(map.get("name"));

        System.out.println(forums);
        boolean flag = forumsService.updateById(forums);

        if (flag){
            List<Forums> forumsList = forumsService.getForumsList(forums.getFup());
            return new ResultUtil<Object>().setData(forumsList);
        }else {
            return new ResultUtil<Object>().setErrorMsg("修改失败");
        }
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public Result<Object> deleteForums(@RequestBody Map<String,String> map){
        Forums forums = forumsService.getById(map.get("id"));
        boolean flag = forumsService.removeById(map.get("id"));
        if (flag){
            List<Forums> forumsList = forumsService.getForumsList(forums.getFup());
            return new ResultUtil<Object>().setData(forumsList);
        }else {
            return new ResultUtil<Object>().setErrorMsg("删除失败");
        }
    }
}
