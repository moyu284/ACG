package xyz.moyuzhe.mapper;

import xyz.moyuzhe.entity.Threads;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity xyz.moyuzhe.entity.Threads
 */
public interface ThreadsMapper extends BaseMapper<Threads> {
    List<Threads> getComment(String pid);
}




