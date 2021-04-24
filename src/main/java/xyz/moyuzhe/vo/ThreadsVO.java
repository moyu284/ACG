package xyz.moyuzhe.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.moyuzhe.entity.Threads;
import xyz.moyuzhe.entity.User;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThreadsVO {
    private Threads threads;
    private User user;
}
