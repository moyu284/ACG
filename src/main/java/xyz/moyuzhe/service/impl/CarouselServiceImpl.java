package xyz.moyuzhe.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.moyuzhe.entity.Carousel;
import xyz.moyuzhe.service.CarouselService;
import xyz.moyuzhe.mapper.CarouselMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel>
implements CarouselService{

    @Override
    public int checkRepeat(String path) {
        return baseMapper.checkRepeat(path);
    }
}




