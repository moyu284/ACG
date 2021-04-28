package xyz.moyuzhe.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.deploy.panel.TreeBuilder;
import xyz.moyuzhe.entity.Forums;
import xyz.moyuzhe.service.ForumsService;
import xyz.moyuzhe.mapper.ForumsMapper;
import org.springframework.stereotype.Service;
import xyz.moyuzhe.utils.BuildTree;
import xyz.moyuzhe.utils.BuildTreeNew;
import xyz.moyuzhe.vo.MenuVO;
import xyz.moyuzhe.vo.TreeNode;
import xyz.moyuzhe.vo.TreeNodeNew;

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

    BuildTreeNew buildTreeNew = new BuildTreeNew();

    @Override
    public List<TreeNodeNew> getAllForums() {
        List<Forums> list = this.list();
        List<TreeNodeNew> treeNodes = new ArrayList<>();
        for (Forums forums : list) {
            TreeNodeNew treeNode = new TreeNodeNew();
            treeNode.setCode(forums.getId());
            treeNode.setPcode(forums.getFup());
            treeNode.setName(forums.getName());
            treeNode.setPath(forums.getPath());
            treeNode.setType(forums.getType());
            treeNodes.add(treeNode);
        }
        return buildTreeNew.buildTree(treeNodes);
    }

    @Override
    public List<Forums> getForumsList(String id) {
        return baseMapper.getForumsList(id);
    }


}




