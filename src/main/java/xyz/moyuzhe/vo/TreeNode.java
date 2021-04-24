package xyz.moyuzhe.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode {

    private String code;
    private String pcode;
    private String name;
    private String path;
    private List<TreeNode> children;
}
