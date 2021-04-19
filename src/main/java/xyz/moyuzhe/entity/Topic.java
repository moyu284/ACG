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
    @TableId(type = IdType.AUTO)
    private Integer pid;

    /**
     * 
     */
    private Integer fid;

    /**
     * 
     */
    private String author;

    /**
     * 
     */
    private Integer authorid;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}