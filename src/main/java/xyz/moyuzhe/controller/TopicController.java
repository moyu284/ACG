package xyz.moyuzhe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.moyuzhe.entity.Topic;
import xyz.moyuzhe.entity.User;
import xyz.moyuzhe.service.ForumsService;
import xyz.moyuzhe.service.TopicService;
import xyz.moyuzhe.service.UserService;
import xyz.moyuzhe.utils.ResultUtil;
import xyz.moyuzhe.vo.Result;
import xyz.moyuzhe.vo.TopicVO;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/topic")
public class TopicController {
    @Autowired
    TopicService topicService;

    @Autowired
    ForumsService forumsService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/saveTopic", method = RequestMethod.POST)
    public Result<Object> saveTopic(@RequestBody Topic topic) {
        String id = String.valueOf(UUID.randomUUID());
        topic.setId(id);
        topicService.save(topic);
        return new ResultUtil<Object>().setData(id);
    }

    @RequestMapping(value = "/getLatestList", method = RequestMethod.GET)
    public Result<Object> getLatestList() {
        List<TopicVO> latestList = topicService.getLatestList();
        for (TopicVO topicVO : latestList) {
            topicVO.setUserHeader(userService.getById(topicVO.getAuthorid()).getHeaderimg());
        }
        return new ResultUtil<Object>().setData(latestList);
    }

    @RequestMapping(value = "/getHotTopic", method = RequestMethod.GET)
    public Result<Object> getHotTopic() {
        return new ResultUtil<Object>().setData(topicService.getHotTopic());
    }

    @RequestMapping(value = "/getThreadsHotTopic", method = RequestMethod.GET)
    public Result<Object> getThreadsHotTopic(String id) {
        return new ResultUtil<Object>().setData(topicService.getThreadsHotTopic(id));
    }

    @RequestMapping(value = "/getForumsTopic", method = RequestMethod.GET)
    public Result<Object> getForumsTopic(String id) {
        List<TopicVO> forumsTopic = topicService.getForumsTopic(id);
        for (TopicVO topicVO : forumsTopic) {
            topicVO.setUserHeader(userService.getById(topicVO.getAuthorid()).getHeaderimg());
        }
        return new ResultUtil<Object>().setData(forumsTopic);
    }
}
