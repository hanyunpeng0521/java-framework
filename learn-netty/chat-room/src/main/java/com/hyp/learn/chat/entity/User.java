package com.hyp.learn.chat.entity;

import io.netty.channel.Channel;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.chat.entity
 * hyp create at 20-3-21
 **/
@Data
public class User {
    private static AtomicInteger uidGener = new AtomicInteger(1000);

    private boolean isAuth = false; // 是否认证
    private long time = 0;  // 登录时间
    private int userId;     // UID
    private String nick;    // 昵称
    private String addr;    // 地址
    private Channel channel;// 通道

    public void setUserId() {
        this.userId = uidGener.incrementAndGet();
    }
}
