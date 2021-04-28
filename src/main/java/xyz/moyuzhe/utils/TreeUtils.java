package xyz.moyuzhe.utils;

import xyz.moyuzhe.vo.TreeNodeNew;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils {

    public List<TreeNodeNew> getTreeData(List<TreeNodeNew> list) {

        for (int i = 0; i < list.size(); i++) {
            if ("forum".equals(list.get(i).getType())) {
                list.get(i).setChildren(new ArrayList<>());
            }
            if (list.get(i).getChildren().size() < 1) {
            } else {
                getTreeData(list.get(i).getChildren());
            }
        }
        return list;
    }
}
