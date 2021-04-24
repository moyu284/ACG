package xyz.moyuzhe.service;

import xyz.moyuzhe.entity.Topic;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.moyuzhe.vo.TopicVO;

import java.util.List;

/**
 *
 */
public interface TopicService extends IService<Topic> {
    List<TopicVO> getLatestList();
    List<Topic> getHotTopic();
    List<Topic> getThreadsHotTopic(String id);
    List<TopicVO> getForumsTopic(String id);
    String saveTopic(Topic topic);
}
