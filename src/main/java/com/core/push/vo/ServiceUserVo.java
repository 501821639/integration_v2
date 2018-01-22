package com.core.push.vo;

/**
 * 封装在线用户信息
 * Created by GSN on 2017/10/30.
 *
 * @author 郭少男
 */
public class ServiceUserVo {

    private String userId;
    private String card;
    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
