package xyz.moyuzhe.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.moyuzhe.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountVO {
    private String token;
    private User user;
}
