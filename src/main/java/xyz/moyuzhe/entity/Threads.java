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
 * @TableName threads
 */
@TableName(value ="threads")
@Data
public class Threads implements Serializable {
    /**
     *
     */
    @TableId
    private String id;

    /**
     *
     */
    private String pid;

    /**
     *
     */
    private String fid;

    /**
     *
     */
    private Integer ftype;

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
    private String content;

    /**
     *
     */
    private Date addtime;

    /**
     *
     */
    private Integer tnum;

    /**
     *
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
