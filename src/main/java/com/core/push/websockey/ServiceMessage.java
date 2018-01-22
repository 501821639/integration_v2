package com.core.push.websockey;

/**
 * 服务端接收客户端信息
 * Created by GSN on 2017/10/30.
 *
 * @author 郭少男
 */
public class ServiceMessage {

    /**
     * 接收用户id
     */
    private String receiveUserId;

    /**
     * 发送内容
     */
    private String content;

    public String getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
