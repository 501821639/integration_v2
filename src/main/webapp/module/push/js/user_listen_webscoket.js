(function ($, w) {

    if (!w.WebSocket) {
        alert("浏览器不支持web socket通讯协议");
        return;
    }

    //请求socket地址
    var webSocketUrl = null;

    //用户信息
    var userMessage = null;

    //聊天记录
    var userRecord = {};

    //是否建立连接
    var isConnect = false;

    //要发送给好友的id
    var sendUserId = null;

    var $cf4 = $("#cf-4"),
        $cf3 = $("#cf-3"),
        $cf2 = $("#cf-2");

    /**
     * 加载个人信息
     */
    $.ajax({
        type: "get",
        url: "/user/allMessage.shtml",
        dataType: "json",
        async: false,
        success: function (user) {
            userMessage = user;
            webSocketUrl = "ws://172.16.6.27:8080/web_socket/" + user.id + "/" + user.card + "/" + user.username;
        }
    });


    /**
     * 加载在线好友
     */
    $.ajax({
        type: 'get',
        url: '/web_socket/get_service_users.shtml',
        dataType: 'json',
        success: function (jsonArray) {
            $.each(jsonArray, function (i) {
                upLine(jsonArray[i].userId, jsonArray[i].card, jsonArray[i].userName);
            });
        }
    });


    webSocketEvent(webSocketConnect());

    /**
     * 建立web socket连接
     */
    function webSocketConnect() {
        return new WebSocket(webSocketUrl);
    }

    /**
     * 绑定事件
     */
    function webSocketEvent(ws) {

        // 打开Socket
        ws.onopen = function () {
            console.log("已建立连接");
            isConnect = true;
        };

        // 监听消息
        ws.onmessage = function (event) {

            var clientMessage = $.parseJSON(event.data);

            console.log(clientMessage);

            if (clientMessage.messageType == 1) {

                //是否与该好友存在聊天记录
                if (!userRecord[clientMessage.sendUserId]) {
                    userRecord[clientMessage.sendUserId] = [];
                }

                var json = {
                    'sendUserId': clientMessage.sendUserId,
                    'card': clientMessage.card,
                    'userName': clientMessage.userName,
                    'content': clientMessage.content,
                    'time': clientMessage.time
                };

                userRecord[clientMessage.sendUserId].push(json);

                //是否与该好友正在通讯中
                if (sendUserId === clientMessage.sendUserId) {
                    setData();
                } else {
                    var $num = $cf2.find(".hy-" + clientMessage.sendUserId).find('.user-number'),
                        number = Number($num.attr("data-user-number")) + 1;
                    $num.html("新消息" + number);
                }

                if(userMessage.id === clientMessage.sendUserId){
                    setData();
                }

                function setData() {
                    $cf3.append('<div class="row">' +
                        '<div class="col-md-12">' +
                        '<div class="time">' + clientMessage.time + '</div>' +
                        '<span>' + clientMessage.card + ':</span>' +
                        '<span>' + clientMessage.content + '</span>' +
                        '</div>' +
                        '</div>');
                    scrollTopButton();
                }


            }

            if (clientMessage.messageType == 2) {
                upLine(clientMessage.sendUserId, clientMessage.card, clientMessage.userName);
            }

            if (clientMessage.messageType == 3) {
                offLine(clientMessage.sendUserId);
            }

        };

        //连接已关闭
        ws.onclose = function (event) {
            isConnect = false;
            console.log("连接已断开");
        };

        //错误
        ws.onerror = function () {
            isConnect = false;
            alert("连接服务器错误,已断开连接");
        };

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
            closeWebSocket();
        };

        // 关闭Socket
        function closeWebSocket() {
            isConnect = false;
            ws.close();
        }

        /**
         * 发送消息
         * @param message 消息内容
         * @param RecordUserId 接受者id
         */
        function send(message, RecordUserId) {
            ws.send(JSON.stringify({
                'receiveUserId': RecordUserId,
                'content': message
            }));
        }

        w.closeWebSocket = closeWebSocket;
        w.send = send;

    }

    /**
     * 点击好友事件
     */
    $cf2.on("click", ".user", function () {

        var clickSendUserId = $(this).attr("data-user-id");
        sendUserId = clickSendUserId;
        $('.message-view').show();

        $cf2.find(".hy-" + sendUserId).find('.user-number').html("");

        //是否有聊天记录
        if (userRecord[clickSendUserId]) {
            var arr = userRecord[clickSendUserId],
                s = "";
            for (var i = 0; i < arr.length; i++) {
                s += '<div class="row">' +
                    '<div class="col-md-12">' +
                    '<div class="time">' + arr[i].time + '</div>' +
                    '<span>' + arr[i].card + ':</span>' +
                    '<span>' + arr[i].content + '</span>' +
                    '</div>' +
                    '</div>';
            }
            $cf3.html(s);
        }

        scrollTopButton();
    });

    /**
     * 发送消息事件
     */
    $cf4.find(".user-submit").click(function () {

        var message = $cf4.find(".user-content").val().trim();

        if(message === ""){
            return;
        }

        send(message, sendUserId);

    });

    //断开连接
    $cf4.find(".user-off").click(function () {
        if (!isConnect) {
            alert("未建立连接");
            return;
        }
        closeWebSocket();
    });

    //建立连接
    $cf4.find(".user-on").click(function () {
        if (isConnect) {
            alert("连接已建立");
            return;
        }
        webSocketEvent(webSocketConnect());
    });

    /**
     * 上线好友通知
     * @param userId 用户id
     * @param card 姓名
     * @param userName 账号
     */
    function upLine(userId, card, userName) {


        if (userMessage.id !== userId) {

            console.log("上线好友通知");

            $("#cf-2").append('<div class="row user hy-' + userId + '" data-user-id="' + userId + '">' +
                '<div class="col-md-12">' +
                '<span class="user-name">' + userName + '</span>' +
                '&nbsp;&nbsp;' +
                '<span class="user-card">' + card + '</span>' +
                '&nbsp;&nbsp;' +
                '<span class="user-number" data-user-number="0"></span>' +
                '</div>' +
                '</div>');
        }

    }

    /**
     * 下线好友
     * @param userId 用户id
     */
    function offLine(userId) {
        console.log("下线好友通知");
        $cf2.find(".hy-" + userId).remove();
    }

    /**
     * #cf-3中的滚动条下拉到底部
     */
    function scrollTopButton() {
        $cf3.scrollTop($cf3.get(0).scrollHeight);
    }


})(jQuery, window);