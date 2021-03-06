package xyz.moyuzhe.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName topic
 */
@TableName(value ="topic")
@Data
public class Topic implements Serializable {
    /**
     *
     */
    @TableId
    private String id;

    /**
     *
     */
    private String fid;

    /**
     *
     */
    private String author;

    /**
     *
     */
    private String authorid;

    /**
     *
     */
    private String subject;

    /**
     *
     */
    private Integer view;

    /**
     *
     */
    private Integer replies;

    /**
     *
     */
    private Date addtime;

    /**
     *
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
