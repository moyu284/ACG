package xyz.moyuzhe.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.moyuzhe.entity.Threads;
import xyz.moyuzhe.service.ThreadsService;
import xyz.moyuzhe.mapper.ThreadsMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class ThreadsServiceImpl extends ServiceImpl<ThreadsMapper, Threads>
implements ThreadsService{

    @Override
    public List<Threads> getComment(String pid) {
        return baseMapper.getComment(pid);
    }

}




