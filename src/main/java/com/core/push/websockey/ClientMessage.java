package com.core.push.websockey;

import java.util.Date;

/**
 * 客户端接收服务端消息
 * Created by GSN on 2017/10/30.\
 *
 * @author 郭少男
 */
public class ClientMessage {

    /**
     * 消息类型
     * 1 : 正常收发消息
     * 2 : 上线通知
     * 3 : 下线通知
     */
    private int messageType;

    /**
     * 发送者id
     */
    private String sendUserId;

    /**
     * 发送者姓名
     */
    private String card;

    /**
     * 发送者账号
     */
    private String userName;

    /**
     * 发送内容
     */
    private String content;

    /**
     * 发送时间
     */
    private Date time;

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
