package xyz.moyuzhe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.moyuzhe.entity.Forums;
import xyz.moyuzhe.entity.Threads;
import xyz.moyuzhe.entity.Topic;
import xyz.moyuzhe.entity.User;
import xyz.moyuzhe.service.ThreadsService;
import xyz.moyuzhe.service.TopicService;
import xyz.moyuzhe.service.UserService;
import xyz.moyuzhe.utils.ResultUtil;
import xyz.moyuzhe.vo.Result;
import xyz.moyuzhe.vo.ThreadsVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/threads")
public class ThreadsController {

    @Autowired
    ThreadsService threadsService;

    @Autowired
    UserService userService;

    @Autowired
    TopicService topicService;

    @RequestMapping(value = "/getThread", method = RequestMethod.GET)
    public Result<ThreadsVO> getThread(String id) {
        Threads threads = threadsService.getById(id);
        User user = userService.getById(threads.getAuthorid());
        user.setUserpw("");
        return new ResultUtil<ThreadsVO>().setData(new ThreadsVO(threads, user));
    }

    @RequestMapping(value = "/getComment", method = RequestMethod.GET)
    public Result<Object> getComment(String pid) {
        List<Threads> comment = threadsService.getComment(pid);
        List<ThreadsVO> threadsVOS = new ArrayList<>();
        for (Threads threads : comment) {
            User user = userService.getById(threads.getAuthorid());
            user.setUserpw("");
            threadsVOS.add(new ThreadsVO(threads, user));
        }
        return new ResultUtil<Object>().setData(threadsVOS);
    }

    @RequestMapping(value = "/submitComment",method = RequestMethod.POST)
    public Result<Object> submitComment(@RequestBody Threads threads){
//        System.out.println(threads);
        if (threads.getContent().isEmpty()){
            return new ResultUtil<Object>().setErrorMsg(100,"不要发送空留言");
        }
        threads.setId(String.valueOf(UUID.randomUUID()));
        threads.setFtype(1);
        threads.setAddtime(new Date());
        threadsService.save(threads);
        return new ResultUtil<Object>().setData("留言成功");
    }

    @RequestMapping(value = "/submitThreads", method = RequestMethod.POST)
    public Result<Object> submitForums(@RequestBody Threads threads){

        if (threads.getFid() == null){
            return new ResultUtil<Object>().setErrorMsg("请选择分类");
        }
        if (threads.getContent() == null || threads.getContent().isEmpty()){
            return new ResultUtil<Object>().setErrorMsg("请输入内容");
        }
        Date date = new Date();

        Topic topic = new Topic();
        topic.setFid(threads.getFid());
        topic.setAuthor(threads.getAuthor());
        topic.setAuthorid(threads.getAuthorid());
        topic.setSubject(threads.getSubject());
        topic.setAddtime(date);
//        System.out.println(topic);

        String pid = topicService.saveTopic(topic);

        threads.setId(pid);
        threads.setPid(pid);
        threads.setFtype(0);
        threads.setAddtime(date);
        System.out.println(threads);
//        threadsService.save(threads);
        return new ResultUtil<Object>().setData("发表成功");
    }
}
