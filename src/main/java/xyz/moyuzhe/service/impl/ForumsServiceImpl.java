package xyz.moyuzhe.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.deploy.panel.TreeBuilder;
import xyz.moyuzhe.entity.Forums;
import xyz.moyuzhe.service.ForumsService;
import xyz.moyuzhe.mapper.ForumsMapper;
import org.springframework.stereotype.Service;
import xyz.moyuzhe.utils.BuildTree;
import xyz.moyuzhe.vo.MenuVO;
import xyz.moyuzhe.vo.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class ForumsServiceImpl extends ServiceImpl<ForumsMapper, Forums>
        implements ForumsService {
    BuildTree buildTree = new BuildTree();

    @Override
    public List<TreeNode> getMenuTree() {
        List<Forums> list = this.list();
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Forums forums : list) {
            TreeNode treeNode = new TreeNode();
            treeNode.setCode(forums.getId());
            treeNode.setPcode(forums.getFup());
            treeNode.setName(forums.getName());
            treeNode.setPath(forums.getPath());
            treeNodes.add(treeNode);
        }
        return buildTree.buildTree(treeNodes);
    }


}




