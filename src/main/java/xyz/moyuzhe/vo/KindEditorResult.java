package xyz.moyuzhe.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KindEditorResult implements Serializable{

    private int error;

    private String url;

    private String message;
}
