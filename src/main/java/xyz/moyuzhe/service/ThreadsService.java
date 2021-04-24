package xyz.moyuzhe.service;

import xyz.moyuzhe.entity.Threads;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface ThreadsService extends IService<Threads> {
    List<Threads> getComment(String pid);
}
