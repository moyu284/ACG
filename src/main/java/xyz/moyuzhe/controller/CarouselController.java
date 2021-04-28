package xyz.moyuzhe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.moyuzhe.entity.Carousel;
import xyz.moyuzhe.service.CarouselService;
import xyz.moyuzhe.utils.ResultUtil;
import xyz.moyuzhe.vo.CarouselVO;
import xyz.moyuzhe.vo.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/carouse")
public class CarouselController {

    @Autowired
    CarouselService carouselService;

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Result<Object> getCarouselList(){
        List<Carousel> carousels = carouselService.list();
        return new ResultUtil<Object>().setData(carousels);
    }

    @RequestMapping(value = "/admin/upload",method = RequestMethod.POST)
    public Result<Object> uploadCarouse(@RequestBody Carousel carousel){
        int i = carouselService.checkRepeat(carousel.getPath());
        if (i > 0){
            return new ResultUtil<Object>().setErrorMsg("轮播图重复");
        }
//        System.out.println(carousel);
        carousel.setId(String.valueOf(UUID.randomUUID()));
        carouselService.save(carousel);
        List<Carousel> carousels = carouselService.list();
//        System.out.println(carousels);
        List<CarouselVO> carouselVOS = new ArrayList<>();
        for (Carousel carousel1 : carousels) {
            CarouselVO carouselVO = new CarouselVO();
            List<String> pathArray = new ArrayList<>();

            pathArray.add(carousel1.getPath());
            carouselVO.setId(carousel1.getId());
            carouselVO.setPath(carousel1.getPath());
            carouselVO.setPathArray(pathArray);

            carouselVOS.add(carouselVO);
        }
        return new ResultUtil<Object>().setData(carouselVOS);
    }

    @RequestMapping(value = "/admin/getList", method = RequestMethod.GET)
    public Result<Object> getList(){
        List<Carousel> carousels = carouselService.list();
//        System.out.println(carousels);
        List<CarouselVO> carouselVOS = new ArrayList<>();
        for (Carousel carousel : carousels) {
            CarouselVO carouselVO = new CarouselVO();
            List<String> pathArray = new ArrayList<>();

            pathArray.add(carousel.getPath());
            carouselVO.setId(carousel.getId());
            carouselVO.setPath(carousel.getPath());
            carouselVO.setPathArray(pathArray);

            carouselVOS.add(carouselVO);
        }
        return new ResultUtil<Object>().setData(carouselVOS);
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
    public Result<Object> deleteCarousel(String id){
        boolean flag = carouselService.removeById(id);
        if (flag){
            return getList();
        }else {
            return new ResultUtil<Object>().setErrorMsg("删除失败");
        }
    }
}
