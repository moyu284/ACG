package xyz.moyuzhe.mapper;

import xyz.moyuzhe.entity.Carousel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity xyz.moyuzhe.entity.Carousel
 */
public interface CarouselMapper extends BaseMapper<Carousel> {
    int checkRepeat(String path);
}




