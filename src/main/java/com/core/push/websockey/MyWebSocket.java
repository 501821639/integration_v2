package com.core.push.websockey;

import com.utils.date.FormatType;
import com.utils.json.FormatJson;
import com.utils.json.FormatObject;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Web Socket
 * Created by GSN on 2017/10/27.
 *
 * @author 郭少男
 */
@ServerEndpoint("/web_socket/{userId}/{card}/{userName}")
public class MyWebSocket {

    /**
     * 重写hashcode equals方法
     */
    private final String id = UUID.randomUUID().toString();

    /**
     * 用户id, socket对象
     */
    public static Map<String, MyWebSocket> map = new ConcurrentHashMap<>();

    private Session session;

    /**
     * 用户相关信息
     */
    private String userId;
    private String card;
    private String userName;

    public String getUserId() {
        return userId;
    }

    public String getCard() {
        return card;
    }

    public String getUserName() {
        return userName;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId, @PathParam("card") String card, @PathParam("userName") String userName) {

        this.session = session;

        this.userId = userId;
        this.card = card;
        this.userName = userName;

        map.put(this.userId, this);

        sendUserMessage(2, "上线");

        printMap();
    }

    @OnMessage
    public void onMessage(String message) {
        ServiceMessage serviceMessage = (ServiceMessage) new FormatObject().stringConversion(message, ServiceMessage.class);
        sendUserMessage(1, serviceMessage.getContent(), serviceMessage.getReceiveUserId(), this.userId);
    }

    @OnError
    public void onError(Throwable error) {
        map.remove(this.userId);
        error.printStackTrace();
    }

    @OnClose
    public void onClose() {
        map.remove(this.userId);
        sendUserMessage(3, "下线");
    }


    /**
     * 发送信息给指定用户
     *
     * @param messageType 消息类型
     * @param message     要发送的信息
     * @param arrUserId   指定的用户id集 不指定参数发送至所有用户
     */
    private void sendUserMessage(int messageType, String message, String... arrUserId) {

        ClientMessage clientMessage = new ClientMessage();

        clientMessage.setMessageType(messageType);
        clientMessage.setSendUserId(this.userId);
        clientMessage.setCard(this.card);
        clientMessage.setUserName(this.userName);
        clientMessage.setContent(message);
        clientMessage.setTime(new Date());


        String json = new FormatJson(new FormatType().getYmdhms()).objectConversion(clientMessage);

        if (arrUserId.length == 0) {

            map.forEach((key, myWebSocket) -> {
                try {
                    myWebSocket.session.getBasicRemote().sendText(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } else {
            try {
                for (String userId : arrUserId) {
                    map.get(userId).session.getBasicRemote().sendText(json);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 控制台输出map
     */
    private void printMap() {
        System.out.println();
        map.forEach((key, myWebSocket) ->
                System.out.println("在线的用户  userId=" + key + "  card=" + myWebSocket.card)
        );
        System.out.println();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MyWebSocket)) {
            return false;
        }

        MyWebSocket that = (MyWebSocket) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
