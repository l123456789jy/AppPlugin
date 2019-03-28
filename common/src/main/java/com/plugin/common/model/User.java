package com.plugin.common.model;

import java.io.Serializable;

/**
 * 演示传递自定义类型，这里的model如果需要跨组件传输就必须要实现序列化，
 * 或者转成json串进行传递
 * @author billy.qi
 * @since 18/5/28 19:43
 */
public class User implements Serializable {
    private String userName;
    private int id;

    public User(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
