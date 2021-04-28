package xyz.moyuzhe.mapper;

import xyz.moyuzhe.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity xyz.moyuzhe.entity.User
 */
public interface UserMapper extends BaseMapper<User> {
    User findUser(String userName,String passwd);
    void changeHeader(String userId,String imgPath);
    int checkUser(String username);
    User getUser(String id);
}




