package xyz.moyuzhe.mapper;

import xyz.moyuzhe.entity.Forums;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity xyz.moyuzhe.entity.Forums
 */
public interface ForumsMapper extends BaseMapper<Forums> {
    List<Forums> getForumsList(String id);
}




