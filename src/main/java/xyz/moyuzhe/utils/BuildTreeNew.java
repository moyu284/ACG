package xyz.moyuzhe.utils;

import xyz.moyuzhe.vo.TreeNode;
import xyz.moyuzhe.vo.TreeNodeNew;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 生成树结构数据
 * </p>
 * @author chenhuijie
 * @since 2021-04-20
 */
public class BuildTreeNew {
    List<TreeNodeNew> TreeNodes = new ArrayList<>();


    public List<TreeNodeNew> buildTree(List<TreeNodeNew> TreeNodes) {

        BuildTreeNew treeBuilder = new BuildTreeNew(TreeNodes);

        return treeBuilder.buildJSONTree();
    }

    public BuildTreeNew() {
    }

    public BuildTreeNew(List<TreeNodeNew> TreeNodes) {
        super();
        this.TreeNodes = TreeNodes;
    }

    // 构建JSON树形结构
    public List<TreeNodeNew> buildJSONTree() {
        List<TreeNodeNew> TreeNodeTree = buildTree();
        return TreeNodeTree;
    }

    // 构建树形结构
    public List<TreeNodeNew> buildTree() {
        List<TreeNodeNew> treeTreeNodes = new ArrayList<>();
        //获取所有根节点
        List<TreeNodeNew> rootTreeNodes = getRootTreeNodes();
        //获取每个根节点
        for (TreeNodeNew rootTreeNode : rootTreeNodes) {
            try {
                //递归这个根节点的子节点
                buildChildTreeNodes(rootTreeNode);

                //用list来存放每个根节点
                treeTreeNodes.add(rootTreeNode);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return treeTreeNodes;
    }

    // 递归子节点
    public void buildChildTreeNodes(TreeNodeNew TreeNode) {
        List<TreeNodeNew> children = getChildTreeNodes(TreeNode);
        if (!children.isEmpty()) {
            for (TreeNodeNew child : children)
                try {
                    buildChildTreeNodes(child);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            TreeNode.setChildren(children);
        }else {
            TreeNode.setChildren(new ArrayList<>());
        }
    }

    /**
     * 获取父节点下所有的子节点
     * @param pTreeNode
     * @return
     */
    public List<TreeNodeNew> getChildTreeNodes(TreeNodeNew pTreeNode) {
        List<TreeNodeNew> childTreeNodes = new ArrayList<>();

        for (TreeNodeNew n : TreeNodes) {
            if (pTreeNode.getCode().equals(n.getPcode())) {
                childTreeNodes.add(n);
            }
        }
        return childTreeNodes;
    }

    // 判断是否为根节点
    public boolean rootTreeNode(TreeNodeNew TreeNode) {
        boolean isRootTreeNode = true;
        for (TreeNodeNew n : TreeNodes) {
            if (TreeNode.getPcode().equals(n.getCode())) {
                isRootTreeNode = false;
                break;
            }
        }
        return isRootTreeNode;
    }

    // 获取集合中所有的根节点
    public List<TreeNodeNew> getRootTreeNodes() {
        List<TreeNodeNew> rootTreeNodes = new ArrayList<>();
        for (TreeNodeNew n : TreeNodes) {
            if (rootTreeNode(n)) {
                rootTreeNodes.add(n);
            }
        }
        return rootTreeNodes;
    }


}
