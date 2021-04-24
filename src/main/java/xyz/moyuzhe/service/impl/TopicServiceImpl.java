package xyz.moyuzhe.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.moyuzhe.entity.Topic;
import xyz.moyuzhe.service.TopicService;
import xyz.moyuzhe.mapper.TopicMapper;
import org.springframework.stereotype.Service;
import xyz.moyuzhe.vo.TopicVO;

import java.util.List;
import java.util.UUID;

/**
 *
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic>
implements TopicService{

    @Override
    public List<TopicVO> getLatestList() {
        return baseMapper.getLatestList();
    }

    @Override
    public List<Topic> getHotTopic() {
        return baseMapper.getHotTopic();
    }

    @Override
    public List<Topic> getThreadsHotTopic(String id) {
        return baseMapper.getThreadsHotTopic(id);
    }

    @Override
    public List<TopicVO> getForumsTopic(String id) {
        return baseMapper.getForumsTopic(id);
    }

    @Override
    public String saveTopic(Topic topic) {
        String pid = String.valueOf(UUID.randomUUID());
        topic.setId(pid);
        save(topic);
        return pid;
    }
}




