package xyz.moyuzhe.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.moyuzhe.entity.User;
import xyz.moyuzhe.service.UserService;
import xyz.moyuzhe.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getUser(String userName, String passwd) {
        return baseMapper.getUser(userName,passwd);
    }

    @Override
    public void changeHeader(String userId, String imgPath) {
        baseMapper.changeHeader(userId,imgPath);
    }

    @Override
    public int checkUser(String username) {
        return baseMapper.checkUser(username);
    }
}




