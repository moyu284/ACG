package xyz.moyuzhe.service;

import xyz.moyuzhe.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface UserService extends IService<User> {
    User findUser(String userName, String passwd);
    User getUser(String id);
    void changeHeader(String userId,String imgPath);
    int checkUser(String username);
}
