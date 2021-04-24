package xyz.moyuzhe.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String userpw;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    private String headerimg;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private Integer usertype;

    /**
     * 
     */
    private Integer isdel;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}