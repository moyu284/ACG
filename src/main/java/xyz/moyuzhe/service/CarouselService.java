package xyz.moyuzhe.service;

import xyz.moyuzhe.entity.Carousel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface CarouselService extends IService<Carousel> {
    public int checkRepeat(String path);
}
