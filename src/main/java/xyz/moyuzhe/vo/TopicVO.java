package xyz.moyuzhe.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.moyuzhe.entity.Topic;
import xyz.moyuzhe.entity.User;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicVO{
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

   private String forums;

   private String userHeader;
}
