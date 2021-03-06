package xyz.moyuzhe.service;

import xyz.moyuzhe.entity.Forums;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.moyuzhe.vo.MenuVO;
import xyz.moyuzhe.vo.TreeNode;
import xyz.moyuzhe.vo.TreeNodeNew;

import java.util.List;

/**
 *
 */
public interface ForumsService extends IService<Forums> {
    public List<TreeNode> getMenuTree();
    public List<TreeNodeNew> getAllForums();
    public List<Forums> getForumsList(String id);
}
