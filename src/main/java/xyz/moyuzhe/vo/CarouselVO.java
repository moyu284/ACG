package xyz.moyuzhe.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @TableName carousel
 */
@TableName(value ="carousel")
@Data
public class CarouselVO implements Serializable {
    /**
     *
     */
    @TableId
    private String id;

    /**
     *
     */
    private String path;

    private List<String> pathArray;

    /**
     *
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
