package xyz.moyuzhe.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName forums
 */
@TableName(value ="forums")
@Data
public class Forums implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String type;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String fup;

    /**
     * 
     */
    private String path;

    /**
     * 
     */
    private String status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}