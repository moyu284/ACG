package xyz.moyuzhe.mapper;

import xyz.moyuzhe.entity.Topic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.moyuzhe.vo.TopicVO;

import java.util.List;

/**
 * @Entity xyz.moyuzhe.entity.Topic
 */
public interface TopicMapper extends BaseMapper<Topic> {
    List<TopicVO> getLatestList();
    List<Topic> getHotTopic();
    List<Topic> getThreadsHotTopic(String id);
    List<TopicVO> getForumsTopic(String id);
    void addView(String id);
}




