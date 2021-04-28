package xyz.moyuzhe.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeNodeNew {

    private String code;
    private String pcode;
    private String name;
    private String path;
    private String type;
    private List<TreeNodeNew> children;

}
