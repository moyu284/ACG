package xyz.moyuzhe.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuVO {
    private String id;
    private String menuName;
    private String menuUrl;
    private String menuUp;
    private Integer leaf;
    private List<MenuVO> children;
}
